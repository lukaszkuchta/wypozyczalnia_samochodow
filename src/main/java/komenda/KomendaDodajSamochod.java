package komenda;

import database.DataAccessObject;
import model.Klient;
import model.Samochod;

public class KomendaDodajSamochod implements Komenda{
    private DataAccessObject<Samochod> dao = new DataAccessObject<>();
    @Override
    public String getKomenda() {
        return "dodaj samochod";
    }

    @Override
    public void obsluga() {
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
                .build();
        dao.insert(samochod);
    }
}
