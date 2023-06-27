package com.example.emr.ActionCells;

import com.example.emr.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;

public class ActionCell2 extends TableCell<PatientRecord,Void> {
   private final Hyperlink view_text;
    public ActionCell2(){
        view_text = new Hyperlink("View");
        view_text.setOnAction(event -> {
            PatientRecord rowData = getTableRow().getItem();
            // Navigation start here
            ViewModel.showPatientProfile(rowData);
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(view_text);
        }
    }
    }
