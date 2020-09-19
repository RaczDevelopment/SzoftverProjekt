package controllers;

import database.EmployeeRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LocalDateStringConverter;
import model.Employees;
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

    private EmployeeRepository employeeRepository = new EmployeeRepository();

    private void initColumn(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        columnStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        columnDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        columnDateOfEmployment.setCellValueFactory(new PropertyValueFactory<>("startOfEmployment"));
        columnDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editTableColumns();
    }

    @FXML
    protected void initialize() {
        new Thread(new Runnable() {
            @Override public void run() {
                handleSearch();
            }
        }).start();
    }

    @FXML
    private void handleSearch() {
        try {
            String selectedName = "%" + tfSearch.getText().trim().toLowerCase() + "%";
            tfSearch.clear();
            ObservableList<Employees> data = FXCollections.observableArrayList(employeeRepository.findByName(selectedName));
            employees.setItems(data);
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

            employeeRepository.insertEmployee(newEmployee);
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
            Employees tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setName(expStringCellEditEvent.getNewValue());
            employeeRepository.commitChange(tmp);

        });

        columnGender.setCellFactory(TextFieldTableCell.forTableColumn());
        columnGender.setOnEditCommit(expStringCellEditEvent -> {
            Employees tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setGender(expStringCellEditEvent.getNewValue());
            employeeRepository.commitChange(tmp);

        });

        columnCity.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCity.setOnEditCommit(expStringCellEditEvent -> {
            Employees tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setCity(expStringCellEditEvent.getNewValue());
            employeeRepository.commitChange(tmp);

        });

        columnStreet.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStreet.setOnEditCommit(expStringCellEditEvent -> {
            Employees tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setStreet(expStringCellEditEvent.getNewValue());
            employeeRepository.commitChange(tmp);

        });

        columnNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNumber.setOnEditCommit(expStringCellEditEvent -> {
            Employees tmp = expStringCellEditEvent.getTableView().getItems().
                    get(expStringCellEditEvent.getTablePosition().getRow());
            tmp.setNumber(expStringCellEditEvent.getNewValue());
            employeeRepository.commitChange(tmp);

        });

        columnDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDateOfBirth.setOnEditCommit(expLocalDateCellEditEvent -> {
            Employees tmp = expLocalDateCellEditEvent.getTableView().getItems().
                    get(expLocalDateCellEditEvent.getTablePosition().getRow());
            tmp.setDateOfBirth(expLocalDateCellEditEvent.getNewValue());
            employeeRepository.commitChange(tmp);
        });

        columnDateOfEmployment.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        columnDateOfEmployment.setOnEditCommit(expLocalDateCellEditEvent -> {
            Employees tmp = expLocalDateCellEditEvent.getTableView().getItems().
                    get(expLocalDateCellEditEvent.getTablePosition().getRow());
            tmp.setStartOfEmployment(expLocalDateCellEditEvent.getNewValue());
            employeeRepository.commitChange(tmp);
        });

        columnDelete.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Törölés");

            @Override
            protected void updateItem(Employees employee, boolean empty) {
                super.updateItem(employee, empty);
                if (employee == null) {
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
            employeeRepository.removeEmployee(employee);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba üzenet");
            alert.setHeaderText(null);
            alert.setContentText("A kijelöltt sor törlése sikertelen.");
            alert.showAndWait();
        }
    }
}