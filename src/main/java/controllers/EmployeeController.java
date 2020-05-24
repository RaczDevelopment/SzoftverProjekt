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
import model.Employees;

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
    private TableView<Employees> employees;

    @FXML
    private TableColumn<Employees, Integer> columnID;

    @FXML
    private TableColumn<Employees, String> columnName;

    @FXML
    private TableColumn<Employees, String> columnGender;

    @FXML
    private TableColumn<Employees, String> columnCity;

    @FXML
    private TableColumn<Employees, String> columnStreet;

    @FXML
    private TableColumn<Employees, String> columnNumber;

    @FXML
    private TableColumn<Employees, LocalDate> columnDateOfBirth;

    @FXML
    private TableColumn<Employees, LocalDate> columnDateOfEmployment;

    @FXML
    private TableColumn<Employees, Employees> columnDelete;

    public void initialize() {}

    public void initColumn(){
        columnID.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Employees, String>("name"));
        columnGender.setCellValueFactory(new PropertyValueFactory<Employees, String>("gender"));
        columnCity.setCellValueFactory(new PropertyValueFactory<Employees, String>("city"));
        columnStreet.setCellValueFactory(new PropertyValueFactory<Employees, String>("street"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<Employees, String>("number"));
        columnDateOfBirth.setCellValueFactory(new PropertyValueFactory<Employees, LocalDate>("dateOfBirth"));
        columnDateOfEmployment.setCellValueFactory(new PropertyValueFactory<Employees, LocalDate>("startOfEmployment"));
        columnDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editTableColumns();
    }

    @FXML
    public void handleSearch(ActionEvent event) {
        String selectedName = "%" + tfSearch.getText().trim().toLowerCase() + "%";
        tfSearch.clear();
        ObservableList<Employees> data = FXCollections.observableArrayList(EmployeeRepository.findByName(selectedName));
        employees.setItems(data);
        initColumn();
    }

    @FXML
    public void handleAdd(ActionEvent event) {
        Employees newEmployee = new Employees();

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
        columnName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> expStringCellEditEvent) {
                Employees tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setName(expStringCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);

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
        columnCity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> expStringCellEditEvent) {
                Employees tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setCity(expStringCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);

            }
        });

        columnStreet.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStreet.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> expStringCellEditEvent) {
                Employees tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setStreet(expStringCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);

            }
        });

        columnNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> expStringCellEditEvent) {
                Employees tmp = expStringCellEditEvent.getTableView().getItems().
                        get(expStringCellEditEvent.getTablePosition().getRow());
                tmp.setNumber(expStringCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);

            }
        });

        columnDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDateOfBirth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, LocalDate> expLocalDateCellEditEvent) {
                Employees tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setDateOfBirth(expLocalDateCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);
            }
        });

        columnDateOfEmployment.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDateOfEmployment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, LocalDate> expLocalDateCellEditEvent) {
                Employees tmp = expLocalDateCellEditEvent.getTableView().getItems().
                        get(expLocalDateCellEditEvent.getTablePosition().getRow());
                tmp.setStartOfEmployment(expLocalDateCellEditEvent.getNewValue());
                EmployeeRepository.commitChange(tmp);
            }
        });

        columnDelete.setCellFactory(param -> new TableCell<Employees, Employees>(){
            private final Button deleteButton = new Button("Törölés");
            @Override
            protected void updateItem(Employees employee, boolean empty){
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

    private void deleteRow(TableView tableView, Employees employee){
        try {
            tableView.getItems().remove(employee);
            EmployeeRepository.removeEmployee(employee);
        }catch (Exception e){
        }
    }
}