package komenda;

import database.DataAccessObject;
import model.Wypozyczenie;

import java.util.Optional;

public class KomendaZnajdzWypozyczenie implements Komenda{
    private DataAccessObject<Wypozyczenie> dataAccessObject;

    public KomendaZnajdzWypozyczenie() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "znajdz wypozyczenie";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id szukanego wypozyczenia:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        Optional<Wypozyczenie> wypozyczenieOptional = dataAccessObject.find(Wypozyczenie.class, id);
        if (wypozyczenieOptional.isPresent()) {
            System.out.println(wypozyczenieOptional.get());
        } else {
            System.err.println("Nie znaleziono wypozyczenia!");
        }
    }
}
