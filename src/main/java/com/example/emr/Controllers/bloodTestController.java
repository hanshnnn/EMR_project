package com.example.emr.Controllers;

import com.example.emr.Records.PatientRecord;
import com.example.emr.Records.analysisRecord;
import com.example.emr.Records.bloodResults;
import com.example.emr.bloodCSV;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class bloodTestController extends analysisBlankController{
    @FXML
    private Button BloodSave;

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
    private AnchorPane bloodAnchor;

    @FXML
    private TextField bloodGluLev;

    @FXML
    private Text bloodSaved;

    @FXML
    private TextField choles_Lev;

    @FXML
    public Tab diagnosis_tab;

    @FXML
    private TextField haemogLev;

    @FXML
    private TextField platelets_count;

    @FXML
    private Tab profile_Tab;

    @FXML
    private TextField rbc;

    @FXML
    private TextField remarks;

    @FXML
    private Tab sum_Tab;

    @FXML
    private TextField t3Lev;

    @FXML
    private TextField t4Lev;

    @FXML
    private MenuButton testType;

    @FXML
    private Tab treatment_Tab;

    @FXML
    private TextField tshLev;

    @FXML
    private TextField wbc;
    @FXML
    private MenuItem bloodAnalysis;



    // List to store data
    private List<bloodResults> bloodTest = new ArrayList<>();
    private PatientRecord rowdata;
    public String displayMode;
    public String timeLog;

    public void initialize(){
        BloodSave.setOnAction(e -> saveBlood());
        setButton(this.rowdata);
    }

    public void saveBlood() {
        // just setting endless variables...
        double Rbc = Double.parseDouble(rbc.getText());
        double Platelets = Double.parseDouble(platelets_count.getText());
        double bloodgluLev = Double.parseDouble(bloodGluLev.getText());
        double t4lev = Double.parseDouble(t4Lev.getText());
        double thslev = Double.parseDouble(tshLev.getText());
        double haemoglev = Double.parseDouble(haemogLev.getText());
        double choles_lev = Double.parseDouble(choles_Lev.getText());
        double t3lev = Double.parseDouble(t3Lev.getText());
        double WBC = Double.parseDouble(wbc.getText());
        String Remarks = remarks.getText();
        // !!!NOTE PLS INTEGRATE THIS LATER ON
        String p_IC = rowdata.getP_Ic();
        LocalDate p_Doa = LocalDate.parse(rowdata.getP_Doa());

        // saving into analysis Results
        analysisRecord analysisLog = new analysisRecord(p_IC, p_Doa.toString(), "Blood");
        analysisLog.writeAnalysis();

        // saving into blood Results
        bloodResults b = new bloodResults(p_IC, p_Doa, analysisLog.getAnalysisDate(), Rbc,Platelets, bloodgluLev, t4lev, thslev, haemoglev, choles_lev, t3lev, WBC,
                Remarks);

        // saving into csv file
        bloodCSV csv = new bloodCSV();
        bloodTest = csv.readBlood(bloodResults.filename);
        bloodTest.add(b);
        csv.writeBlood(bloodTest, bloodResults.filename, analysisLog.getAnalysisDate());

        // after done
        bloodSaved.setVisible(true);

    }

    public void displayBlood(PatientRecord rowdata, String timeLog){
        //view only
        BloodSave.setVisible(false);
        rbc.setEditable(false);
        platelets_count.setEditable(false);
        bloodGluLev.setEditable(false);
        t4Lev.setEditable(false);
        tshLev.setEditable(false);
        haemogLev.setEditable(false);
        choles_Lev.setEditable(false);
        t3Lev.setEditable(false);
        wbc.setEditable(false);
        remarks.setEditable(false);
        testType.setDisable(true);

        //load data
        bloodCSV csv = new bloodCSV();
        bloodTest = csv.readBlood(bloodResults.filename);
        for (bloodResults b:bloodTest){
            if(b.getP_IC().equals(rowdata.getP_Ic()) && LocalDate.parse(rowdata.getP_Doa()).equals(b.getP_Doa()) &&
                    b.getTimeLog().equals(timeLog)){
                rbc.setText(Double.toString(b.getRbc()));
                platelets_count.setText(Double.toString(b.getPlatelets_count()));
                bloodGluLev.setText(Double.toString(b.getBloodGluLev()));
                t4Lev.setText(Double.toString(b.getT4Lev()));
                tshLev.setText(Double.toString(b.getTshLev()));
                haemogLev.setText(Double.toString(b.getHaemogLev()));
                choles_Lev.setText(Double.toString(b.getCholes_Lev()));
                t3Lev.setText(Double.toString(b.getT3Lev()));
                wbc.setText(Double.toString(b.getWbc()));
                remarks.setText(b.getRemarks());
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
