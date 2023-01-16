package komenda;

import database.DataAccessObject;
import model.Zwrot;

import java.util.Optional;

public class KomendaZnajdzZwrot implements Komenda{
    private DataAccessObject<Zwrot> dataAccessObject;

    public KomendaZnajdzZwrot() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "znajdz zwrot";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id szukanego zwrotu:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        Optional<Zwrot> zwrotOptional = dataAccessObject.find(Zwrot.class, id);
        if (zwrotOptional.isPresent()) {
            System.out.println(zwrotOptional.get());
        } else {
            System.err.println("Nie znaleziono zwrotu!");
        }
    }
}
