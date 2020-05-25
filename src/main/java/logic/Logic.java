package logic;


import database.LogicRepository;

import javafx.collections.ObservableList;
import model.CareTaking;
import model.Statistics;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class Logic {

    public static List<Statistics> paymentPerPerson(LocalDate fromWhatTime, LocalDate howLong){
        List<CareTaking> filterdList = betweenTwoDate(LogicRepository.findPersons(), fromWhatTime, howLong);
        List<Statistics> finalList = new ArrayList<>();
        List<String> names = new ArrayList<>();

        Double sum = sumPayment(filterdList);

        for(CareTaking a : filterdList){

            if(!names.contains(a.getElderName())) {
                Double sumPerPerson = sumPaymentPerPerson(a.getElderName(), filterdList);

                Statistics stat = new Statistics();
                stat.setName(a.getElderName());
                stat.setSum(sumPerPerson);
                stat.setPercentage((sumPerPerson/sum)*100);

                finalList.add(stat);
                names.add(a.getElderName());

            }
        }

       return finalList;
    }

    public static List<CareTaking> betweenTwoDate(List<CareTaking> list, LocalDate fromWhatTime, LocalDate howLong){
        List<CareTaking> filteredList = new ArrayList<>();
        for (CareTaking a : list) {
            if((fromWhatTime.isBefore(a.getDate()) || fromWhatTime.equals(a.getDate()))
                    && (howLong.isAfter(a.getDate()) || howLong.equals(a.getDate()))) {
                    filteredList.add(a);
            }
        }
        return filteredList;
    }

    public static Double sumPaymentPerPerson(Object name, List<CareTaking> list){
        Double sum = 0.00;
        for (CareTaking a : list) {
            if (a.getElderName().equals(name)) {
                Double n = new Double(a.getPrice());
                sum = sum + n;
            }
        }
        return sum;
    }

    public static Double sumPayment(List<CareTaking> list){
        Double sum = 0.00;
        for (CareTaking a : list) {
            Double n = new Double(a.getPrice());
            sum = sum + n;
        }
        return sum;
    }
}

