package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains name, sum and percentage.
 * Datatype to store sum of payment per person.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

    private String name;
    private Double sum;
    private Double percentage;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getSum() { return sum; }

    public void setSum(Double sum) { this.sum = sum; }

    public Double getPercentage() { return percentage; }

    public void setPercentage(Double percentage) { this.percentage = percentage; }

    @Override
    public String toString() {
        return "Statistics{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                ", percentage=" + percentage +
                '}';
    }
}
