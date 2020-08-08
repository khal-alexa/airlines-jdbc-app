package domain;

import java.time.LocalDate;

public class Airplane {
    private int id;
    private String codeName;
    private String model;
    private LocalDate manufactureDate;
    private double capacity;
    private double flightRange;

    public Airplane(int id, String codeName, String model, LocalDate manufactureDate, double capacity, double flightRange) {
        this.id = id;
        this.codeName = codeName;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.capacity = capacity;
        this.flightRange = flightRange;
    }

    public Airplane(String codeName, String model, LocalDate manufactureDate, double capacity, double flightRange) {
        this.codeName = codeName;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.capacity = capacity;
        this.flightRange = flightRange;
    }

    public int getId() {
        return id;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFlightRange() {
        return flightRange;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setFlightRange(double flightRange) {
        this.flightRange = flightRange;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", codeName='" + codeName + '\'' +
                ", model='" + model + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", capacity=" + capacity +
                ", flightRange=" + flightRange +
                '}';
    }
}
