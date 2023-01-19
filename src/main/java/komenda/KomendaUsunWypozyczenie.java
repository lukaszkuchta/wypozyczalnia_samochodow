package komenda;

import database.DataAccessObject;
import model.Wypozyczenie;

public class KomendaUsunWypozyczenie implements Komenda{
    private DataAccessObject<Wypozyczenie> dataAccessObject;

    public KomendaUsunWypozyczenie() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "usun wypozyczenie";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id usuwanego wypozyczenia:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        if(dataAccessObject.delete(Wypozyczenie.class, id)){
            System.out.println("Usunięto wypozyczenie!");
        }else{
            System.err.println("Nie udalo sie usunac wypozyczenia!");
        }
    }
}
