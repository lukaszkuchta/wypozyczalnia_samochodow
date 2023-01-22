package komenda;

import database.DataAccessObject;
import model.Rezerwacja;
import model.Wypozyczenie;
import model.Zwrot;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class KomendaDodajZwrot implements Komenda{
    private DataAccessObject<Zwrot> dao = new DataAccessObject<>();
    private DataAccessObject<Wypozyczenie> daoWypozyczenie = new DataAccessObject<>();

    @Override
    public String getKomenda() {
        return "dodaj zwrot";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id wypozyczenia:");
        String idWypozyczenieString = Komenda.SCANNER.nextLine();
        Long idWypozyczenie = Long.parseLong(idWypozyczenieString);
        Optional<Wypozyczenie> wypozyczenieOptional = daoWypozyczenie.find(Wypozyczenie.class, idWypozyczenie);
        if (wypozyczenieOptional.isEmpty()) {
            System.err.println("Wypozyczenie o podanym id nie istnieje");
            return;
        }

        System.out.println("Podaj uwagi:");
        String uwagi = Komenda.SCANNER.nextLine();

        System.out.println("Podaj kare, ktora klient ma zaplacic jesli cos zniszczyl w samochodzie:");
        String kwotaString = Komenda.SCANNER.nextLine();
        double karaZaUszkodzenia = Double.parseDouble(kwotaString);

        LocalDate dataDo = wypozyczenieOptional.get().getRezerwacja().getDataDo();
        double kwotaZaJedenDzien = wypozyczenieOptional.get().getRezerwacja().getSamochod().getKwotaZaJedenDzien();
        double kwotaPobranaPrzyRezerwacji = wypozyczenieOptional.get().getRezerwacja().getKwota();
        double karaZaPrzekroczoneDni = kwotaZaJedenDzien*2;
        double zwrotZaSzybszeOddanieSamochoodu = kwotaZaJedenDzien*0.5;
        long roznicaDniWRezerwacjiIZwrocie = Math.abs(Period.between(dataDo,LocalDate.now()).getDays());
        double cenaZaWynajem = 0;

        if(dataDo.isAfter(LocalDate.now())){
            cenaZaWynajem = kwotaPobranaPrzyRezerwacji + karaZaUszkodzenia - roznicaDniWRezerwacjiIZwrocie*zwrotZaSzybszeOddanieSamochoodu;
        }else if(dataDo.isBefore(LocalDate.now())){
            cenaZaWynajem = kwotaPobranaPrzyRezerwacji + karaZaUszkodzenia + roznicaDniWRezerwacjiIZwrocie*karaZaPrzekroczoneDni;
        }else if(dataDo.equals(LocalDate.now())){
            cenaZaWynajem = kwotaPobranaPrzyRezerwacji + karaZaUszkodzenia;
        }

        Zwrot zwrot = Zwrot.builder()
                .uwagi(uwagi)
                .wypozyczenie(wypozyczenieOptional.get())
                .cenaZaWynajem(cenaZaWynajem)
                .build();
        dao.insert(zwrot);
    }
}
