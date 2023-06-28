package com.example.emr.Records;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class analysisRecord implements Comparable<analysisRecord> {
    private String delimiter = ",";
    private String newline = "\n";
    private String header = "patientIC, patientDoa, analysisType, analysisTimeLog";

    private String p_Ic;
    private String p_Doa;
    private String analysisType;
    private String analysisDate;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static String filename = "analysis.csv";

    public analysisRecord(){

    }
    public analysisRecord(String patientIC, String patientDoA, String analysisType){
        this.setP_Ic(patientIC);
        this.setP_Doa(patientDoA);
        this.setAnalysisType(analysisType);

        this.setAnalysisDate(analysisRecord.dateFormat.format(new Date()));
    }

    public analysisRecord(String patientIC, String patientDoA, String analysisType, Date Log){
        this.setP_Ic(patientIC);
        this.setP_Doa(patientDoA);
        this.setAnalysisType(analysisType);

        this.setAnalysisDate(analysisRecord.dateFormat.format(Log));
    }

    public void writeAnalysis(){
        FileWriter fileWriter = null;
        try {
            File file = new File(filename);
            fileWriter = new FileWriter(file, true);    // not overwriting

            fileWriter.append(this.p_Ic);
            fileWriter.append(delimiter);
            fileWriter.append(this.p_Doa.toString());
            fileWriter.append(delimiter);
            fileWriter.append(this.analysisType);
            fileWriter.append(delimiter);
            fileWriter.append(this.analysisDate);
            fileWriter.append(newline);

        } catch (Exception e) {
            // handle any potential exception
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace(); }
        }
    }

    public List<analysisRecord> readCSV(String filename){
        BufferedReader bReader = null;
        File file = new File(filename);
        List<analysisRecord> analysis = new ArrayList<analysisRecord>();
        try {
            String line ="";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);

                // convert string into Date format
                Date timeLog = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(tokens[3]);

                if (tokens.length > 0){
                    analysisRecord analysisLog = new analysisRecord(tokens[0], tokens[1], tokens[2], timeLog);
                    analysis.add(analysisLog);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return analysis;
    }

    // implements interface Comparable
    @Override
    public int compareTo(analysisRecord a) {
        return getAnalysisDate().compareTo(a.getAnalysisDate());
    }

    // getters setters
    public String getP_Ic() {
        return p_Ic;
    }

    public void setP_Ic(String p_Ic) {
        this.p_Ic = p_Ic;
    }

    public String getP_Doa() {
        return p_Doa;
    }

    public void setP_Doa(String p_Doa) {
        this.p_Doa = p_Doa;
    }

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public String getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(String analysisDate) {
        this.analysisDate = analysisDate;
    }
}

