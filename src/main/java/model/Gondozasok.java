package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Gondozasok {

    @Id
    private Integer gID;

    @Column(name = "D_ID")
    private Integer dID;

    @Column(name = "Ebed")
    private String lunch;

    @Column(name = "Datum")
    private LocalDate date;

    @Column(name = "Utazasi_Ido")
    private LocalTime travelTime;

    @Column(name = "Gondozasi_Ido")
    private LocalTime careTime;

}
