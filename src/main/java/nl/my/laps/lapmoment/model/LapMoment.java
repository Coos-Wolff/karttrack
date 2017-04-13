package nl.my.laps.lapmoment.model;

public class LapMoment {
    private Integer lapNumber;

    private Double lapMoment;

    private Integer kart;

    public Double getLapMoment() {
        return lapMoment;
    }

    public void setLapMoment(Double lapMoment) {
        this.lapMoment = lapMoment;
    }

    public Integer getLapNumber() {
        return lapNumber;
    }

    public void setLapNumber(Integer number) {
        this.lapNumber = number;
    }

    public Integer getKart() {
        return kart;
    }

    public void setKart(Integer kart) {
        this.kart = kart;
    }
}