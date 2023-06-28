package com.example.emr;

import com.example.emr.Controllers.*;
import com.example.emr.Records.PatientRecord;
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

    public static void showLogin() throws Exception {
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

    public static void getPatientRecord() {
        try {
            FXMLLoader patientRecord = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/pForm.fxml"));
            Parent my_root = patientRecord.load();
           // Stage stage = new Stage();
            stage1.setTitle("PatientRecord");
            stage1.setScene(new Scene(my_root));
            stage1.show();
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
            Scene scene = new Scene(my_root);
            scene.getStylesheets().add(ViewModel.class.getResource("dashboard.css").toExternalForm());
            stage1.setTitle("PatientRecord");
            stage1.setScene(scene);
            stage1.show();

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

    //below functions are all related to respective analysis
    public static void showAnalysisBlank(PatientRecord rowdata) {
        try {
            FXMLLoader analysisBlank_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/Analysis_blank.fxml"));
            AnchorPane analysis_pane = analysisBlank_loader.load();
            analysisBlankController analysisController = analysisBlank_loader.getController();
            analysisController.initialize(rowdata);

            Scene newScene = new Scene(analysis_pane);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Adding analysis");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // hi a marking cuz i demn sleepy
    public static void showBloodAnalysis(PatientRecord rowdata){
        try {
            FXMLLoader blood_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/bloodTest.fxml"));
            AnchorPane blood_pane = blood_loader.load();
            bloodTestController bloodTestController = blood_loader.getController();
            bloodTestController.setRowdata(rowdata);
            bloodTestController.setButton(rowdata);

            Scene newScene = new Scene(blood_pane);
            stage.setScene(newScene);
            stage.setTitle("Blood Analysis");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayBloodData(PatientRecord rowdata, String time_log){
        try {
            FXMLLoader blood_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/bloodTest.fxml"));
            AnchorPane blood_pane = blood_loader.load();
            bloodTestController BloodTestController = blood_loader.getController();
            BloodTestController.setRowdata(rowdata);
            BloodTestController.displayBlood(rowdata, time_log);

            Scene newScene = new Scene(blood_pane);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Blood Analysis");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showPhysicalAnalysis(PatientRecord rowdata){
        try {
            FXMLLoader physical_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/Physical_analysis.fxml"));
            AnchorPane physical_pane = physical_loader.load();
            physicalTestController physicalTestController = physical_loader.getController();
            physicalTestController.setRowdata(rowdata);
            physicalTestController.setButton(rowdata);

            Scene newScene = new Scene(physical_pane);
            stage.setScene(newScene);
            stage.setTitle("Physical Analysis");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayPhysicalData(PatientRecord rowdata, String time_log){
        try {
            FXMLLoader physical_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/Physical_analysis.fxml"));
            AnchorPane physical_pane = physical_loader.load();
            physicalTestController PhysicalTestController = physical_loader.getController();
            PhysicalTestController.setRowdata(rowdata);
            PhysicalTestController.displayPhysical(rowdata, time_log);

            Scene newScene = new Scene(physical_pane);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Physical Analysis");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSalivaAnalysis(PatientRecord rowdata){
        try {
            FXMLLoader saliva_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/Saliva.fxml"));
            AnchorPane saliva_pane = saliva_loader.load();
            salivaTestController salivaTestController = saliva_loader.getController();
            salivaTestController.setRowdata(rowdata);
            salivaTestController.setButton(rowdata);

            Scene newScene = new Scene(saliva_pane);
            stage.setScene(newScene);
            stage.setTitle("Saliva Analysis");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displaySalivaAnalysis(PatientRecord rowdata, String time_log){
        try {
            FXMLLoader saliva_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/Saliva.fxml"));
            AnchorPane saliva_pane = saliva_loader.load();
            salivaTestController SalivaTestController = saliva_loader.getController();
            SalivaTestController.setRowdata(rowdata);
            SalivaTestController.displaySaliva(rowdata, time_log);

            Scene newScene = new Scene(saliva_pane);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Saliva Analysis");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showUrineAnalysis(PatientRecord rowdata){
        try {
            FXMLLoader urine_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/urine.fxml"));
            AnchorPane urine_pane = urine_loader.load();
            urineTestController urineTestController = urine_loader.getController();
            urineTestController.setRowdata(rowdata);
            urineTestController.setButton(rowdata);

            Scene newScene = new Scene(urine_pane);
            stage.setScene(newScene);
            stage.setTitle("Urine Analysis");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayUrineAnalysis(PatientRecord rowdata, String time_log){
        try {
            FXMLLoader urine_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/urine.fxml"));
            AnchorPane urine_pane = urine_loader.load();
            urineTestController UrineTestController = urine_loader.getController();
            UrineTestController.setRowdata(rowdata);
            UrineTestController.displayUrine(rowdata, time_log);

            Scene newScene = new Scene(urine_pane);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Urine Analysis");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showXRayAnalysis(PatientRecord rowdata){
        try {
            FXMLLoader xray_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/x-ray.fxml"));
            AnchorPane xray_pane = xray_loader.load();
            XRayTestController xrayTestController = xray_loader.getController();
            xrayTestController.setRowdata(rowdata);
            xrayTestController.setButton(rowdata);

            Scene newScene = new Scene(xray_pane);
            stage.setScene(newScene);
            stage.setTitle("X-Ray Analysis");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayXRayAnalysis(PatientRecord rowdata, String time_log){
        try {
            FXMLLoader xray_loader = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/x-ray.fxml"));
            AnchorPane xray_pane = xray_loader.load();
            XRayTestController xrayTestController = xray_loader.getController();
            xrayTestController.setRowdata(rowdata);
            xrayTestController.displayXRay(rowdata, time_log);

            Scene newScene = new Scene(xray_pane);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("X-Ray Analysis");
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
    public static void patientProfile(com.example.emr.Records.PatientRecord rowData) throws IOException {
        try {
            FXMLLoader patientProfile = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/pProfile_doc.fxml"));
            //Parent my_root = (Parent) patientProfile.load();
            AnchorPane profile_pane = patientProfile.load();
            pProfileController controller = patientProfile.getController();
            controller.patient_view(rowData);

            Scene newScene = new Scene(profile_pane);
            newScene.getStylesheets().add(ViewModel.class.getResource("Patient.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Welcome, " + rowData.getP_Name());
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //navigates and load Patient Profile//
    public static void showPatientProfile(com.example.emr.Records.PatientRecord rowData) {
        try {
            FXMLLoader patientProfile = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/pProfile_doc.fxml"));
            //Parent my_root = (Parent) patientProfile.load();
            AnchorPane profile_pane = patientProfile.load();
            pProfileController controller = patientProfile.getController();
            controller.displayinfo(rowData);
            controller.filterData(rowData);
            Scene newScene = new Scene(profile_pane);
            newScene.getStylesheets().add(ViewModel.class.getResource("Patient.css").toExternalForm());

            // Stage stage = new Stage();
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
            diagnosisController.initialize(rowData);

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
            tc.initialize(rowData);
            tc.updateLabelText(diagnosis, rowData);
            //tc.searchIc(rowData);
            stage.setTitle("Treatment");
            stage.setScene(treatmentScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showPatientTreatmentPage(String diagnosis, PatientRecord rowData){
        try{
            FXMLLoader T_page = new FXMLLoader(ViewModel.class.getResource("/com/example/emr/treatment.fxml"));
            AnchorPane treatment_pane = T_page.load();
            Scene treatmentScene = new Scene(treatment_pane);
            treatmentScene.getStylesheets().add(ViewModel.class.getResource("treatment.css").toExternalForm());
            TreatmentController tc = T_page.getController();

            tc.initializePatientView(diagnosis, rowData);

            stage.setTitle("Treatment Summary");
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
            pprc.initialize(rowData);
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
