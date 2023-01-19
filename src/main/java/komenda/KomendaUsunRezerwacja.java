package komenda;

import database.DataAccessObject;
import model.Rezerwacja;

public class KomendaUsunRezerwacja implements Komenda{
    private DataAccessObject<Rezerwacja> dataAccessObject;

    public KomendaUsunRezerwacja() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "usun rezerwacja";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id usuwanej rezerwacji:");
        String idString = Komenda.SCANNER.nextLine();
        Long id = Long.parseLong(idString);

        if(dataAccessObject.delete(Rezerwacja.class, id)){
            System.out.println("Usunieto rezerwacje!");
        }else{
            System.err.println("Nie udalo sie usunac rezerwacji!");
        }
    }
}
