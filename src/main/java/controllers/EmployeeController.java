package controllers;

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
import javafx.util.converter.LocalDateStringConverter;
import model.Dolgozok;
import org.tinylog.Logger;

import java.time.LocalDate;


public class EmployeeController {

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
    private DatePicker dpStartOfEmployment;

    @FXML
    private TableView<Dolgozok> employees;

    @FXML
    private TableColumn<Dolgozok, Integer> columnID;

    @FXML
    private TableColumn<Dolgozok, String> columnName;

    @FXML
    private TableColumn<Dolgozok, String> columnGender;

    @FXML
    private TableColumn<Dolgozok, String> columnCity;

    @FXML
    private TableColumn<Dolgozok, String> columnStreet;

    @FXML
    private TableColumn<Dolgozok, String> columnNumber;

    @FXML
    private TableColumn<Dolgozok, LocalDate> columnDateOfBirth;

    @FXML
    private TableColumn<Dolgozok, LocalDate> columnDateOfEmployment;

    @FXML
    private TableColumn<Dolgozok, Dolgozok> columnDelete;

    public void initialize() {}

    public void initColumn(){
        columnID.setCellValueFactory(new PropertyValueFactory<Dolgozok, Integer>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Dolgozok, String>("name"));
        columnGender.setCellValueFactory(new PropertyValueFactory<Dolgozok, String>("gender"));
        columnCity.setCellValueFactory(new PropertyValueFactory<Dolgozok, String>("city"));
        columnStreet.setCellValueFactory(new PropertyValueFactory<Dolgozok, String>("street"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<Dolgozok, String>("number"));
        columnDateOfBirth.setCellValueFactory(new PropertyValueFactory<Dolgozok, LocalDate>("dateOfBirth"));
        columnDateOfEmployment.setCellValueFactory(new PropertyValueFactory<Dolgozok, LocalDate>("startOfEmployment"));
        columnDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editTableColumns();
    }

    @FXML
    public void handleSearch(ActionEvent event) {
        String selectedName = "%" + tfSearch.getText().trim().toLowerCase() + "%";
        tfSearch.clear();
        ObservableList<Dolgozok> data = FXCollections.observableArrayList(EmployeeRepository.findByName(selectedName));
        employees.setItems(data);
        initColumn();
    }

    @FXML
    public void handleAdd(ActionEvent event) {
        Dolgozok newEmployee = new Dolgozok();

        newEmployee.setName(tfName.getText().trim());
        newEmployee.setGender(cbGender.getValue().trim());
        newEmployee.setCity(tfCity.getText().trim());
        newEmployee.setStreet(tfStreet.getText().trim());
        newEmployee.setNumber(tfNumber.getText().trim());
        newEmployee.setDateOfBirth(dpDateOfBirth.getValue());
        newEmployee.setStartOfEmployment(dpStartOfEmployment.getValue());

        tfName.clear();
        cbGender.setValue(null);
        tfCity.clear();
        tfStreet.clear();
        tfNumber.clear();
        dpDateOfBirth.setValue(null);
        dpStartOfEmployment.setValue(null);

        EmployeeRepository.insertEmployee(newEmployee);
    }

    public void editTableColumns (){
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Dolgozok, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Dolgozok, String> expStringCellEditEvent) {
                Dolgozok tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setName(expStringCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);

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
        columnCity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Dolgozok, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Dolgozok, String> expStringCellEditEvent) {
                Dolgozok tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setCity(expStringCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);

            }
        });

        columnStreet.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStreet.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Dolgozok, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Dolgozok, String> expStringCellEditEvent) {
                Dolgozok tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setStreet(expStringCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);

            }
        });

        columnNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Dolgozok, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Dolgozok, String> expStringCellEditEvent) {
                Dolgozok tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setNumber(expStringCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);

            }
        });

        columnDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDateOfBirth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Dolgozok, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Dolgozok, LocalDate> expLocalDateCellEditEvent) {
                Dolgozok tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setDateOfBirth(expLocalDateCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);
            }
        });

        columnDateOfEmployment.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDateOfEmployment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Dolgozok, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Dolgozok, LocalDate> expLocalDateCellEditEvent) {
                Dolgozok tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setStartOfEmployment(expLocalDateCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);
            }
        });

        columnDelete.setCellFactory(param -> new TableCell<Dolgozok,Dolgozok>(){
            private final Button deleteButton = new Button("Törölés");
            @Override
            protected void updateItem(Dolgozok employee, boolean empty){
                super.updateItem(employee, empty);
                if(employee == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(actionEvent -> deleteRow(getTableView(), employee)
                );
            }
        });
        employees.setEditable(true);
    }

    private void deleteRow(TableView tableView,Dolgozok employee){
        try {
            tableView.getItems().remove(employee);
            EmployeeRepository.removeEmployee(employee);
        }catch (Exception e){
        }
    }
}