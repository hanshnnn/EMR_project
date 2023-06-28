package com.example.emr.Controllers;

import com.example.emr.Records.PatientRecord;
import com.example.emr.Records.analysisRecord;
import com.example.emr.Records.urineResults;
import com.example.emr.urineCSV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class urineTestController extends analysisBlankController{

    //controllers
    @FXML
    private MenuItem PhysicalAnalysis;

    @FXML
    private MenuItem SalivaAnalysis;

    @FXML
    private MenuItem UrineAnalysis;

    @FXML
    private MenuItem XRayAnalysis;

    @FXML
    private Tab analysis_Tab;

    @FXML
    private MenuItem bloodAnalysis;

    @FXML
    private Tab diagnosis_Tab;

    @FXML
    private TextField gluc_concent;

    @FXML
    private TextField gravity;

    @FXML
    private ChoiceBox<String> nitrities;

    @FXML
    private Tab profile_Tab;

    @FXML
    private ChoiceBox<String> protein;

    @FXML
    private ChoiceBox<String> rbc;

    @FXML
    private TextArea remarks;

    @FXML
    private Tab sum_Tab;

    @FXML
    private MenuButton testType;

    @FXML
    private Tab treatment_Tab;

    @FXML
    private Button urineSave;

    @FXML
    private Text urineSaved;

    @FXML
    private TextField urine_clarity;

    @FXML
    private TextField urine_colour;

    @FXML
    private TextField urine_odour;

    private String[] presence = {"Present", "Absent"};
    private String Nitrities, Protein, RBC;

    //List to store data
    private List<urineResults> urineTest  = new ArrayList<>();
    private PatientRecord rowdata;

    public void initialize() {

        // choiceBox loading
        nitrities.getItems().addAll(presence);
        protein.getItems().addAll(presence);
        rbc.getItems().addAll(presence);
        // initiate
        nitrities.setOnAction(this::nitritiesPresent);
        protein.setOnAction(this::proteinPresent);
        rbc.setOnAction(this::rbcPresent);
        // buttons
        urineSave.setOnAction(e -> saveUrine());
        setButton(getRowdata());
    }

    public void nitritiesPresent(ActionEvent event) {
        Nitrities =  nitrities.getValue();
    }

    public void proteinPresent(ActionEvent event) {
        Protein = protein.getValue();
    }

    public void rbcPresent(ActionEvent event){
        RBC = rbc.getValue();
    }

    public void saveUrine() {
        // just setting endless variables...
        double glucConcent = Double.parseDouble(gluc_concent.getText());
        double Gravity = Double.parseDouble(gravity.getText());
        String Remarks = remarks.getText();
        String UrineClarity = urine_clarity.getText();
        String UrineColour = urine_colour.getText();
        String UrineOdour = urine_odour.getText();
        // !!!NOTE PLS INTEGRATE THIS LATER ON
        String p_IC = getRowdata().getP_Ic();
        LocalDate p_Doa = LocalDate.parse(getRowdata().getP_Doa());

        // saving into analysis Results
        analysisRecord analysisLog = new analysisRecord(p_IC, p_Doa.toString(), "Urine");
        analysisLog.writeAnalysis();

        urineResults u = new urineResults(p_IC, p_Doa, analysisLog.getAnalysisDate(), glucConcent, Gravity, Nitrities, Protein, RBC, Remarks,
                UrineClarity, UrineColour, UrineOdour);

        // saving into csv file
        urineCSV csv = new urineCSV();
        urineTest = csv.readUrine(urineResults.filename);
        urineTest.add(u);
        csv.writeUrine(urineTest, urineResults.filename, analysisLog.getAnalysisDate());

        //after done
        urineSaved.setVisible(true);
    }

    public void displayUrine(PatientRecord rowdata, String timeLog){
        // view only
        testType.setDisable(true);
        urineSave.setVisible(false);
        gluc_concent.setEditable(false);
        gravity.setEditable(false);
        remarks.setEditable(false);
        urine_clarity.setEditable(false);
        urine_colour.setEditable(false);
        urine_odour.setEditable(false);
        nitrities.setDisable(true);
        protein.setDisable(true);
        rbc.setDisable(true);

        // load data
        urineCSV csv = new urineCSV();
        urineTest = csv.readUrine(urineResults.filename);
        for (urineResults u:urineTest){
            if(u.getP_IC().equals(rowdata.getP_Ic()) && LocalDate.parse(rowdata.getP_Doa()).equals(u.getP_Doa()) &&
                    u.getTimeLog().equals(timeLog)){
                gluc_concent.setText(Double.toString(u.getGlucConcent()));
                gravity.setText(Double.toString(u.getGravity()));
                remarks.setText(u.getRemarks());
                urine_clarity.setText(u.getUrineClarity());
                urine_colour.setText(u.getUrineColour());
                urine_odour.setText(u.getUrineOdour());
                nitrities.setValue(u.getNitrities());
                protein.setValue(u.getProtein());
                rbc.setValue(u.getRBC());
                break;
            }
        }
    }

    public PatientRecord getRowdata() {
        return rowdata;
    }

    public void setRowdata(PatientRecord rowdata) {
        this.rowdata = rowdata;
    }
}
