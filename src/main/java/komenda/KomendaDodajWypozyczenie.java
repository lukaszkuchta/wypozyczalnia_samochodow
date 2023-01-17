package komenda;

import database.DataAccessObject;
import model.Klient;
import model.Rezerwacja;
import model.Wypozyczenie;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if(rezerwacjaOptional.get().getDataOd().compareTo(LocalDate.now()) > 0){
            System.err.println("Nie da sie wypozyczyc samochodu przed data wpisana w rezerwacji");
            return;
        }
        if(rezerwacjaOptional.get().getDataDo().compareTo(LocalDate.now()) < 0){
            System.err.println("Rezerwacja sie skonczyla");
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
