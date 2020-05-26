package controllers;

import database.CaretakersRepository;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import model.CareTaking;

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

    private CaretakersRepository caretakersRepository = new CaretakersRepository();

    private void initColumn(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnElderName.setCellValueFactory(new PropertyValueFactory<>("elderName"));
        columnEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        columnLunch.setCellValueFactory(new PropertyValueFactory<>("lunch"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnCareTime.setCellValueFactory(new PropertyValueFactory<>("careTime"));
        columnCareTimeWithoutTravel.setCellValueFactory(new PropertyValueFactory<>("travelTime"));
        columnDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editTableColumns();
    }

    @FXML
    private void handleAdd() {
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

            caretakersRepository.insertCareTake(newCareTake);
        } catch (Exception e){
            Logger.error("Inserting invalid type");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba üzenet");
            alert.setHeaderText(null);
            alert.setContentText("Érvénytelen típus adat vagy hibás adatbázis kapcsolat.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSearch() {
        try {
            ObservableList<CareTaking> data = FXCollections.observableArrayList(
                    caretakersRepository.findByColumn(cbSearchByColumn.getValue().trim(),
                            tfSearchByColumn.getText().trim()));
            tfSearchByColumn.clear();
            caretake.setItems(data);
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

    private void editTableColumns (){


        columnElderName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnElderName.setOnEditCommit(expStringCellEditEvent -> {
            CareTaking tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setElderName(expStringCellEditEvent.getNewValue());
            caretakersRepository.commitChange(tmp);
        });
        columnEmployeeName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEmployeeName.setOnEditCommit(expStringCellEditEvent -> {
            CareTaking tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setEmployeeName(expStringCellEditEvent.getNewValue());
            caretakersRepository.commitChange(tmp);
        });
        columnLunch.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLunch.setOnEditCommit(expStringCellEditEvent -> {
            CareTaking tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setLunch(expStringCellEditEvent.getNewValue());
            caretakersRepository.commitChange(tmp);
        });
        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnPrice.setOnEditCommit(expStringCellEditEvent -> {
            CareTaking tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setPrice(expStringCellEditEvent.getNewValue());
            caretakersRepository.commitChange(tmp);
        });
        columnDate.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDate.setOnEditCommit(expLocalDateCellEditEvent -> {
            CareTaking tmp = expLocalDateCellEditEvent.getTableView().getItems().
                    get(expLocalDateCellEditEvent.getTablePosition().getRow());
            tmp.setDate(expLocalDateCellEditEvent.getNewValue());
            caretakersRepository.commitChange(tmp);
        });
        columnCareTime.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
        columnCareTime.setOnEditCommit(expLocalDateCellEditEvent -> {
            CareTaking tmp = expLocalDateCellEditEvent.getTableView().getItems().
                    get(expLocalDateCellEditEvent.getTablePosition().getRow());
            tmp.setCareTime(expLocalDateCellEditEvent.getNewValue());
            caretakersRepository.commitChange(tmp);
        });
        columnCareTimeWithoutTravel.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
        columnCareTimeWithoutTravel.setOnEditCommit(expLocalDateCellEditEvent -> {
            CareTaking tmp = expLocalDateCellEditEvent.getTableView().getItems().
                    get(expLocalDateCellEditEvent.getTablePosition().getRow());
            tmp.setCareTimeWithoutTravel(expLocalDateCellEditEvent.getNewValue());
            caretakersRepository.commitChange(tmp);
        });
        columnDelete.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Törölés");

            @Override
            protected void updateItem(CareTaking caretake, boolean empty) {
                super.updateItem(caretake, empty);
                if (caretake == null) {
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
            caretakersRepository.removeCareTake(careTake);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba üzenet");
            alert.setHeaderText(null);
            alert.setContentText("A kijelöltt sor törlése sikertelen.");
            alert.showAndWait();
        }
    }

}
