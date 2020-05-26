package logic;

import model.CareTaking;
import model.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    private static ArrayList<CareTaking> testList = new ArrayList<>();
    private Logic logicOnTest;

    @BeforeEach
    public void setUp(){
        testList.add(new CareTaking(1 ,
                "Joska" ,
                "Pista",
                "Ebed",
                LocalDate.of(2020, 05, 13),
                LocalTime.of(12,45),
                LocalTime.of(12,40),
                20));
        testList.add(new CareTaking(1 ,
                "Mari" ,
                "Juli",
                "Burger King",
                LocalDate.of(2020, 05, 13),
                LocalTime.of(12,45),
                LocalTime.of(12,40),
                40));
        testList.add(new CareTaking(1 ,
                "Abzu" ,
                "Mabzu",
                "Mekdanelc",
                LocalDate.of(2020, 05, 13),
                LocalTime.of(12,45),
                LocalTime.of(12,40),
                40));

        logicOnTest = new Logic(testList);
    }

    @AfterEach
    public void tearDown(){
        testList.clear();
    }

    @Test
    public void Testeeeee(){
        ArrayList<Statistics> expectedList = new ArrayList<>();
        expectedList.add(new Statistics("Joska", 20.00, 20.00));
        expectedList.add(new Statistics("Mari", 40.00, 40.00));
        expectedList.add(new Statistics("Abzu", 40.00, 40.00));

        ArrayList<Statistics> actualList = logicOnTest.paymentPerPerson();
        assertEquals(expectedList,actualList);
    }

}
