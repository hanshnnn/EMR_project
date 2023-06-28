package com.example.emr.ActionCells;

import com.example.emr.Records.PatientRecord;
import com.example.emr.Records.analysisRecord;
import com.example.emr.ViewModel;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;

public class ActionCell3 extends TableCell<analysisRecord, Void> {
    private final Hyperlink view_analysis;
    public String timeLog;
    public ActionCell3(PatientRecord patient){

        view_analysis = new Hyperlink("View");
        view_analysis.setOnAction(e -> {
            analysisRecord rowData = getTableRow().getItem();
            // Displaying start here
            if(rowData.getAnalysisType().equals("Blood")) {
                ViewModel.displayBloodData(patient, rowData.getAnalysisDate());
            } else if (rowData.getAnalysisType().equals("Physical")) {
                ViewModel.displayPhysicalData(patient, rowData.getAnalysisDate());
            } else if (rowData.getAnalysisType().equals("Saliva")) {
                ViewModel.displaySalivaAnalysis(patient, rowData.getAnalysisDate());
            } else if (rowData.getAnalysisType().equals("Urine")) {
                ViewModel.displayUrineAnalysis(patient, rowData.getAnalysisDate());
            } else if (rowData.getAnalysisType().equals("X-Ray")) {
                ViewModel.displayXRayAnalysis(patient, rowData.getAnalysisDate());
            }
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(view_analysis);
        }
    }
}
