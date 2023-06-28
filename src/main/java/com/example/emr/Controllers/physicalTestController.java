package com.example.emr.Controllers;

import com.example.emr.PhysicalCSV;
import com.example.emr.Records.PatientRecord;
import com.example.emr.Records.analysisRecord;
import com.example.emr.Records.physicalResults;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class physicalTestController extends analysisBlankController{
    @FXML
    private TextField BMI_index;

    @FXML
    private TextField Height;

    @FXML
    private MenuItem Height_cm;

    @FXML
    private MenuItem Height_ft;

    @FXML
    private MenuItem PhysicalAnalysis;

    @FXML
    private MenuItem SalivaAnalysis;

    @FXML
    private MenuItem UrineAnalysis;

    @FXML
    private TextField Weight;

    @FXML
    private MenuItem Weight_kg;

    @FXML
    private MenuItem Weight_lbs;

    @FXML
    private MenuItem XRayAnalysis;

    @FXML
    private MenuItem bloodAnalysis;

    @FXML
    private TextField bloodPressure;

    @FXML
    private TextField bodyTemp;

    @FXML
    private TextField heartRate;

    @FXML
    private MenuButton heightUnit;

    @FXML
    private TextField remarks;

    @FXML
    private Button savePhysical;

    @FXML
    private MenuButton testType;

    @FXML
    private MenuButton weightUnit;
    @FXML
    private Text physicalSaved;
    private String heightChoice, weightChoice;
    //list to store data
    private List<physicalResults> physicalTest = new ArrayList<>();
    private PatientRecord rowdata;

    public void initialize(){
        // initiate menu button
        Height_cm.setOnAction(e -> {
            heightChoice = Height_cm.getText();
            heightUnit.setText(Height_cm.getText());
        });
        Height_ft.setOnAction(e -> {
            heightChoice = Height_ft.getText();
            heightUnit.setText(Height_ft.getText());
        });
        Weight_kg.setOnAction(e -> {
            weightChoice = Weight_kg.getText();
            heightUnit.setText(Weight_kg.getText());
        });
        Weight_lbs.setOnAction(e -> {
            heightChoice = Weight_lbs.getText();
            heightUnit.setText(Weight_lbs.getText());
        });

        //buttons
        savePhysical.setOnAction(e -> savePhysical());

        setButton(getRowdata());
    }

    public void savePhysical() {
        // just setting endless variables...
        double BloodPressure = Double.parseDouble(bloodPressure.getText());
        double HeartRate = Double.parseDouble(heartRate.getText());
        double BodyTemp = Double.parseDouble(bodyTemp.getText());
        double height = Double.parseDouble(Height.getText());
        double bmi = Double.parseDouble(BMI_index.getText());
        double weight = Double.parseDouble(Weight.getText());
        String remark = remarks.getText();
        // !!!NOTE PLS INTEGRATE THIS LATER ON
        String p_IC = rowdata.getP_Ic();
        LocalDate p_Doa = LocalDate.parse(rowdata.getP_Doa());

        // saving into analysis Results
        analysisRecord analysisLog = new analysisRecord(p_IC, p_Doa.toString(), "Physical");
        analysisLog.writeAnalysis();

        physicalResults p = new physicalResults(p_IC, p_Doa, analysisLog.getAnalysisDate(), BloodPressure, HeartRate, BodyTemp, height, heightChoice,
                bmi, weight, weightChoice, remark);

        //saving into csv file
        PhysicalCSV csv = new PhysicalCSV();
        physicalTest = csv.readPhysical(physicalResults.filename);
        physicalTest.add(p);
        csv.writePhysical(physicalTest, physicalResults.filename, analysisLog.getAnalysisDate());

        //after done
        physicalSaved.setVisible(true);
    }

    public void displayPhysical(PatientRecord rowdata, String timeLog){
        //view only
        savePhysical.setVisible(false);
        bloodPressure.setEditable(false);
        heartRate.setEditable(false);
        bodyTemp.setEditable(false);
        Height.setEditable(false);
        heightUnit.setDisable(true);
        BMI_index.setEditable(false);
        Weight.setEditable(false);
        weightUnit.setDisable(true);
        remarks.setEditable(false);
        testType.setDisable(true);

        // load data
        PhysicalCSV csv = new PhysicalCSV();
        physicalTest = csv.readPhysical(physicalResults.filename);
        for(physicalResults p:physicalTest) {
            if(p.getP_IC().equals(rowdata.getP_Ic()) && LocalDate.parse(rowdata.getP_Doa()).equals(p.getP_Doa()) &&
                    p.getTimeLog().equals(timeLog)){
                bloodPressure.setText(Double.toString(p.getBloodPressure()));
                heartRate.setText(Double.toString(p.getHeartRate()));
                bodyTemp.setText(Double.toString(p.getBodyTemp()));
                Height.setText(Double.toString(p.getHeight()));
                heightUnit.setText(p.getHeight_index());
                BMI_index.setText(Double.toString(p.getBMI()));
                Weight.setText(Double.toString(p.getWeight()));
                weightUnit.setText(p.getWeight_index());
                remarks.setText(p.getRemark());
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
