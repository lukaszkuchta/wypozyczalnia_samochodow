package model;

import jakarta.persistence.*;
import lombok.*;

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

    private LocalDate dataZwrotu;
    private String uwagi;

    @OneToOne
    @EqualsAndHashCode.Exclude
    private Rezerwacja rezerwacja;
}
