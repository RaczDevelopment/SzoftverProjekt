package controllers;

import database.StatisticsRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import logic.Logic;
import model.Statistics;

import org.tinylog.Logger;

public class StatisticsController {
    @FXML
    private Label lblSumElder;

    @FXML
    private Label lblSumEmployee;

    @FXML
    private PieChart pcStatistic;

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

    private StatisticsRepository statisticsRepository = new StatisticsRepository();

    public void initialize(){
        lblSumElder.setText(statisticsRepository.sumElder());
        lblSumEmployee.setText(statisticsRepository.sumEmployee());
    }

    public void initColumn(){
        columnElderName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        columnPercentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
    }

    public void handleSearch() {
        try {
            Logic logic = new Logic(statisticsRepository.findPersons(dpFromWhatTime.getValue(),dpHowLong.getValue()));
            ObservableList<Statistics> data = FXCollections.observableArrayList(logic.paymentPerPerson());
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

