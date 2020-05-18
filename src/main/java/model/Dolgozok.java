package model;

import javax.persistence.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.*;

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
    private Integer dID;

    @Column(name = "Nev")
    private String dName;

    @Column(name = "Varos")
    private String dCity;

    @Column(name = "Utca")
    private String dStreet;

    @Column(name = "Szam")
    private String dNumber;

    @Column(name = "Szuletesi_Datum")
    private LocalDate dDateOfBirth;

    @Column(name = "Alk_Datum")
    private LocalDate dStartOfEmployment;

    public Integer getdID() {
        return dID;
    }

    public void setdID(Integer dID) {
        this.dID = dID;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdCity() {
        return dCity;
    }

    public void setdCity(String dCity) {
        this.dCity = dCity;
    }

    public String getdStreet() {
        return dStreet;
    }

    public void setdStreet(String dStreet) {
        this.dStreet = dStreet;
    }

    public String getdNumber() {
        return dNumber;
    }

    public void setdNumber(String dNumber) {
        this.dNumber = dNumber;
    }

    public LocalDate getdDateOfBirth() {
        return dDateOfBirth;
    }

    public void setdDateOfBirth(LocalDate dDateOfBirth) {
        this.dDateOfBirth = dDateOfBirth;
    }

    public LocalDate getdStartOfEmployment() {
        return dStartOfEmployment;
    }

    public void setdStartOfEmployment(LocalDate dStartOfEmployment) {
        this.dStartOfEmployment = dStartOfEmployment;
    }
}
