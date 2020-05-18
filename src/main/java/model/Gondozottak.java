package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Gondozottak {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer gID;

    @Column(name = "Nev")
    private String gName;

    @Column(name = "Varos")
    private String gCity;

    @Column(name = "Utca")
    private String gStreet;

    @Column(name = "Szam")
    private String gNumber;

    @Column(name = "Szuletesi_Datum")
    private LocalDate gDateOfBirth;

    @Column(name = "Felvetel")
    private LocalDate gStart;

    private int taj;

    @Column(name = "Szuletesi_Hely")
    private String gPlaceOfBirth;

    @Column(name = "Ellatas_Tipusa")
    private String type;

}
