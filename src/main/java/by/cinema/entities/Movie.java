package by.cinema.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name_Movie;
    @Column
    private String dates;
    @Column
    private Integer price;
    @Column
    private Integer seats;
    @ToString.Exclude
    @OneToMany(mappedBy = "movies")
    private Set<Ticket> tickets;
}
