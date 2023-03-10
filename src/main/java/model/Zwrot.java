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
public class Zwrot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDate dataZwrotu;
    private String uwagi;
    private double cenaZaWynajem;

    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Wypozyczenie wypozyczenie;


}
