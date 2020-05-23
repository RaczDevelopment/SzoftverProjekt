package controllers;

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

import model.Dolgozok;
import model.Gondozottak;

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
    private TableView<Gondozottak> elders;

    @FXML
    private TableColumn<Gondozottak, Integer> columnID;

    @FXML
    private TableColumn<Gondozottak, String> columnName;

    @FXML
    private TableColumn<Dolgozok, String> columnGender;

    @FXML
    private TableColumn<Gondozottak, String> columnCity;

    @FXML
    private TableColumn<Gondozottak, String> columnStreet;

    @FXML
    private TableColumn<Gondozottak, String> columnNumber;

    @FXML
    private TableColumn<Gondozottak, LocalDate> columnDateOfBirth;

    @FXML
    private TableColumn<Gondozottak, String> columnPlaceOfBirth;

    @FXML
    private TableColumn<Gondozottak, Integer> columnTAJ;

    @FXML
    private TableColumn<Gondozottak, LocalDate> columnStart;

    @FXML
    private TableColumn<Gondozottak, String> columnType;

    @FXML
    private TableColumn<Gondozottak, Gondozottak> columnDelete;

    public void initialize() {}

    public void initColumn(){
        columnID.setCellValueFactory(new PropertyValueFactory<Gondozottak, Integer>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Gondozottak, String>("name"));
        columnGender.setCellValueFactory(new PropertyValueFactory<Dolgozok, String>("gender"));
        columnCity.setCellValueFactory(new PropertyValueFactory<Gondozottak, String>("city"));
        columnStreet.setCellValueFactory(new PropertyValueFactory<Gondozottak, String>("street"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<Gondozottak, String>("number"));
        columnDateOfBirth.setCellValueFactory(new PropertyValueFactory<Gondozottak, LocalDate>("dateOfBirth"));
        columnPlaceOfBirth.setCellValueFactory(new PropertyValueFactory<Gondozottak, String>("placeOfBirth"));
        columnTAJ.setCellValueFactory(new PropertyValueFactory<Gondozottak, Integer>("taj"));
        columnStart.setCellValueFactory(new PropertyValueFactory<Gondozottak, LocalDate>("start"));
        columnType.setCellValueFactory(new PropertyValueFactory<Gondozottak, String>("type"));
        columnDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editTableColumns();
    }

    @FXML
    public void handleSearch(ActionEvent event) {
        String selectedName = "%" + tfSearch.getText().trim().toLowerCase() + "%";
        tfSearch.clear();
        ObservableList<Gondozottak> data = FXCollections.observableArrayList(EldersRepository.findByName(selectedName));
        elders.setItems(data);
        initColumn();
    }

    @FXML
    public void handleAdd(ActionEvent event) {
        Gondozottak newElder = new Gondozottak();

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

        EldersRepository.insertElder(newElder);
    }

    public void editTableColumns (){
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gondozottak, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Gondozottak, String> expStringCellEditEvent) {
                Gondozottak tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setName(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnGender.setCellFactory(TextFieldTableCell.forTableColumn());
        columnGender.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Dolgozok, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Dolgozok, String> expStringCellEditEvent) {
                Dolgozok tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setGender(expStringCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);

            }
        });

        columnCity.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gondozottak, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Gondozottak, String> expStringCellEditEvent) {
                Gondozottak tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setCity(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnStreet.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStreet.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gondozottak, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Gondozottak, String> expStringCellEditEvent) {
                Gondozottak tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setStreet(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gondozottak, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Gondozottak, String> expStringCellEditEvent) {
                Gondozottak tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setNumber(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDateOfBirth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gondozottak, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Gondozottak, LocalDate> expLocalDateCellEditEvent) {
                Gondozottak tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setDateOfBirth(expLocalDateCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnPlaceOfBirth.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPlaceOfBirth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gondozottak, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Gondozottak, String> expStringCellEditEvent) {
                Gondozottak tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setPlaceOfBirth(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnTAJ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnTAJ.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gondozottak, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Gondozottak, Integer> expStringCellEditEvent) {
                Gondozottak tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setTaj(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnStart.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnStart.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gondozottak, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Gondozottak, LocalDate> expLocalDateCellEditEvent) {
                Gondozottak tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setStart(expLocalDateCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnType.setCellFactory(TextFieldTableCell.forTableColumn());
        columnType.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gondozottak, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Gondozottak, String> expStringCellEditEvent) {
                Gondozottak tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setType(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnDelete.setCellFactory(param -> new TableCell<Gondozottak,Gondozottak>(){
            private final Button deleteButton = new Button("Törölés");
            @Override
            protected void updateItem(Gondozottak elders, boolean empty){
                super.updateItem(elders, empty);
                if(elders == null){
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

    private void deleteRow(TableView tableView, Gondozottak elders){
        try {
            tableView.getItems().remove(elders);
            EldersRepository.removeEmployee(elders);
        }catch (Exception e){
        }
    }
}
