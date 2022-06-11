package by.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @OneToMany(mappedBy = "movies", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Ticket> tickets;

    public Movie(Long id, String name_Movie, String dates, Integer price, Integer free_places) {
        this.id = id;
        this.name_Movie = name_Movie;
        this.dates = dates;
        this.price = price;
        this.free_places = free_places;
    }
}
