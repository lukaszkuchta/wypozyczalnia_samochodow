package komenda;

import database.DataAccessObject;
import model.Klient;

public class KomendaAktualizujKlient implements Komenda{
    private DataAccessObject<Klient> dataAccessObject;

    public KomendaAktualizujKlient() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "aktualizuj klient";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id klienta:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        if (!dataAccessObject.exists(Klient.class, id)) {
            System.err.println("Blad, klient o takim id nie istnieje!");
            return;
        }

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
                .id(id)
                .build();

        dataAccessObject.update(Klient.class, id, klient);
    }
}
