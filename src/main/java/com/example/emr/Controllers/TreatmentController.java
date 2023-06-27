package com.example.emr.Controllers;

import com.example.emr.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TreatmentController extends PatientProcedureRecordController implements Initializable {

    public Text diagCode_text;
    public Text appointDate_text;
    @FXML
    public Label diagCode_label;
    public ImageView procedureFrame;
    @FXML
    public ImageView image_add;
    @FXML
    public ImageView generateSum_Btn;
    public ImageView image_add1;
    public TableView table_procedure1;
    public TableColumn codeColumn1;
    public TableColumn dateColumn1;
    public TableColumn descriptionColumn1;
    public TableColumn priceColumn1;
    public Text diagCode_text11;
    public Button displayBtn3;
    public Text diagCode_text1;
    public TableView table_medicine;

    @FXML
    private DatePicker datePicker;
    public Tab profile_tab;
    public Tab diagnosis_tab;
    public Tab treatment_tab;

    public String diagnosis;
    public LocalDate selectedDate;
    private LocalDate addedDate = LocalDate.now();;
    @FXML
    private Pane table_pane;
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
    public Button displayBtn2;

    public PatientRecord rowData;
    private PatientProcedureRecordController pprc;
    private PatientProcedureRecord record;
    List<PatientProcedureRecord> matchingIc = new ArrayList<PatientProcedureRecord>();

    List<medicineRecord> matchingIc2 = new ArrayList<medicineRecord>();
    List<PatientProcedureRecord> records = new ArrayList<PatientProcedureRecord>();
    private List<PatientProcedureRecord> searchList = new ArrayList<>();

    private List<medicineRecord> searchList2 = new ArrayList<>();
    private List<PatientProcedureRecord> procedureList = new ArrayList<>();
    ObservableList<medicineRecord> medicineList;
    List<medicineRecord> medicines;
    private medicineHandler mh = new medicineHandler();
    private String medCode;

    ObservableList<com.example.emr.medicineRecord> observableArrayList = FXCollections.observableArrayList();

    //get diag code from diagnosis page
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        diagnosis_tab.setOnSelectionChanged(Event -> {
            ViewModel.showDiagnosisForm(rowData);
        });

        //add_button for procedure//
        image_add.setOnMouseClicked(mouseEvent -> {
            try {
                new ViewModel().showProcedureForm(diagnosis,rowData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        image_add1.setOnMouseClicked(mouseEvent -> {
            new ViewModel().showMedicineForm(rowData);
        });

        displayBtn2.setOnAction(e -> {
            System.out.println("Display button fired");
            System.out.println("treatment " + addedDate);
            //addDays(selectedDate); // Add this line to update the dates
            try {
                displaySavedProc();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        displayBtn3.setOnMouseClicked(mouseEvent -> {
            try {
                displayMedicines();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        table_medicine.setEditable(true);

        TableColumn<com.example.emr.medicineRecord, String> MedicineCode = new TableColumn<com.example.emr.medicineRecord, String>("Medicine Code");
        MedicineCode.setCellValueFactory(new PropertyValueFactory<com.example.emr.medicineRecord, String>("chosenMC"));
        MedicineCode.setCellFactory(TextFieldTableCell.forTableColumn());
        MedicineCode.setOnEditCommit(event -> {
            com.example.emr.medicineRecord patient = event.getRowValue();
            patient.setChosenMC(event.getNewValue());

            // Update the CSV file with the new values
            updateCSV(observableArrayList);
        });


        TableColumn<com.example.emr.medicineRecord, String> duration = new TableColumn<>("Duration");
        duration.setCellValueFactory(new PropertyValueFactory<com.example.emr.medicineRecord, String>("duration"));
        duration.setCellFactory(TextFieldTableCell.forTableColumn());
        duration.setOnEditCommit(event -> {
            com.example.emr.medicineRecord patient = event.getRowValue();
            patient.setDuration(event.getNewValue());

            // Update the CSV file with the new values
            updateCSV(observableArrayList);
        });

        TableColumn<com.example.emr.medicineRecord, String> Quantity = new TableColumn<>("Quantity");
        Quantity.setCellValueFactory(new PropertyValueFactory<com.example.emr.medicineRecord, String>("quantity"));
        Quantity.setCellFactory(TextFieldTableCell.forTableColumn());
        Quantity.setOnEditCommit(event -> {
            com.example.emr.medicineRecord patient = event.getRowValue();
            patient.setQuantity(event.getNewValue());

            // Update the CSV file with the new values
            updateCSV(observableArrayList);
        });

        TableColumn<com.example.emr.medicineRecord, String> prescribedBy = new TableColumn<com.example.emr.medicineRecord, String>("Prescribed By");
        prescribedBy.setCellValueFactory(new PropertyValueFactory<com.example.emr.medicineRecord, String>("chosenDoc"));
        prescribedBy.setCellFactory(TextFieldTableCell.forTableColumn());
        prescribedBy.setOnEditCommit(event -> {
            com.example.emr.medicineRecord patient = event.getRowValue();
            patient.setChosenDoc(event.getNewValue());

            // Update the CSV file with the new values
            updateCSV(observableArrayList);
        });

        TableColumn<com.example.emr.medicineRecord, String> Remarks = new TableColumn<>("Remarks");
        Remarks.setCellValueFactory(new PropertyValueFactory<com.example.emr.medicineRecord, String>("remarks"));
        Remarks.setCellFactory(TextFieldTableCell.forTableColumn());
        Remarks.setOnEditCommit(event -> {
            com.example.emr.medicineRecord patient = event.getRowValue();
            patient.setRemarks(event.getNewValue());

            // Update the CSV file with the new values
            updateCSV(observableArrayList);
        });

        table_medicine.getColumns().addAll(MedicineCode, duration, Quantity, prescribedBy, Remarks);
        medicineHandler pah = new medicineHandler();

        observableArrayList.addAll(pah.readTXT(com.example.emr.medicineRecord.filename));
        System.out.println(observableArrayList);

    }

    //PROCEDURE//
    public void updateLabelText(String diagnosis,PatientRecord rowData) {
        this.rowData = rowData;
        this.diagnosis = diagnosis;
        diagCode_label.setText(diagnosis);
    }

    public void getAppointDate(){
        selectedDate = datePicker.getValue();
        pprc.addDays(selectedDate);
    }

    private void displaySavedProc() throws IOException {
        System.out.println("Treatment: Displaying Procedures");
        // Retrieve the records based on diagnosis and rowData
        records = searchProcCode(diagnosis, rowData);
        //initialised localdate.now to addeddate
        addDays(addedDate);
        System.out.println("treatment added date:"+ addedDate);
        displayProcedures(records);
    }

    //MEDICINE PART//
    private void displayMedicines () throws IOException {
            matchingIc2 = searchIc_Doa_Code(medCode, rowData);
            if (matchingIc2.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("No medicine records found");
                alert.show();
            } else {
                displayMedicines(matchingIc2);
            }
        }

        private void displayMedicines (List < medicineRecord > medicines) {
            if (medicines.isEmpty()) {
            } else {
                medicineList = FXCollections.observableArrayList();
                for (medicineRecord medicine : medicines) {
                    medicineRecord updatetedMeds = new medicineRecord(medicine.getChosenMC(), medicine.getDuration(), medicine.getQuantity(), medicine.getChosenDoc(), medicine.getRemarks());
                    medicineList.add(updatetedMeds);
                }
            }
            table_medicine.setItems(medicineList);
        }

    public void updateCSV(List<com.example.emr.medicineRecord> records) {
        try {
            medicineHandler pah = new medicineHandler();
            pah.writeTXT(records, com.example.emr.medicineRecord.filename);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //search ic, doa & med code from patientmedicine.csv
    public List<medicineRecord> searchIc_Doa_Code(String medCode, PatientRecord rowData) throws IOException {
        this.medCode = medCode;
        String ic = rowData.getP_Ic();
        String doa = rowData.getP_Doa();
        matchingIc2.clear();
        searchList2 = mh.readTXT(medicineRecord.filename);
        // Search for the procedure code based on the diagnosis
        for (medicineRecord medicineRecord : searchList2) {
            if (medicineRecord.getP_ic() != null && medicineRecord.getP_Doa() != null && medicineRecord.getP_ic().equals(ic) && medicineRecord.getP_Doa().equals(doa)) {
                matchingIc2.add(medicineRecord);
            }
        }
        return matchingIc2;
    }

}

