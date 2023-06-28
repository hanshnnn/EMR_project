package com.example.emr.Controllers;

import com.example.emr.ActionCells.ActionCell3;
import com.example.emr.Records.PatientRecord;
import com.example.emr.Records.analysisRecord;
import com.example.emr.ViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;

public class pProfileController implements Initializable {
    public Rectangle rectangle_image;
    public Label label_name1;
    public Label label_pastmedicalrecord1;
    public Label label_dob1;
    public Label label_anyallergies1;
    public Label label_gender1;
    public Label label_dateofadmission1;
    public Label label_address1;
    public Tab analysis_tab;
    public Tab diagnosis_tab;
    public Button diagnosis_button;
    public Tab treatment_tab;
    public ImageView imageview_image;
    public Label label_age1;

    public TableView<com.example.emr.Records.analysisRecord> historyTable;

    private PatientRecord rowData;
    private ObservableList<analysisRecord> observableList = FXCollections.observableArrayList();

    public static class profileController {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diagnosis_tab.setOnSelectionChanged(Event -> {
            ViewModel.showDiagnosisForm(rowData);
        });
        analysis_tab.setOnSelectionChanged(e -> {
            ViewModel.showAnalysisBlank(rowData);
        });
        constructTable();
    }

    public void displayinfo(PatientRecord rowData) {
        this.rowData = rowData;
        label_name1.setText(rowData.getP_Name());
        label_dob1.setText(rowData.getP_Dob());
        label_address1.setText(rowData.getP_Address());
        label_anyallergies1.setText(rowData.getP_Allergies());
        label_dateofadmission1.setText(rowData.getP_Doa());
        label_gender1.setText(rowData.getP_Gender());
        label_pastmedicalrecord1.setText(rowData.getP_Pmr());

        String Ic_number = rowData.getP_Ic();
        int year = Integer.parseInt(Ic_number.substring(0, 2));
        int month = Integer.parseInt(Ic_number.substring(2, 4));
        int day = Integer.parseInt(Ic_number.substring(4, 6));
        int century = 1900 + (year <=23 ? 100 : 0);
        int birthYear = century + year;
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = LocalDate.of(birthYear, month, day);
        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();
        label_age1.setText(String.valueOf(age));

        String imagePath = rowData.getImagePath();
        if (imagePath != null) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                Image img = new Image(imageFile.toURI().toString());
                imageview_image.setImage(img);
            }
        }
    }

    public void constructTable(){
        historyTable.setEditable(true);
        analysisRecord arh = new analysisRecord();

//        TableColumn<com.example.emr.Records.analysisRecord, String> patientIC = new TableColumn<>("patient IC");
//        patientIC.setCellValueFactory(new PropertyValueFactory<com.example.emr.Records.analysisRecord, String>("P_Ic"));
//        patientIC.setCellFactory(TextFieldTableCell.forTableColumn());
//
//        TableColumn<com.example.emr.Records.analysisRecord, String> patientDoa = new TableColumn<>("Date of Admission");
//        patientDoa.setCellValueFactory(new PropertyValueFactory<com.example.emr.Records.analysisRecord, String>("P_Doa"));
//        patientDoa.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<analysisRecord, String> analsisType = new TableColumn<>("Analysis");
        analsisType.setCellValueFactory(new PropertyValueFactory<analysisRecord, String>("AnalysisType"));
        analsisType.setCellFactory(TextFieldTableCell.forTableColumn());
        analsisType.setMinWidth(450);

        TableColumn<com.example.emr.Records.analysisRecord, String> analysisTimeLog = new TableColumn<>("Timelog");
        analysisTimeLog.setCellValueFactory(new PropertyValueFactory<com.example.emr.Records.analysisRecord, String>("AnalysisDate"));
        analysisTimeLog.setCellFactory(TextFieldTableCell.forTableColumn());
        analysisTimeLog.setMinWidth(300);

        // view action cell
        TableColumn<com.example.emr.Records.analysisRecord, Void> view = new TableColumn<>("view_analysis");
        view.setCellFactory(column -> new ActionCell3(rowData));
        view.setMinWidth(200);

        historyTable.getColumns().addAll(analsisType, analysisTimeLog, view);
        observableList.addAll(arh.readCSV(analysisRecord.filename));
        historyTable.setItems(observableList);

        Thread readFile = new Thread(() -> {
            while (true) {
                List<analysisRecord> updated_data = arh.readCSV(com.example.emr.Records.analysisRecord.filename);
                Platform.runLater(() -> {
                    observableList.clear();
                    observableList.addAll(updated_data);
                    historyTable.refresh();
                });

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        readFile.setDaemon(true); // Set the thread as a daemon thread (optional)
        readFile.start();
    };


    public void filterData(PatientRecord rowData){
        FilteredList<analysisRecord> filteredList = new FilteredList<>(observableList, Analysis -> Analysis.getP_Ic().equals(rowData.getP_Ic()) && Analysis.getP_Doa().equals(rowData.getP_Doa()));
        SortedList<analysisRecord> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(historyTable.comparatorProperty());

        historyTable.setItems(sortedData);
    }

    // for patient portal
    public void patient_view(PatientRecord rowData){
        displayinfo(rowData);
        filterData(rowData);
        analysis_tab.setText("");
        analysis_tab.setDisable(true);
        diagnosis_tab.setText("");
        diagnosis_tab.setDisable(true);

        treatment_tab.setDisable(false);
        treatment_tab.setText("Treatment Summary");
        treatment_tab.setOnSelectionChanged(event -> {
        //below tries to read diagnosis.csv to extract what diagnosis code is given to the patient
        try {
            FileReader fileReader = null;
            fileReader = new FileReader("diagnosis.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String diagnosisData = bufferedReader.readLine();

            String diagCode = "";

            while (diagnosisData != null){
                String[] splitDiagnosisData = diagnosisData.split(",");
//                System.out.println(splitDiagnosisData[0] + " >> " + splitDiagnosisData[1] + " >> " +  splitDiagnosisData[5]);
//                System.out.println(rowData.getP_Ic() + "    " + rowData.getP_Doa());
                if (splitDiagnosisData[0].equals(rowData.getP_Ic()) && splitDiagnosisData[1].equals(rowData.getP_Doa())){
                    diagCode = splitDiagnosisData[5];
                    // System.out.println(diagCode);
                }
                diagnosisData = bufferedReader.readLine();
            }
            ViewModel.showPatientTreatmentPage(diagCode, rowData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        });

    }
}
