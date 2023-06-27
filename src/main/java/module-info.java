module com.example.emr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.emr to javafx.fxml;
    exports com.example.emr;
    exports com.example.emr.Controllers;
    opens com.example.emr.Controllers to javafx.fxml;
    exports com.example.emr.ActionCells;
    opens com.example.emr.ActionCells to javafx.fxml;
}