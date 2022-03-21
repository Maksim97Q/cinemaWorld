package by.cinema.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
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
    private Integer free_places;
    @OneToMany(mappedBy = "movies", cascade = CascadeType.ALL)
    private Set<Seat> seats;
    @OneToMany(mappedBy = "movies", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Ticket> tickets;
}
