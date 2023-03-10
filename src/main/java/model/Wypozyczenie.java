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
public class Wypozyczenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDate dataWypozyczenia;
    private String uwagi;

    @OneToOne
    @EqualsAndHashCode.Exclude
    private Rezerwacja rezerwacja;

    @OneToOne(mappedBy = "wypozyczenie")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Zwrot zwrot;
}
