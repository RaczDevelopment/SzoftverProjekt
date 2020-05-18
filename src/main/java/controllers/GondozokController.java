package controllers;

import database.EmployeeController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Dolgozok;
import org.example.App;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GondozokController {

    @FXML
    private Button btnList;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField tfdName;

    @FXML
    private TextField tfdCity;

    @FXML
    private TextField tfdStreet;

    @FXML
    private TextField tfdNumber;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private DatePicker dpStartOfEmployment;

    @FXML
    private TableView<Dolgozok> employees;

    @FXML
    private TableColumn<Dolgozok, Integer> columndID;

    @FXML
    private TableColumn<Dolgozok, String> columndName;

    @FXML
    private TableColumn<Dolgozok, String> columndCity;

    @FXML
    private TableColumn<Dolgozok, String> columndStreet;

    @FXML
    private TableColumn<Dolgozok, String> columndNumber;

    @FXML
    private TableColumn<Dolgozok, LocalDate> columndDateOfBirth;

    @FXML
    private TableColumn<Dolgozok, LocalDate> columndDateOfEmployment;


    public void initColumn(){
        columndID.setCellValueFactory(new PropertyValueFactory<Dolgozok, Integer>("dID"));
        columndName.setCellValueFactory(new PropertyValueFactory<Dolgozok, String>("dName"));
        columndCity.setCellValueFactory(new PropertyValueFactory<Dolgozok, String>("dCity"));
        columndStreet.setCellValueFactory(new PropertyValueFactory<Dolgozok, String>("dStreet"));
        columndNumber.setCellValueFactory(new PropertyValueFactory<Dolgozok, String>("dNumber"));
        columndDateOfBirth.setCellValueFactory(new PropertyValueFactory<Dolgozok, LocalDate>("dDateOfBirth"));
        columndDateOfEmployment.setCellValueFactory(new PropertyValueFactory<Dolgozok, LocalDate>("dDateOfEmployment"));
    }



    @FXML
    void ListData(ActionEvent event) {
        ObservableList<Dolgozok> data = FXCollections.observableArrayList(EmployeeController.getEmployee());
        employees.setItems(data);
        initColumn();
    }





    /*@FXML
    void handelAdd(ActionEvent event) {

        Dolgozok newEmployee = new Dolgozok();

        newEmployee.setdName(tfdName.getText());
        newEmployee.setdCity(tfdCity.getText());
        newEmployee.setdStreet(tfdStreet.getText());
        newEmployee.setdNumber(tfdNumber.getText());
        newEmployee.setdDateOfBirth(dpDateOfBirth.getValue());
        newEmployee.setdStartOfEmployment(dpStartOfEmployment.getValue());
        System.out.println(newEmployee.getdCity());
        //dolgozokDAO.addEmployee(newEmployee);

        tfdName.clear();
        tfdCity.clear();
        tfdStreet.clear();
        tfdNumber.clear();
        dpDateOfBirth.setValue(null);
        dpStartOfEmployment.setValue(null);

    }*/




}