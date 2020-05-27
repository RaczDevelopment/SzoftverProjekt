package logic;

import model.CareTaking;
import model.Statistics;

import java.text.DecimalFormat;
import java.util.*;

/**
 * This is the main logic of the program.
 * Calculate how much does a person pay in the given time interval
 * and also give the received number in percentage.
 */
public class Logic {

    private List<CareTaking> filteredList;

    /**
     * This is the constructor, you must give it a list
     * to calculate the payment/person.
     * @param filteredList
     */
    public Logic(List<CareTaking> filteredList){
        this.filteredList = filteredList;
    }

    /**
     * Creates a list of statistic objects.
     * @return List of statistics object.
     */
    public ArrayList<Statistics> paymentPerPerson(){

        ArrayList<Statistics> finalList = new ArrayList<>();
        List<String> names = new ArrayList<>();
        DecimalFormat f = new DecimalFormat("##,00");

        Double sum = sumPayment(filteredList);

        for(CareTaking a : filteredList){

            if(!names.contains(a.getElderName())) {
                Double sumPerPerson = Double.parseDouble(f.format(sumPaymentPerPerson(a.getElderName(), filteredList)));

                Statistics stat = new Statistics();
                stat.setName(a.getElderName());
                stat.setSum(sumPerPerson);
                stat.setPercentage(Double.parseDouble(f.format(sumPerPerson/sum*100)));

                finalList.add(stat);
                names.add(a.getElderName());

            }
        }
       return finalList;
    }

    /**
     * This calculate the sum of the payments/person in the time interval.
     * @param name
     * @param list
     * @return a Double which contains the sum.
     */
    private Double sumPaymentPerPerson(Object name, List<CareTaking> list){
        Double sum = 0.00;
        for (CareTaking a : list) {
            if (a.getElderName().equals(name)) {
                Double n = new Double(a.getPrice());
                sum = sum + n;
            }
        }
        return sum;
    }

    /**
     * This calculate the sum of all payment in the time interval.
     * @param list
     * @return a Double which contains the sum
     */
    private Double sumPayment(List<CareTaking> list){
        Double sum = 0.00;
        for (CareTaking a : list) {
            Double n = new Double(a.getPrice());
            sum = sum + n;
        }
        return sum;
    }
}

