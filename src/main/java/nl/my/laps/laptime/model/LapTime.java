package nl.my.laps.laptime.model;

import nl.my.laps.kart.model.Kart;

import javax.persistence.*;

@Entity
@Table(name = "lap_time")
public class LapTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_lap")
    private Double timeLap;

    @Column(name = "lap_number")
    private Integer labNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kart_id", nullable = false)
    private Kart kart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTimeLap() {
        return timeLap;
    }

    public void setTimeLap(Double timeLap) {
        this.timeLap = timeLap;
    }

    public Integer getLabNumber() {
        return labNumber;
    }

    public void setLabNumber(Integer labNumber) {
        this.labNumber = labNumber;
    }

    public Kart getKart() {
        return kart;
    }

    public void setKart(Kart kart) {
        this.kart = kart;
    }
}
