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

import model.Employees;
import model.Elders;

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
    private TableColumn<Employees, String> columnGender;

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

    public void initialize() {}

    public void initColumn(){
        columnID.setCellValueFactory(new PropertyValueFactory<Elders, Integer>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Elders, String>("name"));
        columnGender.setCellValueFactory(new PropertyValueFactory<Employees, String>("gender"));
        columnCity.setCellValueFactory(new PropertyValueFactory<Elders, String>("city"));
        columnStreet.setCellValueFactory(new PropertyValueFactory<Elders, String>("street"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<Elders, String>("number"));
        columnDateOfBirth.setCellValueFactory(new PropertyValueFactory<Elders, LocalDate>("dateOfBirth"));
        columnPlaceOfBirth.setCellValueFactory(new PropertyValueFactory<Elders, String>("placeOfBirth"));
        columnTAJ.setCellValueFactory(new PropertyValueFactory<Elders, Integer>("taj"));
        columnStart.setCellValueFactory(new PropertyValueFactory<Elders, LocalDate>("start"));
        columnType.setCellValueFactory(new PropertyValueFactory<Elders, String>("type"));
        columnDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editTableColumns();
    }

    @FXML
    public void handleSearch(ActionEvent event) {
        String selectedName = "%" + tfSearch.getText().trim().toLowerCase() + "%";
        tfSearch.clear();
        ObservableList<Elders> data = FXCollections.observableArrayList(EldersRepository.findByName(selectedName));
        elders.setItems(data);
        initColumn();
    }

    @FXML
    public void handleAdd(ActionEvent event) {
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

        EldersRepository.insertElder(newElder);
    }

    public void editTableColumns (){
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Elders, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Elders, String> expStringCellEditEvent) {
                Elders tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setName(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnGender.setCellFactory(TextFieldTableCell.forTableColumn());
        columnGender.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> expStringCellEditEvent) {
                Employees tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setGender(expStringCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);

            }
        });

        columnCity.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Elders, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Elders, String> expStringCellEditEvent) {
                Elders tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setCity(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnStreet.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStreet.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Elders, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Elders, String> expStringCellEditEvent) {
                Elders tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setStreet(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Elders, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Elders, String> expStringCellEditEvent) {
                Elders tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setNumber(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDateOfBirth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Elders, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Elders, LocalDate> expLocalDateCellEditEvent) {
                Elders tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setDateOfBirth(expLocalDateCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnPlaceOfBirth.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPlaceOfBirth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Elders, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Elders, String> expStringCellEditEvent) {
                Elders tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setPlaceOfBirth(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnTAJ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnTAJ.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Elders, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Elders, Integer> expStringCellEditEvent) {
                Elders tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setTaj(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnStart.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnStart.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Elders, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Elders, LocalDate> expLocalDateCellEditEvent) {
                Elders tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setStart(expLocalDateCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnType.setCellFactory(TextFieldTableCell.forTableColumn());
        columnType.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Elders, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Elders, String> expStringCellEditEvent) {
                Elders tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setType(expStringCellEditEvent.getNewValue());
                EldersRepository.commitChange(tmp);
            }
        });

        columnDelete.setCellFactory(param -> new TableCell<Elders, Elders>(){
            private final Button deleteButton = new Button("Törölés");
            @Override
            protected void updateItem(Elders elders, boolean empty){
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

    private void deleteRow(TableView tableView, Elders elders){
        try {
            tableView.getItems().remove(elders);
            EldersRepository.removeEmployee(elders);
        }catch (Exception e){
        }
    }
}
