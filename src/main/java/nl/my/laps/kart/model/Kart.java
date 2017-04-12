package nl.my.laps.kart.model;

import nl.my.laps.laptime.model.LapTime;
import nl.my.laps.race.model.Race;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "kart")
public class Kart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "kart_number")
    private Integer kartNumber;

    @OneToMany(mappedBy = "kart")
    private Set<LapTime> lapTimes;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKartNumber() {
        return kartNumber;
    }

    public void setKartNumber(Integer kartNumber) {
        this.kartNumber = kartNumber;
    }

    public Set<LapTime> getLapTimes() {
        return lapTimes;
    }

    public void setLapTimes(Set<LapTime> lapTimes) {
        this.lapTimes = lapTimes;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
