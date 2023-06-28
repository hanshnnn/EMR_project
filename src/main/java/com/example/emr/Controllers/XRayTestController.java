package com.example.emr.Controllers;

import com.example.emr.Records.PatientRecord;
import com.example.emr.Records.XRayResults;
import com.example.emr.Records.analysisRecord;
import com.example.emr.XRayCSV;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XRayTestController extends analysisBlankController{

    @FXML
    private MenuItem PhysicalAnalysis;

    @FXML
    private MenuItem SalivaAnalysis;

    @FXML
    private MenuItem UrineAnalysis;

    @FXML
    private MenuItem XRayAnalysis;

    @FXML
    private Text XRaySaved;

    @FXML
    private Tab analysis_Tab;

    @FXML
    private MenuItem bloodAnalysis;

    @FXML
    private Tab diagnosis_Tab;

    @FXML
    private Button logAnalysis;

    @FXML
    private Tab profile_Tab;

    @FXML
    private TextArea remarks;

    @FXML
    private Button save;

    @FXML
    private Tab sum_Tab;

    @FXML
    private MenuButton testType;

    @FXML
    private Tab treatment_Tab;

    @FXML
    private Button xrayButton1;

    @FXML
    private Button xrayButton2;

    @FXML
    private Button xrayButton3;

    @FXML
    private AnchorPane xrayTab;

    @FXML
    private ImageView xray_png1;

    @FXML
    private ImageView xray_png2;

    @FXML
    private ImageView xray_png3;
    private String image1 = null, image2 = null, image3 = null;
    //List to store data
    private List<XRayResults> XRayTest  = new ArrayList<>();
    private PatientRecord rowdata;


    public void initialize(){
        xrayButton1.setOnAction(e -> {
            try {
                imageChooser("one");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        xrayButton2.setOnAction(e -> {
            try {
                imageChooser("two");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        xrayButton3.setOnAction(e -> {
            try {
                imageChooser("three");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        save.setOnAction(e -> saveXRay());
        setButton(this.rowdata);
    }

    public void imageChooser(String choice) throws IOException {
        FileChooser fc = new FileChooser();
        // specifying file type accepted
        fc.setTitle("Please select the image file");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files","*.jpg", "*.jpeg", "*.png", "*.gif"));
        File f = fc.showOpenDialog(null);

        if (f != null){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd_hhmmss");
            String fileName = rowdata.getP_Ic() + "_" + dateFormat.format(new Date()) + ".jpg";
            String destinationPath = "ImageFile/" + fileName;
            File destinationFile = new File(destinationPath);
            Image img = new Image(f.toURI().toString());
            if (choice == "one"){
                xray_png1.setImage(img);
                image1 = "ImageFile/" + fileName;

                Files.copy(f.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                f = destinationFile;
            } else if (choice == "two") {
                xray_png2.setImage(img);
                image2 = "ImageFile/" + fileName;

                Files.copy(f.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                f = destinationFile;
            } else {
                xray_png3.setImage(img);
                image3 = "ImageFile/" + fileName;

                Files.copy(f.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                f = destinationFile;
            }
        }
    }

    public void saveXRay() {
        String remark = remarks.getText();
        // !!!NOTE PLS INTEGRATE THIS LATER ON
        String p_IC = getRowdata().getP_Ic();
        LocalDate p_Doa = LocalDate.parse(getRowdata().getP_Doa());

        // saving into analysis Results
        analysisRecord analysisLog = new analysisRecord(p_IC, p_Doa.toString(), "X-Ray");
        analysisLog.writeAnalysis();

        // saving into XRay Results
        XRayResults x = new XRayResults(p_IC, p_Doa, analysisLog.getAnalysisDate(), image1, image2, image3, remark);

        // saving into csv file
        XRayCSV csv = new XRayCSV();
        XRayTest = csv.readXRay(XRayResults.filename);
        XRayTest.add(x);
        csv.writeXRay(XRayTest, XRayResults.filename);

        //after done
        XRaySaved.setVisible(true);

    }

    public void displayXRay(PatientRecord rowdata, String timeLog) throws FileNotFoundException {
        save.setVisible(false);
        testType.setDisable(true);
        xrayButton1.setDisable(true);
        xrayButton2.setDisable(true);
        xrayButton3.setDisable(true);
        remarks.setEditable(false);

        // load data
        XRayCSV csv = new XRayCSV();
        XRayTest = csv.readXRay(XRayResults.filename);
        for (XRayResults x:XRayTest){
            if (x.getP_IC().equals(rowdata.getP_Ic()) && LocalDate.parse(rowdata.getP_Doa()).equals(x.getP_Doa()) &&
                x.getTimeLog().equals(timeLog)){
                FileInputStream inputStream = new FileInputStream(x.getImage1p());
                xray_png1.setImage(new Image(inputStream));
                inputStream = new FileInputStream(x.getImage2p());
                xray_png2.setImage(new Image(inputStream));
                inputStream = new FileInputStream(x.getImage3p());
                xray_png3.setImage(new Image(inputStream));
                remarks.setText(x.getRemark());
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
