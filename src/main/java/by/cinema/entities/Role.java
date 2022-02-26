package by.cinema.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}