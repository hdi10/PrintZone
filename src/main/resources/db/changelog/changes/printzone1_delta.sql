-- 1️⃣ Bestehende Tabellen in richtiger Reihenfolge droppen
DROP TABLE IF EXISTS ist;
DROP TABLE IF EXISTS bestellung;
DROP TABLE IF EXISTS lieferadresse;
DROP TABLE IF EXISTS printmedium;
DROP TABLE IF EXISTS kunde;

-- 2️⃣ Tabellen neu anlegen
CREATE TABLE kunde (
                       kunde_id SERIAL PRIMARY KEY,
                       vorname VARCHAR(50) NOT NULL,
                       nachname VARCHAR(50) NOT NULL,
                       email VARCHAR(100),
                       telefon VARCHAR(20)
);

CREATE TABLE printmedium (
                             printmedium_id SERIAL PRIMARY KEY,
                             titel VARCHAR(100) NOT NULL,
                             art VARCHAR(50) NOT NULL,
                             preis DECIMAL(9,2) NOT NULL
);

CREATE TABLE lieferadresse (
                               lieferadresse_id SERIAL PRIMARY KEY,
                               kunde_id INTEGER NOT NULL,
                               strasse VARCHAR(100) NOT NULL,
                               plz VARCHAR(10) NOT NULL,
                               ort VARCHAR(50) NOT NULL,
                               FOREIGN KEY (kunde_id) REFERENCES kunde(kunde_id)
);

CREATE TABLE bestellung (
                            bestellung_id SERIAL PRIMARY KEY,
                            kunde_id INTEGER NOT NULL,
                            lieferadresse_id INTEGER NOT NULL,
                            bestelldatum DATE NOT NULL,
                            gesamtpreis DECIMAL(14,2) NOT NULL,
                            FOREIGN KEY (kunde_id) REFERENCES kunde(kunde_id),
                            FOREIGN KEY (lieferadresse_id) REFERENCES lieferadresse(lieferadresse_id)
);

CREATE TABLE ist (
                     bestellung_id INTEGER NOT NULL,
                     printmedium_id INTEGER NOT NULL,
                     menge INTEGER NOT NULL DEFAULT 1,
                     PRIMARY KEY (bestellung_id, printmedium_id),
                     FOREIGN KEY (bestellung_id) REFERENCES bestellung(bestellung_id),
                     FOREIGN KEY (printmedium_id) REFERENCES printmedium(printmedium_id)
);

-- 3️⃣ Trigger-Funktion & Trigger
CREATE OR REPLACE FUNCTION berechne_gesamtpreis()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE bestellung
    SET gesamtpreis = (
        SELECT SUM(i.menge * p.preis)
        FROM ist i
                 JOIN printmedium p ON i.printmedium_id = p.printmedium_id
        WHERE i.bestellung_id = NEW.bestellung_id
    )
    WHERE bestellung_id = NEW.bestellung_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_gesamtpreis_nach_insert
    AFTER INSERT OR UPDATE OR DELETE ON ist
    FOR EACH ROW
EXECUTE FUNCTION berechne_gesamtpreis();

-- 4️⃣ Daten einfügen
INSERT INTO kunde (vorname, nachname, email, telefon) VALUES
                                                          ('Max', 'Mustermann', 'max.mustermann@example.com', '0301234567'),
                                                          ('Erika', 'Musterfrau', 'erika.musterfrau@example.com', '01761234567'),
                                                          ('Hans', 'Schmidt', 'h.schmidt@example.com', '0409876543'),
                                                          ('Julia', 'Mayer', 'j.mayer@example.com', '08911223344'),
                                                          ('Peter', 'Müller', 'p.mueller@example.com', '06999887766');

INSERT INTO lieferadresse (kunde_id, strasse, plz, ort) VALUES
                                                            (1, 'Musterstrasse 1', '10115', 'Berlin'),
                                                            (1, 'Beispielweg 5', '04229', 'Leipzig'),
                                                            (2, 'Hauptstrasse 10', '20095', 'Hamburg'),
                                                            (3, 'Bahnhofstrasse 20', '50667', 'Köln'),
                                                            (4, 'Schulweg 3', '80333', 'München');

INSERT INTO printmedium (titel, art, preis) VALUES
                                                ('Der Zeitungsbote', 'Zeitung', 2.50),
                                                ('Datenbanken verstehen', 'Buch', 29.90),
                                                ('Die Algorithmik', 'Buch', 45.00),
                                                ('Wissenschafts-Magazin', 'Magazin', 5.75),
                                                ('Reisejournal', 'Magazin', 7.20);

INSERT INTO bestellung (kunde_id, lieferadresse_id, bestelldatum, gesamtpreis) VALUES
                                                                                   (1, 1, '2025-03-01', 62.90),
                                                                                   (1, 2, '2025-03-15', 45.00),
                                                                                   (2, 3, '2025-03-20', 46.25),
                                                                                   (3, 4, '2025-03-25', 16.20),
                                                                                   (4, 5, '2025-04-01', 5.75);

INSERT INTO ist (bestellung_id, printmedium_id, menge) VALUES
                                                           (1, 1, 1),
                                                           (1, 2, 2),
                                                           (2, 3, 1),
                                                           (3, 2, 1),
                                                           (3, 4, 3),
                                                           (4, 5, 2),
                                                           (4, 1, 1),
                                                           (5, 4, 1);
