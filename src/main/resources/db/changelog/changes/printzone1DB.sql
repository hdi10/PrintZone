DROP TABLE "Printmedium";
DROP TABLE "Bestellung";
DROP TABLE "Lieferadresse";
DROP TABLE "Kunde";
DROP TABLE "ist";




BEGIN;
-- Erstelle Tabelle "Kunde"
CREATE TABLE "Kunde" (
    "Kunde_ID" INTEGER PRIMARY KEY,
    "Vorname" VARCHAR(50) NOT NULL,
    "Nachname" VARCHAR(50) NOT NULL,
    "Email" VARCHAR(100),
    "Telefon" VARCHAR(20)
);
-- Erstelle Tabelle "Printmedium"
CREATE TABLE "Printmedium" (
    "Printmedium_ID" INTEGER PRIMARY KEY,
    "Titel" VARCHAR(100) NOT NULL,
    "Art" VARCHAR(50) NOT NULL,  -- z.B. Buch, Magazin, Zeitung
    "Preis" DECIMAL(9,2) NOT NULL
);
-- Erstelle Tabelle "Lieferadresse"
CREATE TABLE "Lieferadresse" (
    "Lieferadresse_ID" INTEGER PRIMARY KEY,
    "Kunde_ID" INTEGER NOT NULL,
    "Strasse" VARCHAR(100) NOT NULL,
    "PLZ" VARCHAR(10) NOT NULL,
    "Ort" VARCHAR(50) NOT NULL,
    FOREIGN KEY ("Kunde_ID") REFERENCES "Kunde"("Kunde_ID")
);
-- Erstelle Tabelle "Bestellung"
CREATE TABLE "Bestellung" (
    "Bestellung_ID" INTEGER PRIMARY KEY,
    "Kunde_ID" INTEGER NOT NULL,
    "Lieferadresse_ID" INTEGER NOT NULL,
    "Bestelldatum" DATE NOT NULL,
	"Gesamtpreis" DECIMAL (14,2) NOT NULL,
    FOREIGN KEY ("Kunde_ID") REFERENCES "Kunde"("Kunde_ID"),
    FOREIGN KEY ("Lieferadresse_ID") REFERENCES "Lieferadresse"("Lieferadresse_ID")
);
-- Erstelle Tabelle "ist" (Verbindungstabelle zwischen Bestellung und Printmedium)
CREATE TABLE "ist" (
    "Bestellung_ID" INTEGER NOT NULL,
    "Printmedium_ID" INTEGER NOT NULL,
    "Menge" INTEGER NOT NULL DEFAULT 1,
    PRIMARY KEY ("Bestellung_ID", "Printmedium_ID"),
    FOREIGN KEY ("Bestellung_ID") REFERENCES "Bestellung"("Bestellung_ID"),
    FOREIGN KEY ("Printmedium_ID") REFERENCES "Printmedium"("Printmedium_ID")
);
COMMIT;



BEGIN;
-- Funktion zur Berechnung
CREATE OR REPLACE FUNCTION berechne_gesamtpreis()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE "Bestellung"
    SET "Gesamtpreis" = (
        SELECT SUM(i."Menge" * p."Preis")
        FROM "ist" i
        JOIN "Printmedium" p ON i."Printmedium_ID" = p."Printmedium_ID"
        WHERE i."Bestellung_ID" = NEW."Bestellung_ID"
    )
    WHERE "Bestellung_ID" = NEW."Bestellung_ID";

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

COMMIT;

BEGIN;
-- Trigger erstellen
CREATE TRIGGER trigger_gesamtpreis_nach_insert
AFTER INSERT OR UPDATE OR DELETE ON "ist"
FOR EACH ROW
EXECUTE FUNCTION berechne_gesamtpreis();

COMMIT;

BEGIN;
-- Daten für Tabelle "Kunde"
INSERT INTO "Kunde" ("Kunde_ID", "Vorname", "Nachname", "Email", "Telefon") VALUES
    (1, 'Max', 'Mustermann', 'max.mustermann@example.com', '0301234567'),
    (2, 'Erika', 'Musterfrau', 'erika.musterfrau@example.com', '01761234567'),
    (3, 'Hans', 'Schmidt', 'h.schmidt@example.com', '0409876543'),
    (4, 'Julia', 'Mayer', 'j.mayer@example.com', '08911223344'),
    (5, 'Peter', 'Müller', 'p.mueller@example.com', '06999887766');
-- Daten für Tabelle "Lieferadresse"
INSERT INTO "Lieferadresse" ("Lieferadresse_ID", "Kunde_ID", "Strasse", "PLZ", "Ort") VALUES
    (1, 1, 'Musterstrasse 1', '10115', 'Berlin'),
    (2, 1, 'Beispielweg 5', '04229', 'Leipzig'),
    (3, 2, 'Hauptstrasse 10', '20095', 'Hamburg'),
    (4, 3, 'Bahnhofstrasse 20', '50667', 'Köln'),
    (5, 4, 'Schulweg 3', '80333', 'München');
-- Daten für Tabelle "Printmedium"
INSERT INTO "Printmedium" ("Printmedium_ID", "Titel", "Art", "Preis") VALUES
    (1, 'Der Zeitungsbote', 'Zeitung', 2.50),
    (2, 'Datenbanken verstehen', 'Buch', 29.90),
    (3, 'Die Algorithmik', 'Buch', 45.00),
    (4, 'Wissenschafts-Magazin', 'Magazin', 5.75),
    (5, 'Reisejournal', 'Magazin', 7.20);
-- Daten für Tabelle "Bestellung"

------Lösen über TRIGGER----->

-- (Jede Bestellung verweist auf einen Kunden und eine Lieferadresse)
INSERT INTO "Bestellung" ("Bestellung_ID", "Kunde_ID", "Lieferadresse_ID", "Bestelldatum", "Gesamtpreis") VALUES
    (1, 1, 1, '2025-03-01', 62.90), -- 1×2.50 + 2×29.90 = 2.50 + 59.80
    (2, 1, 2, '2025-03-15', 45.00), -- 1×45.00
    (3, 2, 3, '2025-03-20', 46.25), -- 1×29.90 + 3×5.75 = 29.90 + 17.25
    (4, 3, 4, '2025-03-25', 16.20), -- 2×7.20 + 1×2.50 = 14.40 + 2.50
    (5, 4, 5, '2025-04-01', 5.75);  -- 1×5.75

-- Daten für Tabelle "ist"
-- (Zuordnung von Printmedien zu Bestellungen mit Menge)
INSERT INTO "ist" ("Bestellung_ID", "Printmedium_ID", "Menge") VALUES
    (1, 1, 1),  -- Bestellung 1 enthält 1 Exemplar von Printmedium 1
    (1, 2, 2),  -- Bestellung 1 enthält 2 Exemplare von Printmedium 2
    (2, 3, 1),  -- Bestellung 2 enthält 1 Exemplar von Printmedium 3
    (3, 2, 1),  -- Bestellung 3 enthält 1 Exemplar von Printmedium 2
    (3, 4, 3),  -- Bestellung 3 enthält 3 Exemplare von Printmedium 4
    (4, 5, 2),  -- Bestellung 4 enthält 2 Exemplare von Printmedium 5
    (4, 1, 1),  -- Bestellung 4 enthält 1 Exemplar von Printmedium 1
    (5, 4, 1);  -- Bestellung 5 enthält 1 Exemplar von Printmedium 4
COMMIT;
