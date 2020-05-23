package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Gondozasok {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Ellatott_Neve")
    private String elderName;

    @Column(name = "Dolgozo_Neve")
    private String employeeName;

    @Column(name = "Ebed")
    private String lunch;

    @Column(name = "Datum")
    private LocalDate date;

    @Column(name = "Utazasi_Ido")
    private LocalTime travelTime;

    @Column(name = "Gondozasi_Ido")
    private LocalTime careTime;

    @Column(name = "Ebed_ara")
    private Integer price;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getElderName() { return elderName; }

    public void setElderName(String elderName) { this.elderName = elderName; }

    public String getEmployeeName() { return employeeName; }

    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getLunch() { return lunch; }

    public void setLunch(String lunch) { this.lunch = lunch; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTravelTime() { return travelTime; }

    public void setTravelTime(LocalTime travelTime) { this.travelTime = travelTime; }

    public LocalTime getCareTime() { return careTime; }

    public void setCareTime(LocalTime careTime) { this.careTime = careTime; }

    public Integer getPrice() { return price; }

    public void setPrice(Integer price) { this.price = price; }
}
