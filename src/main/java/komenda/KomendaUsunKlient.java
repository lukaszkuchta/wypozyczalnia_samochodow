package komenda;

import database.DataAccessObject;
import model.Klient;

public class KomendaUsunKlient implements Komenda{
    private DataAccessObject<Klient> dataAccessObject;

    public KomendaUsunKlient() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "usun klient";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id usuwanego klienta:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        if(dataAccessObject.delete(Klient.class, id)){
            System.out.println("Usunieto klienta!");
        }else{
            System.err.println("Nie udalo sie usunac klienta!");
        }
    }
}
