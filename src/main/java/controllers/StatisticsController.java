package controllers;

import database.CaretakeRepository;
import database.LogicRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Statistics;

import org.tinylog.Logger;

import static logic.Logic.paymentPerPerson;

public class StatisticsController {
    @FXML
    private DatePicker dpFromWhatTime;

    @FXML
    private DatePicker dpHowLong;

    @FXML
    private TableView<Statistics> statistics;

    @FXML
    private TableColumn<Statistics,String> columnElderName;

    @FXML
    private TableColumn<Statistics, Double> columnSum;

    @FXML
    private TableColumn<Statistics, Double> columnPercentage;

    @FXML
    private PieChart pcStatistic;



    public void initialize() {}

    public void initColumn(){

        columnElderName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        columnPercentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
    }

    public void handleSearch() {
        try {
            ObservableList<Statistics> data = FXCollections.observableArrayList(paymentPerPerson(dpFromWhatTime.getValue(),dpHowLong.getValue()));
            pcStatistic.getData().clear();
            for(Statistics a : data){
                PieChart.Data tmp = new PieChart.Data(
                        a.getName(), a.getPercentage()
                );
                pcStatistic.getData().add(tmp);
            }

            statistics.setItems(data);
            initColumn();
        }catch (Exception e){
            Logger.error("Search by invalid type");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba üzenet");
            alert.setHeaderText(null);
            alert.setContentText("Érvénytelen típus adat vagy hibás adatbázis kapcsolat.");
            alert.showAndWait();
        }
    }
}

