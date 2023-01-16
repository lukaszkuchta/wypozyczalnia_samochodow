package komenda;

import database.DataAccessObject;
import model.Wypozyczenie;

public class KomendaListaWypozyczenie implements Komenda{
    private DataAccessObject<Wypozyczenie> dao = new DataAccessObject<>();

    @Override
    public String getKomenda() {
        return "lista wypozyczenie";
    }

    @Override
    public void obsluga() {
        dao.findAll(Wypozyczenie.class).forEach(System.out::println);
    }
}
