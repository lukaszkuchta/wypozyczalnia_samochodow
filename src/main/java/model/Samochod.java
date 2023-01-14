package model;

import jakarta.persistence.*;
import lombok.*;

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
    @Enumerated(EnumType.STRING)
    private Status status;
    private double kwotaZaJedenDzien;

    @OneToMany(mappedBy = "rezerwacja")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Rezerwacja> rezerwacje;
}
