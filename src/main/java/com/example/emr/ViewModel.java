package com.example.emr;

import com.example.emr.Controllers.*;
import javafx.application.Preloader;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewModel {
    public AnchorPane PatientRecord;
    public static TabPane tabPane;

    public static Stage stage;

    public static Stage stage1;


    public ViewModel() {
        tabPane = new TabPane();
        stage = new Stage();
        stage1 = new Stage();
    }

    public void showLogin() throws Exception {
        stage1.setResizable(false);
        Parent root = FXMLLoader.load(ViewModel.class.getResource("hello-view.fxml"));
        stage1.setTitle("Electronic Medical Record");
        stage1.setScene(new Scene(root, 1280, 720));
        stage1.show();
    }

    public void changeToRegistration() throws IOException {
        FXMLLoader R_form = new FXMLLoader(getClass().getResource("RegistrationGiselle.fxml"));
        Parent r_root = R_form.load();
       // Stage stage = new Stage();
        stage.setTitle("Registration");
        stage.setScene(new Scene(r_root));
        stage.show();
    }

    public  void getPatientRecord() {
        try {
            FXMLLoader patientRecord = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/pForm.fxml"));
            Parent my_root = patientRecord.load();
           // Stage stage = new Stage();
            stage.setTitle("PatientRecord");
            stage.setScene(new Scene(my_root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDashboard(String enteredUsername) throws IOException{
        try {
            FXMLLoader patientRecord = new FXMLLoader(getClass().getResource("/com/example/emr/dashboard.fxml"));
            Parent my_root = patientRecord.load();

            DashboardController controller = patientRecord.getController();
            controller.initializeDashboard(enteredUsername);

           // Stage stage = new Stage();
            stage.setTitle("PatientRecord");
            Scene scene = new Scene(my_root);
            scene.getStylesheets().add(ViewModel.class.getResource("dashboard.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showRegistrationForm() throws IOException {
        try {
            FXMLLoader R_form = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/registrationform.fxml"));
            Parent r_root = R_form.load();
           // Stage stage = new Stage();
            stage.setTitle("RegistrationForm");
            stage.setScene(new Scene(r_root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void showMedicalHistoryForm(String ic, String doa) throws IOException {
        try {
            FXMLLoader MH_form = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/medicalHistory.fxml"));
            Parent mh_root = MH_form.load();
            MedicalHistoryController controller = MH_form.getController();
            controller.setIc(ic);
            controller.setDoa(doa);
            Stage stage = new Stage();
            stage.setTitle("MedicalHistoryForm");
            stage.setScene(new Scene(mh_root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //navigates and load patient profile //
    public void patientProfile() throws IOException {
        try {
            FXMLLoader R_form = new FXMLLoader(getClass().getResource("/com/example/emr/pProfile_doc.fxml"));
            Parent r_root = R_form.load();
            //Stage stage = new Stage();
            stage.setTitle("RegistrationForm");
            stage.setScene(new Scene(r_root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //navigates and load Patient Profile//
    public static void showPatientProfile(com.example.emr.PatientRecord rowData) {
        try {
            FXMLLoader patientProfile = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/pProfile_doc.fxml"));
            //Parent my_root = (Parent) patientProfile.load();
            AnchorPane profile_pane = patientProfile.load();
            pProfileController controller = patientProfile.getController();
            controller.displayinfo(rowData);
            Scene newScene = new Scene(profile_pane);
            newScene.getStylesheets().add(ViewModel.class.getResource("Patient.css").toExternalForm());

            //Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Patient Profile");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //navigates and load diagnosis.fxml //
    public static void showDiagnosisForm(PatientRecord rowData) {
        try {
            FXMLLoader diagnosis_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/Diagnosis.fxml"));
            AnchorPane diagnosis_pane = diagnosis_loader.load();
            DiagnosisController diagnosisController = diagnosis_loader.getController();

            // Create an instance of PatientProcedureRecordController
            PatientProcedureRecordController patientProcedureRecordController = new PatientProcedureRecordController();
            // Set the instance in DiagnosisController
            diagnosisController.setPatientProcedureRecordController(patientProcedureRecordController);

            diagnosisController.creatediagnosis(rowData);
            Scene newScene = new Scene(diagnosis_pane);
            stage.setScene(newScene);
            stage.setTitle("Diagnosis Form");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void showTreatmentPage(String diagnosis, PatientRecord rowData) throws IOException {
        try {
            FXMLLoader T_page = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/treatment.fxml"));
            AnchorPane treatment_pane = T_page.load();
            Scene treatmentScene = new Scene(treatment_pane);
            treatmentScene.getStylesheets().add(ViewModel.class.getResource("treatment.css").toExternalForm());
            TreatmentController tc = T_page.getController();
            tc.updateLabelText(diagnosis, rowData);
            //tc.searchIc(rowData);
            stage.setTitle("Treatment");
            stage.setScene(treatmentScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showProcedureForm(String diagnosis, PatientRecord rowData) throws IOException {
        try {
            FXMLLoader P_form = new FXMLLoader(ViewModel.class.getResource("Procedure_form.fxml"));
            Parent r_root = (Parent) P_form.load();
            PatientProcedureRecordController pprc = P_form.getController();
            pprc.searchProcCode(diagnosis, rowData);
            //pprc.displayProcedures();


           // Stage stage = new Stage();
            stage.setTitle("Procedure");
            Scene procedureScene= new Scene(r_root);
            r_root.getStylesheets().add(ViewModel.class.getResource("procedure.css").toExternalForm());
            stage.setScene(procedureScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void showMedicineForm(PatientRecord rowData) {
        try {
            FXMLLoader medicineForm_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/Medicine_form.fxml"));
            AnchorPane medicineForm_pane = medicineForm_loader.load();
            PatientMedicineRecordController mFC = medicineForm_loader.getController();
            mFC.saveMedicine(rowData);
            Scene newScene = new Scene(medicineForm_pane);
            stage.setScene(newScene);
            stage.setTitle("Medicine Form");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
