package komenda;

import database.DataAccessObject;
import model.Rezerwacja;

import java.util.Optional;

public class KomendaZnajdzRezerwacja implements Komenda{
    private DataAccessObject<Rezerwacja> dataAccessObject;

    public KomendaZnajdzRezerwacja() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "znajdz rezerwacja";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id szukanej rezerwacji:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        Optional<Rezerwacja> rezerwacjaOptional = dataAccessObject.find(Rezerwacja.class, id);
        if (rezerwacjaOptional.isPresent()) {
            System.out.println(rezerwacjaOptional.get());
        } else {
            System.err.println("Nie znaleziono rezerwacji!");
        }
    }
}
