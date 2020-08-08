package domain;

import java.time.LocalDate;

public class Crew {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private LocalDate birthday;
    private String citizenship;

    public Crew(String firstName, String lastName, String position, LocalDate birthday, String citizenship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.birthday = birthday;
        this.citizenship = citizenship;
    }

    public Crew(int id, String firstName, String lastName, String position, LocalDate birthday, String citizenship) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.birthday = birthday;
        this.citizenship = citizenship;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", birthday=" + birthday +
                ", citizenship='" + citizenship + '\'' +
                '}';
    }

}
