package controllers;

import database.EldersRepository;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import model.Elders;
import org.tinylog.Logger;

import java.time.LocalDate;

public class EldersController {

    @FXML
    private TextField tfSearch;

    @FXML
    private TextField tfName;

    @FXML
    private ComboBox<String> cbGender;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfStreet;

    @FXML
    private TextField tfNumber;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private TextField tfPlaceOfBirth;

    @FXML
    private TextField tfTAJ;

    @FXML
    private DatePicker dpStart;

    @FXML
    private TextField tfType;

    @FXML
    private TableView<Elders> elders;

    @FXML
    private TableColumn<Elders, Integer> columnID;

    @FXML
    private TableColumn<Elders, String> columnName;

    @FXML
    private TableColumn<Elders, String> columnGender;

    @FXML
    private TableColumn<Elders, String> columnCity;

    @FXML
    private TableColumn<Elders, String> columnStreet;

    @FXML
    private TableColumn<Elders, String> columnNumber;

    @FXML
    private TableColumn<Elders, LocalDate> columnDateOfBirth;

    @FXML
    private TableColumn<Elders, String> columnPlaceOfBirth;

    @FXML
    private TableColumn<Elders, Integer> columnTAJ;

    @FXML
    private TableColumn<Elders, LocalDate> columnStart;

    @FXML
    private TableColumn<Elders, String> columnType;

    @FXML
    private TableColumn<Elders, Elders> columnDelete;

    private EldersRepository eldersRepository = new EldersRepository();

    private void initColumn(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        columnStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        columnDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        columnPlaceOfBirth.setCellValueFactory(new PropertyValueFactory<>("placeOfBirth"));
        columnTAJ.setCellValueFactory(new PropertyValueFactory<>("taj"));
        columnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editTableColumns();
    }

    @FXML
    private void handleSearch() {
        try{
            String selectedName = "%" + tfSearch.getText().trim().toLowerCase() + "%";
            tfSearch.clear();
            ObservableList<Elders> data = FXCollections.observableArrayList(eldersRepository.findByName(selectedName));
            elders.setItems(data);
            initColumn();
        } catch (Exception e){
            Logger.error("Search by invalid type");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba üzenet");
            alert.setHeaderText(null);
            alert.setContentText("Érvénytelen típus adat vagy hibás adatbázis kapcsolat.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAdd() {
        try {
            Elders newElder = new Elders();

            newElder.setName(tfName.getText().trim());
            newElder.setGender(cbGender.getValue().trim());
            newElder.setCity(tfCity.getText().trim());
            newElder.setStreet(tfStreet.getText().trim());
            newElder.setNumber(tfNumber.getText().trim());
            newElder.setDateOfBirth(dpDateOfBirth.getValue());
            newElder.setPlaceOfBirth(tfPlaceOfBirth.getText().trim());
            newElder.setTaj(Integer.parseInt(tfTAJ.getText()));
            newElder.setStart(dpStart.getValue());
            newElder.setType(tfType.getText().trim());

            tfName.clear();
            cbGender.setValue(null);
            tfCity.clear();
            tfStreet.clear();
            tfNumber.clear();
            dpDateOfBirth.setValue(null);
            tfPlaceOfBirth.clear();
            tfTAJ.clear();
            dpStart.setValue(null);
            tfType.clear();

            eldersRepository.insertElder(newElder);
        } catch (Exception e){
            Logger.error("Inserting invalid type");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba üzenet");
            alert.setHeaderText(null);
            alert.setContentText("Érvénytelen típus adat vagy hibás adatbázis kapcsolat.");
            alert.showAndWait();
        }
    }

    private void editTableColumns (){
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(expStringCellEditEvent -> {
            Elders tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setName(expStringCellEditEvent.getNewValue());
            eldersRepository.commitChange(tmp);
        });

        columnGender.setCellFactory(TextFieldTableCell.forTableColumn());
        columnGender.setOnEditCommit(expStringCellEditEvent -> {
            Elders tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setGender(expStringCellEditEvent.getNewValue());
            eldersRepository.commitChange(tmp);

        });

        columnCity.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCity.setOnEditCommit(expStringCellEditEvent -> {
            Elders tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setCity(expStringCellEditEvent.getNewValue());
            eldersRepository.commitChange(tmp);
        });

        columnStreet.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStreet.setOnEditCommit(expStringCellEditEvent -> {
            Elders tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setStreet(expStringCellEditEvent.getNewValue());
            eldersRepository.commitChange(tmp);
        });

        columnNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNumber.setOnEditCommit(expStringCellEditEvent -> {
            Elders tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setNumber(expStringCellEditEvent.getNewValue());
            eldersRepository.commitChange(tmp);
        });

        columnDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDateOfBirth.setOnEditCommit(expLocalDateCellEditEvent -> {
            Elders tmp = expLocalDateCellEditEvent.getTableView().getItems().
                    get(expLocalDateCellEditEvent.getTablePosition().getRow());
            tmp.setDateOfBirth(expLocalDateCellEditEvent.getNewValue());
            eldersRepository.commitChange(tmp);
        });

        columnPlaceOfBirth.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPlaceOfBirth.setOnEditCommit(expStringCellEditEvent -> {
            Elders tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setPlaceOfBirth(expStringCellEditEvent.getNewValue());
            eldersRepository.commitChange(tmp);
        });

        columnTAJ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnTAJ.setOnEditCommit(expStringCellEditEvent -> {
            Elders tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setTaj(expStringCellEditEvent.getNewValue());
            eldersRepository.commitChange(tmp);
        });

        columnStart.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnStart.setOnEditCommit(expLocalDateCellEditEvent -> {
            Elders tmp = expLocalDateCellEditEvent.getTableView().getItems().
                    get(expLocalDateCellEditEvent.getTablePosition().getRow());
            tmp.setStart(expLocalDateCellEditEvent.getNewValue());
            eldersRepository.commitChange(tmp);
        });

        columnType.setCellFactory(TextFieldTableCell.forTableColumn());
        columnType.setOnEditCommit(expStringCellEditEvent -> {
            Elders tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setType(expStringCellEditEvent.getNewValue());
            eldersRepository.commitChange(tmp);
        });

        columnDelete.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Törölés");

            @Override
            protected void updateItem(Elders elders, boolean empty) {
                super.updateItem(elders, empty);
                if (elders == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(actionEvent -> deleteRow(getTableView(), elders)
                );
            }
        });
        elders.setEditable(true);
    }

    private void deleteRow(TableView tableView, Elders elders){
        try {
            tableView.getItems().remove(elders);
            eldersRepository.removeEmployee(elders);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba üzenet");
            alert.setHeaderText(null);
            alert.setContentText("A kijelöltt sor törlése sikertelen.");
            alert.showAndWait();
        }
    }
}
