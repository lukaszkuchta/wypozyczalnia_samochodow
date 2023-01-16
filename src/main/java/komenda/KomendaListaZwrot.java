package komenda;

import database.DataAccessObject;
import model.Zwrot;

public class KomendaListaZwrot implements Komenda{
    private DataAccessObject<Zwrot> dao = new DataAccessObject<>();

    @Override
    public String getKomenda() {
        return "lista zwrot";
    }

    @Override
    public void obsluga() {
        dao.findAll(Zwrot.class).forEach(System.out::println);
    }
}
