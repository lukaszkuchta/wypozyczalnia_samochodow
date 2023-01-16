package komenda;

import database.DataAccessObject;
import model.Samochod;

import java.util.Optional;

public class KomendaZnajdzSamochod implements Komenda{
    private DataAccessObject<Samochod> dataAccessObject;

    public KomendaZnajdzSamochod() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "znajdz samochod";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id szukanego samochodu:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        Optional<Samochod> samochodOptional = dataAccessObject.find(Samochod.class, id);
        if (samochodOptional.isPresent()) {
            System.out.println(samochodOptional.get());
        } else {
            System.err.println("Nie znaleziono samochodu!");
        }
    }
}
