package komenda;

import database.DataAccessObject;
import model.Klient;

public class KomendaDodajKlient implements Komenda{
    private DataAccessObject<Klient> dao = new DataAccessObject<>();
    @Override
    public String getKomenda() {
        return "dodaj klient";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj imie klienta:");
        String imie = Komenda.SCANNER.nextLine();

        System.out.println("Podaj nazwisko klienta:");
        String nazwisko = Komenda.SCANNER.nextLine();

        System.out.println("Podaj email klienta:");
        String email = Komenda.SCANNER.nextLine();

        System.out.println("Podaj adres klienta:");
        String adres = Komenda.SCANNER.nextLine();

        Klient klient = Klient.builder()
                .imie(imie)
                .nazwisko(nazwisko)
                .email(email)
                .adres(adres)
                .build();
        dao.insert(klient);
    }
}
