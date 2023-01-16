package komenda;

import database.DataAccessObject;
import model.Rezerwacja;

public class KomendaListaRezerwacja implements Komenda{
    private DataAccessObject<Rezerwacja> dao = new DataAccessObject<>();

    @Override
    public String getKomenda() {
        return "lista rezerwacja";
    }

    @Override
    public void obsluga() {
        dao.findAll(Rezerwacja.class).forEach(System.out::println);
    }
}
