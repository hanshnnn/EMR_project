package com.example.emr.ActionCells;

import com.example.emr.PatientAccHandler;
import com.example.emr.Records.PatientRecord;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

import java.io.File;
import java.io.IOException;

public class ActionCell extends TableCell<PatientRecord,Void> {
    private final Button deleteButton;

    public ActionCell(ObservableList<PatientRecord> patientRecordObservableList) {
        deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            PatientRecord rowData = getTableRow().getItem();
            patientRecordObservableList.remove(rowData);  // Use the provided ObservableList to remove the item
            PatientAccHandler pah = new PatientAccHandler();
            String imagePath = rowData.getImagePath();

            if (imagePath != null) {
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    imageFile.delete();
                }
            }
            try {
                pah.delete(rowData.getP_Ic(), PatientRecord.filename);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(deleteButton);
        }
    }
}
