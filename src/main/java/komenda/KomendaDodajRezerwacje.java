package komenda;

import database.DataAccessObject;
import model.Klient;
import model.Rezerwacja;
import model.Samochod;

import java.time.LocalDate;
import java.util.Optional;

public class KomendaDodajRezerwacje implements Komenda{
    private DataAccessObject<Rezerwacja> dao = new DataAccessObject<>();
    private DataAccessObject<Klient> daoKlient = new DataAccessObject<>();
    private DataAccessObject<Samochod> daoSamochod = new DataAccessObject<>();
    @Override
    public String getKomenda() {
        return "dodaj rezerwacja";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id klienta:");
        String idKlientString = Komenda.SCANNER.nextLine();
        Long idKlient = Long.parseLong(idKlientString);
        Optional<Klient> klientOptional = daoKlient.find(Klient.class, idKlient);
        if (klientOptional.isEmpty()) {
            System.err.println("Klient o podanym id nie istnieje");
            return;
        }
        System.out.println("Podaj id samochodu:");
        String idSamochodString = Komenda.SCANNER.nextLine();
        Long idSamochod = Long.parseLong(idSamochodString);
        Optional<Samochod> samochodOptional = daoSamochod.find(Samochod.class, idSamochod);
        if (samochodOptional.isEmpty()) {
            System.err.println("Samochod o podanym id nie istnieje");
            return;
        }

        LocalDate dataOd;
        do {
            System.out.println("Podaj od kiedy rezerwacja(yyyy-mm-dd):");
            String dataOdString = Komenda.SCANNER.nextLine();
            dataOd = LocalDate.parse(dataOdString);
        } while(!dataOd.isAfter(LocalDate.now()));

        LocalDate dataDo;
        do {
            System.out.println("Podaj do kiedy rezerwacja(yyyy-mm-dd):");
            String dataDoString = Komenda.SCANNER.nextLine();
            dataDo = LocalDate.parse(dataDoString);
        } while(!dataDo.isAfter(dataOd));

        Rezerwacja rezerwacja = Rezerwacja.builder()
                .klient(klientOptional.get())
                .samochod(samochodOptional.get())
                .dataOd(dataOd)
                .dataDo(dataDo)
                .build();
        dao.insert(rezerwacja);
    }
}
