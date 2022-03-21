package by.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer placeNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    private Movie movies;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Ticket(Integer placeNumber, Movie movie, User user) {
        this.placeNumber = placeNumber;
        this.movies = movie;
        this.user = user;
    }
}
