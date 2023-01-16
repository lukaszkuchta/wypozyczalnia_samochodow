package komenda;

import database.DataAccessObject;
import model.Klient;

public class KomendaListaKlient implements Komenda{
    private DataAccessObject<Klient> dao = new DataAccessObject<>();

    @Override
    public String getKomenda() {
        return "lista klient";
    }

    @Override
    public void obsluga() {
        dao.findAll(Klient.class).forEach(System.out::println);
    }
}
