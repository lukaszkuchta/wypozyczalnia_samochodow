package model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import java.time.Duration;
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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Wypozyczenie wypozyczenie;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Klient klient;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Samochod samochod;
}
