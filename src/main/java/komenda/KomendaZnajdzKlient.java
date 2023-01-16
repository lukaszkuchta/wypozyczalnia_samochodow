package komenda;

import database.DataAccessObject;
import model.Klient;

import java.util.Optional;

public class KomendaZnajdzKlient implements Komenda{
    private DataAccessObject<Klient> dataAccessObject;

    public KomendaZnajdzKlient() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "znajdz klient";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id szukanego klienta:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        Optional<Klient> klientOptional = dataAccessObject.find(Klient.class, id);
        if (klientOptional.isPresent()) {
            System.out.println(klientOptional.get());
        } else {
            System.err.println("Nie znaleziono klienta!");
        }
    }
}
