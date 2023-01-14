package model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rezerwacja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDate dataRezerwacji;

    private LocalDate dataOd;
    private LocalDate dataDo;
    private double kwota;

    @OneToOne(mappedBy = "rezerwacja")
    @EqualsAndHashCode.Exclude
    private Wypozyczenie wypozyczenie;

    @OneToOne(mappedBy = "rezerwacja")
    @EqualsAndHashCode.Exclude
    private Zwrot zwrot;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Klient klient;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Samochod samochod;
}
