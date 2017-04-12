package nl.my.laps.models;

public class LapMoment {
    private Integer lapNumber;

    private Double time;

    private Integer kart;

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
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


/*
* Race
*   Session (datum, tijd, plaats, etc)
*       Drivers (naam, plaats, leeftijd, ervaring, kart)
    *       Kart (cc, banden, etc.)
*       Laps (drivers)
* */