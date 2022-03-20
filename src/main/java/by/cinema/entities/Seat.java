package by.cinema.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer place;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movies;

    public Seat(Integer place) {
        this.place = place;
    }

    public Seat(Integer place, Movie movies) {
        this.place = place;
        this.movies = movies;
    }
}
