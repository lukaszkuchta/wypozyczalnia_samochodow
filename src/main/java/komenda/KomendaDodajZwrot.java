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
        double kara = Double.parseDouble(kwotaString);

//        String przeroczenieDniRezerwacji;
//        double karaZaPrzekroczoneDni = 0;
//        do {
//            System.out.println("Czy klient przekroczyl date rezerwacji?(Tak/Nie):");
//            przeroczenieDniRezerwacji = Komenda.SCANNER.nextLine();
//            if (przeroczenieDniRezerwacji.equalsIgnoreCase("Nie")) {
//                continue;
//            }
//            if (przeroczenieDniRezerwacji.equalsIgnoreCase("Tak")) {
//                LocalDate dataDo = wypozyczenieOptional.get().getRezerwacja().getDataDo();
//                long przekroczenieOIleDni = Period.between(dataDo,LocalDate.now()).getDays();
//                double kwotaZaJedenDzien = wypozyczenieOptional.get().getRezerwacja().getSamochod().getKwotaZaJedenDzien();
//                karaZaPrzekroczoneDni = 2*kwotaZaJedenDzien*przekroczenieOIleDni;
//            }
//        }while(!(przeroczenieDniRezerwacji.equalsIgnoreCase("Nie") || przeroczenieDniRezerwacji.equalsIgnoreCase("Tak")));
//
//        double cenaZaWynajem = wypozyczenieOptional.get().getRezerwacja().getKwota() + kara + karaZaPrzekroczoneDni;

        LocalDate dataOd = wypozyczenieOptional.get().getRezerwacja().getDataOd();
        LocalDate dataDo = wypozyczenieOptional.get().getRezerwacja().getDataDo();
        double kwotaZaJedenDzien = wypozyczenieOptional.get().getRezerwacja().getSamochod().getKwotaZaJedenDzien();
        double karaZaPrzekroczoneDni = 0;
        double kwotaZaFizycznePosiadanieSamochodu = 0;
        long roznicaDniWRezerwacjiIZwrocie = Period.between(dataDo,LocalDate.now()).getDays();
        long ileKlientDniMialSamochod = Period.between(dataOd,dataDo).getDays();

        if(dataDo.isAfter(LocalDate.now())){
            kwotaZaFizycznePosiadanieSamochodu = ileKlientDniMialSamochod*kwotaZaJedenDzien;
            karaZaPrzekroczoneDni = 2*kwotaZaJedenDzien*roznicaDniWRezerwacjiIZwrocie;
        }else if(dataDo.isBefore(LocalDate.now())){
            kwotaZaFizycznePosiadanieSamochodu = ileKlientDniMialSamochod*kwotaZaJedenDzien;
            karaZaPrzekroczoneDni = 0.5*kwotaZaJedenDzien*roznicaDniWRezerwacjiIZwrocie;
        }else if(dataDo.equals(LocalDate.now())){
            kwotaZaFizycznePosiadanieSamochodu = ileKlientDniMialSamochod*kwotaZaJedenDzien;
            karaZaPrzekroczoneDni = 0;
        }
        double cenaZaWynajem = kwotaZaFizycznePosiadanieSamochodu + kara + karaZaPrzekroczoneDni;

        Zwrot zwrot = Zwrot.builder()
                .uwagi(uwagi)
                .wypozyczenie(wypozyczenieOptional.get())
                .cenaZaWynajem(cenaZaWynajem)
                .build();
        dao.insert(zwrot);
    }
}
