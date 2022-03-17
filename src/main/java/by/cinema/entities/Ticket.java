package by.cinema.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer placeNumber;
    @ManyToOne
    private Movie movies;
    @OneToOne(mappedBy = "ticket")
    private User user;

    public Ticket(Integer placeNumber, Movie movie) {
        this.placeNumber = placeNumber;
        this.movies = movie;
    }
}
