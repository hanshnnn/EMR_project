package com.example.emr.Controllers;

import com.example.emr.Records.PatientRecord;
import com.example.emr.Records.analysisRecord;
import com.example.emr.Records.salivaResults;
import com.example.emr.salivaCSV;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class salivaTestController extends analysisBlankController{
    //controllers

    @FXML
    private TextField CalciumLevel;

    @FXML
    private TextField CrotisolLevel;

    @FXML
    private TextField CytokineLevel;

    @FXML
    private TextField IgALevel;

    @FXML
    private TextField IgGLevel;

    @FXML
    private TextField IgMLevel;

    @FXML
    private MenuItem PhysicalAnalysis;

    @FXML
    private TextField PotassiumLevel;

    @FXML
    private TextField Remarks;

    @FXML
    private MenuItem SalivaAnalysis;

    @FXML
    private TextField Sodium;

    @FXML
    private MenuItem UrineAnalysis;

    @FXML
    private MenuItem XRayAnalysis;

    @FXML
    private MenuItem bloodAnalysis;

    @FXML
    private TextField salivaPH;

    @FXML
    private Button saveSaliva;
    @FXML
    private Text salivaSaved;
    @FXML
    private MenuButton testType;

    //List to store data
    private List<salivaResults> salivaTest = new ArrayList<>();
    private PatientRecord rowdata;

    public void initialize() {
        saveSaliva.setOnAction(e -> saveSaliva());
        setButton(getRowdata());
    }

    public void saveSaliva() {
        // just setting endless variables...
        double saliva_ph = Double.parseDouble(salivaPH.getText());
        double iga = Double.parseDouble(IgALevel.getText());
        double igg = Double.parseDouble(IgGLevel.getText());
        double igm = Double.parseDouble(IgMLevel.getText());
        double cytokinelvl = Double.parseDouble(CytokineLevel.getText());
        double cortisollvl = Double.parseDouble(CrotisolLevel.getText());
        double sodiumlvl = Double.parseDouble(Sodium.getText());
        double potassiumlvl = Double.parseDouble(PotassiumLevel.getText());
        double calciumlvl = Double.parseDouble(CalciumLevel.getText());
        String remarks = Remarks.getText();
        // !!!NOTE PLS INTEGRATE THIS LATER ON
        String p_IC = getRowdata().getP_Ic();
        LocalDate p_Doa = LocalDate.parse(getRowdata().getP_Doa());

        // saving into analysis Results
        analysisRecord analysisLog = new analysisRecord(p_IC, p_Doa.toString(), "Saliva");
        analysisLog.writeAnalysis();

        salivaResults s = new salivaResults(p_IC, p_Doa, analysisLog.getAnalysisDate(), saliva_ph, iga, igg, igm, cytokinelvl,
                cortisollvl, sodiumlvl, potassiumlvl, calciumlvl, remarks);

        // saving into csv file
        salivaCSV csv = new salivaCSV();
        salivaTest = csv.readSaliva(salivaResults.filename);
        salivaTest.add(s);
        csv.writeSaliva(salivaTest, salivaResults.filename, analysisLog.getAnalysisDate());

        //after done
        salivaSaved.setVisible(true);
    }

    public void displaySaliva(PatientRecord rowdata, String timeLog){
        //view only
        saveSaliva.setVisible(false);
        salivaPH.setEditable(false);
        IgALevel.setEditable(false);
        IgGLevel.setEditable(false);
        IgMLevel.setEditable(false);
        CytokineLevel.setEditable(false);
        CrotisolLevel.setEditable(false);
        Sodium.setEditable(false);
        PotassiumLevel.setEditable(false);
        CalciumLevel.setEditable(false);
        Remarks.setEditable(false);
        testType.setDisable(true);

        // load data
        salivaCSV csv = new salivaCSV();
        salivaTest = csv.readSaliva(salivaResults.filename);
        for(salivaResults s:salivaTest) {
            if(s.getP_IC().equals(rowdata.getP_Ic()) && LocalDate.parse(rowdata.getP_Doa()).equals(s.getP_Doa()) &&
                    s.getTimeLog().equals(timeLog)){
                salivaPH.setText(Double.toString(s.getSaliva_ph()));
                IgALevel.setText(Double.toString(s.getIga()));
                IgGLevel.setText(Double.toString(s.getIgg()));
                IgMLevel.setText(Double.toString(s.getSaliva_ph()));
                CytokineLevel.setText(Double.toString(s.getCytokinelvl()));
                CrotisolLevel.setText(Double.toString(s.getCortisollvl()));
                Sodium.setText(Double.toString(s.getSodiumlvl()));
                PotassiumLevel.setText(Double.toString(s.getPotassiumlvl()));
                CalciumLevel.setText(Double.toString(s.getSaliva_ph()));
                Remarks.setText(s.getRemarks());
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
