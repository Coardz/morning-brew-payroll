package com.morningbrew.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.morningbrew.Classes.Employee;
import com.morningbrew.Utils.AlertHelper;
import com.morningbrew.Utils.Database;
import com.morningbrew.Utils.SceneHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeeManagerController {

    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, Integer> idColumn, workingDaysColumn;
    @FXML private TableColumn<Employee, String> firstNameColumn, lastNameColumn, departmentColumn, positionColumn;
    @FXML private TableColumn<Employee, Double> rateColumn;

    @FXML private TextField firstNameField, lastNameField, deptField, positionField, rateField, daysField;
    
    @FXML private Button btnAdd, btnRemove, btnUpdate, btnSave, btnClear;

    // Navigation Button
    @FXML private Button btnHome, btnEmployeeManager, btnlogin;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setupTable();
        loadDataFromDatabase();

        // Prevent non-numeric characters in daysField (Integers only)
        
        daysField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                daysField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        rateField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                rateField.setText(oldValue);
            }
        });
    }

    private void setupTable() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        departmentColumn.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        positionColumn.setCellValueFactory(cellData -> cellData.getValue().positionProperty());

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());  
        rateColumn.setCellValueFactory(cellData -> cellData.getValue().rateProperty().asObject());
        workingDaysColumn.setCellValueFactory(cellData -> cellData.getValue().workingDaysProperty().asObject());

        employeeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_LAST_COLUMN);
    }

    private void loadDataFromDatabase() {
        employeeList.clear();
        String query = "SELECT * FROM employees";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                employeeList.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("department"),
                    rs.getString("position"),
                    rs.getDouble("rate"),
                    rs.getInt("working_days")
                ));
            }
            employeeTable.setItems(employeeList);
        } catch (SQLException e) {
            AlertHelper.showSystemError(e.getMessage());
        }
    }

    private boolean isDuplicate(String firstName, String lastName) {
        String query = "SELECT COUNT(*) FROM employees WHERE first_name = ? AND last_name = ?";

        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
        
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
        
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Returns true if a record already exists
            }
        } catch (SQLException e) {
            AlertHelper.showSystemError(e.getMessage());
        }
        return false;
    }

    @FXML
    private void handleAddEmployee() {
        String fname = firstNameField.getText().trim();
        String lname = lastNameField.getText().trim();
        
        if (fname.isEmpty() || lname.isEmpty() || 
            rateField.getText().isEmpty() || daysField.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "Form Incomplete", null, "Please fill in all fields.");
                return;
            }
            
            if (isDuplicate(fname, lname)) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Duplicate Entry", 
        "Employee Already Exists", 
                "A record for " + fname + " " + lname + " already exists in the system.");
            return;
        }
        
        String sql = "INSERT INTO employees(first_name, last_name, department, position, rate, working_days) VALUES(?,?,?,?,?,?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, firstNameField.getText());
            pstmt.setString(2, lastNameField.getText());
            pstmt.setString(3, deptField.getText());
            pstmt.setString(4, positionField.getText());
            pstmt.setDouble(5, Double.parseDouble(rateField.getText()));
            pstmt.setInt(6, Integer.parseInt(daysField.getText()));
            
            pstmt.executeUpdate();
            loadDataFromDatabase();
            clearFields();
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Success", null, "Employee added successfully!");
        } catch (NumberFormatException e) {
            AlertHelper.showValidationError("Please ensure Rate and Days are valid numbers.");
        } catch (SQLException e) {
            AlertHelper.showSystemError(e.getMessage());
        }
    }
    
    @FXML
    private void handleRemoveEmployee() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        
        if (selected == null) return;
        
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = Database.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, selected.getId());
            pstmt.executeUpdate();
            loadDataFromDatabase();
        } catch (SQLException e) {
            AlertHelper.showSystemError(e.getMessage());
        }
    }
    
    
    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        deptField.clear();
        positionField.clear();
        rateField.clear();
        daysField.clear();
    }
    
    @FXML
    private void handleEditEmployee(){
        
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        
        if (selected != null) {
            firstNameField.setText(selected.getFirstName());
            lastNameField.setText(selected.getLastName());
            deptField.setText(selected.getDepartment());
            positionField.setText(selected.getPosition());
            rateField.setText(String.valueOf(selected.getRate()));
            daysField.setText(String.valueOf(selected.getWorkingDays()));
        
            btnAdd.setDisable(true);
        }
        
    }

    @FXML
    private void handleUpdateSave() {
        
        String fname = firstNameField.getText().strip();
        String lname = firstNameField.getText().strip();
        
        if (fname.isEmpty() || lname.isEmpty() || 
            rateField.getText().isEmpty() || daysField.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "Form Incomplete", null, "Please fill in all fields.");
                return;
            }
        
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        
        if (selected == null) return;
        
        String sql = "UPDATE employees SET first_name=?, last_name=?, department=?, position=?, rate=?, working_days=? WHERE id=?";
        
        try (Connection conn = Database.getConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, firstNameField.getText());
                pstmt.setString(2, lastNameField.getText());
                pstmt.setString(3, deptField.getText());
                pstmt.setString(4, positionField.getText());
                pstmt.setDouble(5, Double.parseDouble(rateField.getText()));
                pstmt.setInt(6, Integer.parseInt(daysField.getText()));
                pstmt.setInt(7, selected.getId());
        
                pstmt.executeUpdate();
                loadDataFromDatabase();
                clearFields();
                btnAdd.setDisable(false);

        } catch (NumberFormatException e) {
            AlertHelper.showValidationError("Please ensure Rate and Days are valid numbers.");
        } catch (SQLException e) {
            AlertHelper.showSystemError(e.getMessage());
        }
    }

    @FXML
    private void handleClearFields() {
        clearFields();
        employeeTable.getSelectionModel().clearSelection();
        btnAdd.setDisable(false);
    }

    @FXML
    private void switchToEmployeeManager() {
        SceneHelper.switchScene("employeeManager");
    }

    @FXML
    private void switchToDashboard() {
        SceneHelper.switchScene("dashboard");
    }
     @FXML
    private void switchTologin() {
        SceneHelper.switchScene("login");
    }
}
