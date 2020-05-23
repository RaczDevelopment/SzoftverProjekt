package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Dolgozok")
public class Dolgozok {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Nev")
    private String name;

    @Column(name = "Nem")
    private String gender;

    @Column(name = "Varos")
    private String city;

    @Column(name = "Utca")
    private String street;

    @Column(name = "Szam")
    private String number;

    @Column(name = "Szuletesi_Datum")
    private LocalDate dateOfBirth;

    @Column(name = "Alk_Datum")
    private LocalDate startOfEmployment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getStartOfEmployment() {
        return startOfEmployment;
    }

    public void setStartOfEmployment(LocalDate startOfEmployment) {
        this.startOfEmployment = startOfEmployment;
    }
}
