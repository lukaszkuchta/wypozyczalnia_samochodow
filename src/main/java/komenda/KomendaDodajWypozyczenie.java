package komenda;

import database.DataAccessObject;
import model.Klient;
import model.Rezerwacja;
import model.Wypozyczenie;

import java.util.Optional;

public class KomendaDodajWypozyczenie implements Komenda {
    private DataAccessObject<Wypozyczenie> dao = new DataAccessObject<>();
    private DataAccessObject<Rezerwacja> daoRezerwacja = new DataAccessObject<>();

    @Override
    public String getKomenda() {
        return "dodaj wypozyczenie";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id rezerwacji:");
        String idRezerwajaString = Komenda.SCANNER.nextLine();
        Long idRezerwacja = Long.parseLong(idRezerwajaString);
        Optional<Rezerwacja> rezerwacjaOptional = daoRezerwacja.find(Rezerwacja.class, idRezerwacja);
        if (rezerwacjaOptional.isEmpty()) {
            System.err.println("Rezerwacja o podanym id nie istnieje");
            return;
        }

        System.out.println("Podaj uwagi:");
        String uwagi = Komenda.SCANNER.nextLine();

        Wypozyczenie wypozyczenie = Wypozyczenie.builder()
                .uwagi(uwagi)
                .rezerwacja(rezerwacjaOptional.get())
                .build();
        dao.insert(wypozyczenie);
    }
}
