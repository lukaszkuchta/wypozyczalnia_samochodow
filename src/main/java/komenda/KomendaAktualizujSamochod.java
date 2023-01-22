package komenda;

import database.DataAccessObject;
import model.Samochod;

public class KomendaAktualizujSamochod implements Komenda {
    private DataAccessObject<Samochod> dataAccessObject;

    public KomendaAktualizujSamochod() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "aktualizuj samochod";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id samochodu:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        if (!dataAccessObject.exists(Samochod.class, id)) {
            System.err.println("Blad, samochod o takim id nie istnieje!");
            return;
        }

        System.out.println("Podaj marke samochodu:");
        String marka = Komenda.SCANNER.nextLine();

        System.out.println("Podaj model samochodu:");
        String model = Komenda.SCANNER.nextLine();

        System.out.println("Podaj rocznik samochodu:");
        String rocznik = Komenda.SCANNER.nextLine();

        System.out.println("Podaj kolor samochodu:");
        String kolor = Komenda.SCANNER.nextLine();

        System.out.println("Podaj kwote za jeden dzien wynajecia samochodu:");
        String kwotaZaJedenDzienString = Komenda.SCANNER.nextLine();
        double kwotaZaJedenDzien = Double.parseDouble(kwotaZaJedenDzienString);

        Samochod samochod = Samochod.builder()
                .marka(marka)
                .model(model)
                .rocznik(rocznik)
                .kolor(kolor)
                .kwotaZaJedenDzien(kwotaZaJedenDzien)
                .id(id)
                .build();

        dataAccessObject.update(Samochod.class, id, samochod);
    }
}