package com.example.emr.Controllers;
import com.example.emr.PatientAccHandler;
import com.example.emr.Records.PatientRecord;
import com.example.emr.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    public Label label_name;
    public TextField textfield_name;
    public Label label_icno;
    public TextField textfield_icno;
    public Label label_DOB;
    public Label label_gender2;
    public ChoiceBox Choicebox_gender;
    public Label Address_label;
    public TextField textfield_address;
    public Label label_allergies;
    public ChoiceBox ChoiceBox_allergies;

    public Label label_dateofadmission;
    public Label label_pastmedicalrecord;
    public Button submit_Btn;
    public Button next_Btn;
    public ImageView image_imageview;
    public Label label_uploadimage;
    public AnchorPane registration_form;
    public String[] gender = {"male","female"};
    public String[] allergies = {"nuts","dairy products","seafood","pineapple","NONE"};
    public TextArea textarea_pmr;
    public DatePicker textfield_dob;
    public DatePicker textfield_doa;
    private File ImageFile;


    public class registrationController {
        @FXML
        private Label Address_label;

        @FXML
        private ChoiceBox<?> ChoiceBox_allergies;

        @FXML
        private ChoiceBox<?> Choicebox_gender;

        @FXML
        private ImageView image_imageview;

        @FXML
        private Label label_DOB;

        @FXML
        private Label label_allergies;

        @FXML
        private Label label_dateofadmission;

        @FXML
        private Label label_gender2;

        @FXML
        private Label label_icno;

        @FXML
        private Label label_name;

        @FXML
        private Label label_pastmedicalrecord;

        @FXML
        private Label label_uploadimage;

        @FXML
        private Rectangle rectangle_image;

        @FXML
        private AnchorPane registration_form;

        @FXML
        private Button submit_Btn;

        @FXML
        private TextField textfield_address;

        @FXML
        private TextField textfield_dateofadmission;

        @FXML
        private TextField textfield_dob;

        @FXML
        private TextField textfield_icno;

        @FXML
        private TextField textfield_name;

        @FXML
        private TextField textfield_pastmedicalrecord;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Choicebox_gender.getItems().addAll(gender);
        ChoiceBox_allergies.getItems().addAll(allergies);

        label_uploadimage.setOnMouseClicked(event->handleUploadImage());

        submit_Btn.setOnAction(e-> {
            try {
                createPatient();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    private void handleUploadImage() {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Please select the image file");
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File ImageFile = filechooser.showOpenDialog(null);

        if (ImageFile != null) {
            String fileName = textfield_name.getText() + ".jpg";
            String destinationPath = "ImageFile/" + fileName;
            File destinationFile = new File(destinationPath);
            Image img = new Image(ImageFile.toURI().toString());
            image_imageview.setImage(img);

            try {
                Files.copy(ImageFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                ImageFile = destinationFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createPatient() throws IOException {
        String ic = textfield_icno.getText();
        LocalDate doa = textfield_doa.getValue();
        String name = textfield_name.getText();
        LocalDate dob = textfield_dob.getValue();
        String address = textfield_address.getText();
        String gender = (String) Choicebox_gender.getValue();
        String allergies = (String) ChoiceBox_allergies.getValue();
        String pmr = textarea_pmr.getText();
        PatientRecord patientRecord = new PatientRecord(ic,doa,name,dob,gender,address,allergies,pmr);

        if (ic.isEmpty() || name.isEmpty() || dob == null  || address.isEmpty() || doa == null ||  gender.isEmpty() || allergies.isEmpty() || pmr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the required fields.");
            alert.showAndWait();
            return;
        }
        String icNum = "YYMMDD";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");

        String date = ic.substring(0,6);

        try {

            LocalDate parsedDate = LocalDate.parse(date, formatter);

            String formattedDate = parsedDate.format(formatter);

            if (ic.length() != 12 || !date.equals(formattedDate)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("IC must be in the format: " + icNum);
                alert.showAndWait();
                return;
            }
        } catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid IC format. IC must be in the format: " + icNum);
            alert.showAndWait();
            return;
        }
        if (this.ImageFile != null) {
            String imagePath = "ImageFile/" + generateImageName();
            File destinationFile = new File(imagePath);

            try {
                Files.copy(this.ImageFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                patientRecord.setImagePath(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        PatientAccHandler pah = new PatientAccHandler();
        pah.create(patientRecord);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Info saved");
        Optional<ButtonType> option_btn = alert.showAndWait();

        next_Btn.setOnAction(e -> {
            try {
                System.out.println("next btn clicked");
                new ViewModel().showMedicalHistoryForm(ic, doa.toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }


    private String generateImageName() {
        return "image_" + System.currentTimeMillis() + ".jpg";
    }
}
