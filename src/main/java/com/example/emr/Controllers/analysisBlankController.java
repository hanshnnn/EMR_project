package com.example.emr.Controllers;

import com.example.emr.Records.PatientRecord;
import com.example.emr.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;


public class analysisBlankController {
    @FXML
    private MenuItem PhysicalAnalysis;

    @FXML
    private MenuItem SalivaAnalysis;

    @FXML
    private MenuItem UrineAnalysis;

    @FXML
    private MenuItem XRayAnalysis;

    @FXML
    private Tab analysis_tab;

    @FXML
    private MenuItem bloodAnalysis;

    @FXML
    public Tab diagnosis_tab;

    @FXML
    private Tab profile_tabb;

    @FXML
    private MenuButton select;

    @FXML
    private Tab summary_tab;

    @FXML
    private Tab treatment_tab;

    private PatientRecord rowdata;

    public void initialize(PatientRecord rowdata){
        setButton(rowdata);
        diagnosis_tab.setOnSelectionChanged(Event -> {
            ViewModel.showDiagnosisForm(rowdata);
        });
        analysis_tab.setOnSelectionChanged(e -> {
            ViewModel.showAnalysisBlank(rowdata);
        });
        // navigation bar
        profile_tabb.setOnSelectionChanged(e ->{
            ViewModel.showPatientProfile(rowdata);
        });

    }
    public void setButton(PatientRecord Rowdata){
        //initiate menu button
        bloodAnalysis.setOnAction(e -> {
            ViewModel.showBloodAnalysis(Rowdata);
        });
        UrineAnalysis.setOnAction(e -> {
            ViewModel.showUrineAnalysis(Rowdata);
        });
        SalivaAnalysis.setOnAction(e -> {
            ViewModel.showSalivaAnalysis(Rowdata);
        });
        PhysicalAnalysis.setOnAction(e -> {
            ViewModel.showPhysicalAnalysis(Rowdata);
        });
        XRayAnalysis.setOnAction(e -> {
            ViewModel.showXRayAnalysis(Rowdata);
        });
    }

    public PatientRecord getRowdata() {
        return rowdata;
    }

    public void setRowdata(PatientRecord rowdata) {
        this.rowdata = rowdata;
    }
}
