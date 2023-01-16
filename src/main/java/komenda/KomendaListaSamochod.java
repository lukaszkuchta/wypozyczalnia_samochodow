package komenda;

import database.DataAccessObject;
import model.Samochod;

public class KomendaListaSamochod implements Komenda{
    private DataAccessObject<Samochod> dao = new DataAccessObject<>();

    @Override
    public String getKomenda() {
        return "lista samochod";
    }

    @Override
    public void obsluga() {
        dao.findAll(Samochod.class).forEach(System.out::println);
    }
}
