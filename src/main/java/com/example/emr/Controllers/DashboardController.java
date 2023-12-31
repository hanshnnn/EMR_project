package com.example.emr.Controllers;
import com.example.emr.PatientAccHandler;
import com.example.emr.Records.PatientRecord;
import com.example.emr.ViewModel;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

//Extending from tablecell of patient record //
public class DashboardController extends TableCell<PatientRecord, Void> implements Initializable {

        public ImageView patientprofile;
        public ImageView menu;
        public ImageView patientProfile;
        public ImageView home;
        public Pane LivePatientPane;
        public javafx.scene.chart.LineChart LineChart;
        public Label patient_count;
        public Label doctor_count;
        public Pane doctors_count;

        PatientAccHandler patientAccHandler = new PatientAccHandler();
        List<PatientRecord> patientRecords = patientAccHandler.readCSV(PatientRecord.filename);

        private String enteredUsername; // Variable to store the username

        //initialize the dashboard with the provided username. It sets the enteredUsername variable to the given username//
        public void initializeDashboard(String enteredUsername) {
            this.enteredUsername = enteredUsername;
        }
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
        menu.setOnMouseClicked(mouseEvent -> {
            if (enteredUsername.startsWith("patient_")) {
                // Check if the user is logged in as a patient
                // Redirect to patient profile page
                try {
                    patientView();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else if (enteredUsername.startsWith("staff_")) {
                // Check if the user is logged in as a staff member
                // Redirect to staff profile page*/
                ViewModel.getPatientRecord();
            }

        });
        inLineChart();
        calDoctor();
        calPatient();
    }

        //inLineChart() have the function to display the Line Data getting from the number of patients following the date of admission//
        private void inLineChart () {
        LineChart.getXAxis().setLabel("Date Of Admission");
        LineChart.getYAxis().setLabel("Number of Patients");
        LineChart.setTitle("Live Patient Count");
        //sets the labels and title for the line chart and uses a HashMap called doaCounter to count the number of patients for each date of admission//
        Map<String, Integer> doaCounter = new HashMap<>();
        for (PatientRecord patientRecord : patientRecords) {
            String date = patientRecord.getP_Doa();
            // The counter will increment following the date//
            doaCounter.put(date, doaCounter.getOrDefault(date, 0) + 1);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : doaCounter.entrySet()) {
            String date = entry.getKey();
            int count = entry.getValue();
            series.getData().add(new XYChart.Data<>(date, count));
        }
        //populates the line chart with the data from the doaCounter map//
        LineChart.getData().add(series);
    }
        int calPatient(){
        //counter increases and count no. of patient after iterating over the patient records list//
        int counter = 0;
        for(PatientRecord patientRecord : patientRecords){
            counter ++;
            patient_count.setText(String.valueOf(counter));
        }
        return counter;
    }
        int calDoctor() {
        //calculates the number of doctor after iterating over the set DiagnosisController.practitioner//
        int counter = 0;
        for (String s : DiagnosisController.practitioner) {
            counter++;
            doctor_count.setText(String.valueOf(counter));
        }
        return counter;
    }

        // below handles patient side of view
        private void patientView() throws IOException {
            PatientAccHandler p = new PatientAccHandler();
            List<PatientRecord> patients_records = p.readCSV(PatientRecord.filename);
            FileReader fileReader = new FileReader("user_login_data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String eachUserData = bufferedReader.readLine();
            String ic = null;
            while (eachUserData != null) {
                String[] splittedUserData = eachUserData.split(",");
                if(splittedUserData[0].equals(enteredUsername)){
                    ic = splittedUserData[1];
                    break;
                } eachUserData = bufferedReader.readLine();
            }
            for (PatientRecord patient : patients_records){
                if (ic == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("IC not found in database");
                    alert.setContentText("IC not found, please contact hospital admin to check status.");
                    alert.showAndWait();
                    return;
                }
                if (patient.getP_Ic().equals(ic)){
                    ViewModel.patientProfile(patient);
                    break;
                }
            }
        }
}

