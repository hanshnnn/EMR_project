package com.example.emr.Controllers;

import com.example.emr.*;
import com.example.emr.Records.PatientRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PatientProcedureRecordController{
    @FXML
    public TableView<PatientProcedureRecord> table_procedure;
    @FXML
    private TableColumn<PatientProcedureRecord, String> codeColumn;
    @FXML
    private TableColumn<PatientProcedureRecord, String> dateColumn;
    @FXML
    private TableColumn<PatientProcedureRecord, String> descriptionColumn;
    @FXML
    private TableColumn<PatientProcedureRecord, String> priceColumn;
    @FXML
    public Button saveBtn;
    @FXML
    public Button displayBtn;

    //crystal: should be procefureForm fx id, but b4 i made some changes, i will edit later on
    @FXML
    public AnchorPane procedureForm;
    private PatientProcHandler pph = new PatientProcHandler();
    private List<PatientProcedureRecord> searchList = new ArrayList<>();

    private String diagnosis;
    protected LocalDate selectedDate = LocalDate.now();
    protected List<PatientProcedureRecord> matchingRecords = new ArrayList<PatientProcedureRecord>();
    protected ObservableList<PatientProcedureRecord> procedureList = FXCollections.observableArrayList();
    private PatientRecord rowData;


    public void initialize(PatientRecord rowData) {
        this.rowData = rowData;
        displayBtn.setOnAction(e -> {
            if (matchingRecords.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("No such procedure exists");
                alert.show();
            }else{
                System.out.println(selectedDate);
                addDays(selectedDate); // Add this line to update the dates
                displayProcedures(matchingRecords);
            }
        });

        saveBtn.setOnAction(e -> {
            try {
                matchingRecords = searchProcCode(diagnosis, rowData);

                if (matchingRecords.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No such procedure exists");
                    alert.show();
                } else {
                    // Update the dates in matchingRecords

                    for (PatientProcedureRecord record : matchingRecords) {
                        addDays(selectedDate);
                        saveProcedureRecord(record, rowData);
                    }

                    // Notify that the procedures are saved
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Procedures saved!");
                    alert.show();
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

    }
    // Compare whether the first data in csv matches the data
    // It should be used when clicked add at treatment page
    // Display patient proc based on code into table

    public List<PatientProcedureRecord> searchProcCode(String diagnosis,PatientRecord rowData) throws IOException {
        this.diagnosis = diagnosis;
        this.rowData = rowData;
        matchingRecords.clear();
        searchList = pph.readFixedProc(PatientProcedureRecord.filename_fixedP);
        // Search for the procedure code based on the diagnosis
        for (PatientProcedureRecord record : searchList) {
            if (record.getCode() != null && record.getCode().equals(diagnosis)) {
                //this.record = record;
                //String numericStringIC = rowData.getP_Ic().replaceAll("-", "");
                matchingRecords.add(record);
                //saveProcedureRecord(record);
            }
        }
        return  matchingRecords;
    }
    protected void addDays(LocalDate selectedDate){
        this.selectedDate = selectedDate;
        LocalDate updatedDate = selectedDate;
        for (PatientProcedureRecord record : matchingRecords) {
            if (updatedDate != null) {
                updatedDate = updatedDate.plusDays(2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = updatedDate.format(formatter);
                record.setDate(formattedDate);
            }
        }

    }
    public void saveProcedureRecord(PatientProcedureRecord record, PatientRecord rowData) throws IOException {
        if (record != null) {
            System.out.println("Saving Procedure Record: " + record);
            PatientProcedureRecord pd = new PatientProcedureRecord(String.valueOf(rowData.getP_Ic()), rowData.getP_Doa(), record.getCode(), record.getDate(), record.getDescription(), record.getPrice());
            PatientProcHandler patientProcHandler = new PatientProcHandler();
            patientProcHandler.create(pd);

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("No such procedure exists");
            alert.show();
        }
    }

    public PatientProcedureRecordController(){
    }
    protected void displayProcedures(List<PatientProcedureRecord> records) {
        System.out.println("Displaying Procedures");

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        if (records.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("No procedure records found");
            alert.show();
        } else {
            for (PatientProcedureRecord record : records) {
                PatientProcedureRecord updatedRecord = new PatientProcedureRecord(record.getCode(), record.getDate(), record.getDescription(), record.getPrice());
                procedureList.add(updatedRecord);
            }
            table_procedure.setItems(procedureList);
        }
    }

}

