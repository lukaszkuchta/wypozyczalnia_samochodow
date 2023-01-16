package model;

import jakarta.persistence.*;
import komenda.KomendaDodajRezerwacje;
import lombok.*;

import java.time.Duration;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Samochod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marka;
    private String model;
    private String rocznik;
    private String kolor;
    private double kwotaZaJedenDzien;

    @OneToMany(mappedBy = "samochod")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Rezerwacja> rezerwacje;
}
