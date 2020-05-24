package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Gondozottak")
public class Elders {

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

    @Column(name = "Felvetel")
    private LocalDate start;

    private int taj;

    @Column(name = "Szuletesi_Hely")
    private String placeOfBirth;

    @Column(name = "Ellatas_Tipusa")
    private String type;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public LocalDate getStart() { return start; }

    public void setStart(LocalDate start) { this.start = start; }

    public int getTaj() { return taj; }

    public void setTaj(int taj) { this.taj = taj; }

    public String getPlaceOfBirth() { return placeOfBirth; }

    public void setPlaceOfBirth(String placeOfBirth) { this.placeOfBirth = placeOfBirth; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
}
