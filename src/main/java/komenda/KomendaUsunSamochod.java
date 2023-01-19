package komenda;

import database.DataAccessObject;
import model.Samochod;

public class KomendaUsunSamochod implements Komenda{
    private DataAccessObject<Samochod> dataAccessObject;

    public KomendaUsunSamochod() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "usun samochod";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id usuwanego samochodu:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        if(dataAccessObject.delete(Samochod.class, id)){
            System.out.println("Usunieto samochod!");
        }else{
            System.err.println("Nie udalo sie usunac samochodu!");
        }
    }
}
