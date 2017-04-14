package nl.my.laps.race.model;

import nl.my.laps.kart.model.Kart;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "race")
public class Race {

    public Race() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private Set<Kart> karts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Kart> getKarts() {
        return karts;
    }

    public void setKarts(Set<Kart> karts) {
        this.karts = karts;
    }


}
