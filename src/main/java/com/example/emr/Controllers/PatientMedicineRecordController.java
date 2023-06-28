package com.example.emr.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import com.example.emr.Records.PatientRecord;
import com.example.emr.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PatientMedicineRecordController implements Initializable {




    @FXML
    public MenuButton medicineCode;
    @FXML
    public MenuItem A100, A200, A300, A400, A500, A600, A700, A800, A900, A1000, A1100, A1200, A1300, A1400, A1500, A1600, A1700, A1800, A1900, A2000, A2100, A2200, A2300, A2400, A2500, A2600;
    @FXML
    public Label chosenMC;
    @FXML
    public TextField duration;
    @FXML
    public TextField quantity;
    @FXML
    public MenuButton prescribedBy;
    @FXML
    public MenuItem DocZ;
    @FXML
    public MenuItem DocS;
    @FXML
    public MenuItem DocM;
    @FXML
    public Label chosenDoc;
    @FXML
    public TextArea remarks;
    @FXML
    public Button saveButton;

    private medicineHandler pph = new medicineHandler();
    private medicineRecord record;
    public List<medicineRecord> searchList = new ArrayList<>();
    private String searchString = "";
    private ObservableList<medicineRecord> observableArrayList = FXCollections.observableArrayList();
    List<medicineRecord> matchingRecords = new ArrayList<medicineRecord>();
    private PatientRecord rowData;
    public String diagnosis;
    private String medCode;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FileReader medicineCodes = null;
        try {
            medicineCodes = new FileReader("medicineCode.txt");
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        BufferedReader bufferedReader = new BufferedReader(medicineCodes);
        String eachMedicineCode = null;
        try {
            eachMedicineCode = bufferedReader.readLine();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String[] splittedMedicineCode = eachMedicineCode.split(",");

        A100.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[0]));
        A200.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[1]));
        A300.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[2]));
        A400.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[3]));
        A500.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[4]));
        A600.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[5]));
        A700.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[6]));
        A800.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[7]));
        A900.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[8]));
        A1000.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[9]));
        A1100.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[10]));
        A1200.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[11]));
        A1300.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[12]));
        A1400.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[13]));
        A1500.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[14]));
        A1600.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[15]));
        A1700.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[16]));
        A1800.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[17]));
        A1900.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[18]));
        A2000.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[19]));
        A2100.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[20]));
        A2200.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[21]));
        A2300.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[22]));
        A2400.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[23]));
        A2500.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[24]));
        A2600.setOnAction(event1 -> chosenMC.setText(splittedMedicineCode[25]));

        FileReader doctorNames = null;
        try {
            doctorNames = new FileReader("doctor.txt");
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        BufferedReader bufferedReaderForDoctor = new BufferedReader(doctorNames);
        String eachDoctorName = null;
        try {
            eachDoctorName = bufferedReaderForDoctor.readLine();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String[] splittedDoctorName = eachDoctorName.split(",");

        DocM.setOnAction(event2 -> chosenDoc.setText(splittedDoctorName[0]));
        DocS.setOnAction(event2 -> chosenDoc.setText(splittedDoctorName[1]));
        DocZ.setOnAction(event2 -> chosenDoc.setText(splittedDoctorName[2]));

        saveButton.setOnAction(ef -> {
            try {
                saveMedicine(rowData);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Operation Successful");
                alert.setContentText("Data saved");
                // Display the Alert pop-up
                alert.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void saveMedicine(PatientRecord rowData) throws IOException {
        this.rowData = rowData;
        String medCode = chosenMC.getText();
        medicineRecord medicineRecord1 = new medicineRecord(rowData.getP_Ic(), rowData.getP_Doa(), medCode, duration.getText(),quantity.getText(), chosenDoc.getText(),remarks.getText());
        medicineHandler medicineHandler = new medicineHandler();
        medicineHandler.create(medicineRecord1);
    }

}

