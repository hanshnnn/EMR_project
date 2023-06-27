package com.example.emr.Controllers;

import com.example.emr.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DiagnosisController implements Initializable {
    
    public Button add_symptoms;
    public Button add_diagnosis;
    public Button to_treatment;
    public ListView<String> symptoms_view;
    public ChoiceBox<String> practitioner_box;
    public Tab diagnosis_tab;
    public Tab profile_tab;
    public Tab treatment_tab;
    public TextArea remark_text;
    private ObservableList<String> selectedItems = FXCollections.observableArrayList();
    private ObservableList<Map.Entry<String, String>> keyValueList;
    @FXML
    private PatientProcedureRecordController patientProcedureRecordController;

    public String[] symptoms = {"Fever", "Headache" , "Fatigue" , "Nausea" , "Vomiting", "Diarrhea", "Abdominal pain" , "Chest pain" , "Shortness of breath" , "Cough" ,
            "Sore throat" ,
            "Runny or stuffy nose" ,
            "Muscle aches",
            "Joint pain" ,
            "Rash",
            "Swelling" ,
            "Dizziness" ,
            "Fainting" ,
            "Unexplained weight loss or gain" ,
            "Changes in vision" ,
            "Difficulty swallowing" ,
            "Changes in appetite",
            "Excessive thirst or hunger" ,
            "Frequent urination" ,
            "Difficulty sleeping" ,
            "Mood swings" ,
            "Memory problems" ,
            "Confusion" ,
            "Tremors" ,
            "Numbness or tingling sensations"};
    public static String[] practitioner = {"Dr. Emily Johnson", "Dr. Michael Smith" , "Dr. Sarah Davis" , "Dr. David Wilson" , "Dr. Jessica Anderson" , "Dr. Christopher Brown" , "Dr. Samantha Thompson" ,"Dr. Matthew Martinez", "Dr. Olivia Taylor", "Dr. James Clark" ,"Dr. Emily Johnson", "Dr. Michael Smith " ,"Dr. Sarah Davis", "Dr. David Wilson", "Dr. Jessica Anderson", "Dr. Christopher Brown", "Dr. Samantha Thompson", "Dr. Matthew Martinez", "Dr. Olivia Taylor", "Dr. James Clark"};
    @FXML
    public ComboBox<Map.Entry<String , String>> diagnosis_box;
    public GridPane gridpane;

    public Label label;
    public Label test_label;
    int row = 1;
    int column = 0;
    private PatientRecord rowData;
    private String diagnosis;
    private PatientProcedureRecord record;
    private TreatmentController treatmentController;

    // Setter method for patientProcedureRecordController
    public void setPatientProcedureRecordController(PatientProcedureRecordController patientProcedureRecordController) {
        this.patientProcedureRecordController = patientProcedureRecordController;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        add_symptoms.setOnMouseClicked(mouseEvent -> {
            symptoms_view.setVisible(true);
        });

        practitioner_box.getItems().addAll(practitioner);
        symptoms_view.getItems().addAll(symptoms);
        symptoms_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<Map.Entry<String, String>> keyValueList = FXCollections.observableArrayList();
        keyValueList.addAll(
                Map.entry("I10", "ICD-10 Hypertension"),
                Map.entry("E10", "Type-1 diabetes"),
                Map.entry("E11", "Type-2 diabetes"),
                Map.entry("O24.4", "Gestational diabetes"),
                Map.entry("J45.20", "Mild intermittent asthma"),
                Map.entry("J45.21", "Mild persistent asthma"),
                Map.entry("J45.22", "Moderate persistent asthma"),
                Map.entry("J45.23", "Severe persistent asthma"),
                Map.entry("M17.9", "Knee osteoarthritis"),
                Map.entry("M16.9", "Hip osteoarthritis"),
                Map.entry("M47.819", "Spine osteoarthritis"),
                Map.entry("F32.9", "Major depressive disorder - Single episode"),
                Map.entry("F33.9", "Major depressive disorder - Recurrent episode"),
                Map.entry("J42", "Chronic bronchitis"),
                Map.entry("J43.9", "Emphysema"),
                Map.entry("I20.9", "Unspecified angina"),
                Map.entry("I21.9", "Acute myocardial infarction"),
                Map.entry("I25.9", "Stable angina"),
                Map.entry("N39.0", "Uncomplicated urinary tract infection"),
                Map.entry("N39.3", "Complicated urinary tract infection"),
                Map.entry("K21.0", "Gastroesophageal reflux disease (GERD) - With esophagitis"),
                Map.entry("K21.9", "Gastroesophageal reflux disease (GERD) - Without esophagitis"),
                Map.entry("E03.9", "Primary hypothyroidism"),
                Map.entry("E03.1", "Secondary hypothyroidism"),
                Map.entry("G43.909", "Migraine without aura"),
                Map.entry("G43.109", "Migraine with aura")
        );

        // Create a ComboBox
        diagnosis_box.setItems(keyValueList);
        // Set the display text for the ComboBox
        diagnosis_box.setConverter(new KeyValueStringConverter());

        symptoms_view.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Label dynamicLabel = new Label(newValue);
                this.selectedItems.add(newValue);
                dynamicLabel.setBackground(Background.fill(Color.WHITE));
                dynamicLabel.setAlignment(Pos.CENTER);
                dynamicLabel.setTextFill(Color.BLACK);
                dynamicLabel.setPrefHeight(25);
                dynamicLabel.setPrefWidth(136);
                for (int i = 1; i <= 1; i++) {
                    column++;
                    if (column >= 4) {
                        column = 0;
                        row++;
                    }
                }
                gridpane.add(dynamicLabel, column, row);
            }
        });

        treatment_tab.setOnSelectionChanged(event -> {
            try {
                ViewModel.showTreatmentPage(diagnosis, rowData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        add_diagnosis.setOnMouseClicked(mouseEvent -> {
            try {
                creatediagnosis(rowData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        to_treatment.setOnMouseClicked(mouseEvent -> {
            try {
               ViewModel.showTreatmentPage(String.valueOf(diagnosis_box.getValue()),rowData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void creatediagnosis(PatientRecord rowData) throws IOException {
        this.rowData = rowData;
        ArrayList<String> arrayList = new ArrayList<>(selectedItems);
        String remarks = remark_text.getText();
        String practitioner = String.valueOf(practitioner_box.getValue());
        diagnosis = String.valueOf(diagnosis_box.getValue());

       // TreatmentController t = new TreatmentController();
       // t.updateLabelText(diagnosis);
        patientProcedureRecordController.searchProcCode(diagnosis,rowData);

        PatientDiagnosis pd = new PatientDiagnosis(String.valueOf(rowData.getP_Ic()), rowData.getP_Doa(), arrayList ,remarks,practitioner,diagnosis);
        DiagnosisHandler dh = new DiagnosisHandler();
        dh.create(pd);
    }

    //convert key/values into string format//
    private static class KeyValueStringConverter extends javafx.util.StringConverter<Map.Entry<String, String>> {
        @Override
        public String toString(Map.Entry<String, String> entry) {
            if (entry != null) {
                return entry.getKey() + entry.getValue();
            }
            return null;
        }
        @Override
        public Map.Entry<String, String> fromString(String s) {
            return null;
        }
    }
}
