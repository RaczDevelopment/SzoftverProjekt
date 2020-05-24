package controllers;

import database.CaretakeRepository;
import database.EldersRepository;
import database.EmployeeRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import model.CareTaking;

import model.Elders;
import model.Employees;
import org.tinylog.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import static java.time.temporal.ChronoUnit.MINUTES;

public class CaretakeController {


    @FXML
    private TextField tfElderName;

    @FXML
    private ComboBox<String> cbEmployeeName;

    @FXML
    private ComboBox<String> cbLunch;

    @FXML
    private ComboBox<String> cbPrice;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField tfCareTime;

    @FXML
    private TextField tfTravelTime;

    @FXML
    private ComboBox<String> cbSearchByColumn;

    @FXML
    private TextField tfSearchByColumn;

    @FXML
    private TableView<CareTaking> caretake;

    @FXML
    private TableColumn<CareTaking, Integer> columnID;

    @FXML
    private TableColumn<CareTaking, String> columnElderName;

    @FXML
    private TableColumn<CareTaking, String> columnEmployeeName;

    @FXML
    private TableColumn<CareTaking, String> columnLunch;

    @FXML
    private TableColumn<CareTaking, Integer> columnPrice;

    @FXML
    private TableColumn<CareTaking, LocalDate> columnDate;

    @FXML
    private TableColumn<CareTaking, LocalTime> columnCareTime;

    @FXML
    private TableColumn<CareTaking, LocalTime> columnCareTimeWithoutTravel;

    @FXML
    private TableColumn<CareTaking, CareTaking> columnDelete;

    public void initialize() {}

    public void initColumn(){
        columnID.setCellValueFactory(new PropertyValueFactory<CareTaking, Integer>("id"));
        columnElderName.setCellValueFactory(new PropertyValueFactory<CareTaking, String>("elderName"));
        columnEmployeeName.setCellValueFactory(new PropertyValueFactory<CareTaking, String>("employeeName"));
        columnLunch.setCellValueFactory(new PropertyValueFactory<CareTaking, String>("lunch"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<CareTaking, Integer>("price"));
        columnDate.setCellValueFactory(new PropertyValueFactory<CareTaking, LocalDate>("date"));
        columnCareTime.setCellValueFactory(new PropertyValueFactory<CareTaking, LocalTime>("careTime"));
        columnCareTimeWithoutTravel.setCellValueFactory(new PropertyValueFactory<CareTaking, LocalTime>("travelTime"));
        columnDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editTableColumns();
    }

    @FXML
    public void handleAdd(ActionEvent event) {
        try {
            CareTaking newCareTake = new CareTaking();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

            newCareTake.setElderName(tfElderName.getText().trim());
            newCareTake.setEmployeeName(cbEmployeeName.getValue().trim());
            newCareTake.setLunch(cbLunch.getValue().trim());
            newCareTake.setPrice(Integer.parseInt(cbPrice.getValue().trim()));
            newCareTake.setDate(dpDate.getValue());
            newCareTake.setCareTime(LocalTime.parse(tfCareTime.getText().trim(), dtf));
            newCareTake.setCareTimeWithoutTravel(LocalTime.parse(tfCareTime.getText().trim(), dtf)
                    .minus(Integer.parseInt(tfTravelTime.getText()), MINUTES));

            cbEmployeeName.getItems().addAll(cbEmployeeName.getValue().trim());
            cbLunch.getItems().addAll(cbLunch.getValue().trim());
            cbPrice.getItems().addAll(cbPrice.getValue().trim());

            tfElderName.clear();
            tfCareTime.clear();

            CaretakeRepository.insertCareTake(newCareTake);

        } catch (Exception e){
            Logger.error("Baj van");
        }

    }

    @FXML
    void handleSearch(ActionEvent event) {
        try {
            ObservableList<CareTaking> data = FXCollections.observableArrayList(
                    CaretakeRepository.findByColumn(cbSearchByColumn.getValue().trim(),
                            tfSearchByColumn.getText().trim()));
            tfSearchByColumn.clear();
            caretake.setItems(data);
            initColumn();
        }catch (Exception e){

        }
    }

    public void editTableColumns (){
        columnElderName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnElderName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CareTaking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CareTaking, String> expStringCellEditEvent) {
                CareTaking tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setElderName(expStringCellEditEvent.getNewValue());
                CaretakeRepository.commitChange(tmp);
            }
        });

        columnEmployeeName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEmployeeName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CareTaking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CareTaking, String> expStringCellEditEvent) {
                CareTaking tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setEmployeeName(expStringCellEditEvent.getNewValue());
                CaretakeRepository.commitChange(tmp);
            }
        });

        columnLunch.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLunch.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CareTaking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CareTaking, String> expStringCellEditEvent) {
                CareTaking tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setLunch(expStringCellEditEvent.getNewValue());
                CaretakeRepository.commitChange(tmp);
            }
        });

        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnPrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CareTaking, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CareTaking, Integer> expStringCellEditEvent) {
                CareTaking tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setPrice(expStringCellEditEvent.getNewValue());
                CaretakeRepository.commitChange(tmp);
            }
        });

        columnDate.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CareTaking, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CareTaking, LocalDate> expLocalDateCellEditEvent) {
                CareTaking tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setDate(expLocalDateCellEditEvent.getNewValue());
                CaretakeRepository.commitChange(tmp);
            }
        });

        columnCareTime.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
        columnCareTime.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CareTaking, LocalTime>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CareTaking, LocalTime> expLocalDateCellEditEvent) {
                CareTaking tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setCareTime(expLocalDateCellEditEvent.getNewValue());
                CaretakeRepository.commitChange(tmp);
            }
        });

        columnCareTimeWithoutTravel.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
        columnCareTimeWithoutTravel.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CareTaking, LocalTime>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CareTaking, LocalTime> expLocalDateCellEditEvent) {
                CareTaking tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setCareTimeWithoutTravel(expLocalDateCellEditEvent.getNewValue());
                CaretakeRepository.commitChange(tmp);
            }
        });

        columnDelete.setCellFactory(param -> new TableCell<CareTaking, CareTaking>(){
            private final Button deleteButton = new Button("Törölés");
            @Override
            protected void updateItem(CareTaking caretake, boolean empty){
                super.updateItem(caretake, empty);
                if(caretake == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(actionEvent -> deleteRow(getTableView(), caretake)
                );
            }
        });
        caretake.setEditable(true);
    }

    private void deleteRow(TableView tableView, CareTaking careTake){
        try {
            tableView.getItems().remove(careTake);
            CaretakeRepository.removeCareTake(careTake);
        }catch (Exception e){
        }
    }

}
