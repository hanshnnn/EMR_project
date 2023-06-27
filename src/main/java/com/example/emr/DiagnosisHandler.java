package com.example.emr;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiagnosisHandler {
    private String delimiter = ",";
    private String newline = "\n";
    private String header = "ic,doa,symptoms,remark,practitioner,diagnosis_code ";

    public DiagnosisHandler(){}

    public DiagnosisHandler(String header,String delimiter , String newline){
        this.header = header;
        this.delimiter = delimiter;
        this.newline = newline;
    }

    public void writeCSV(List<PatientDiagnosis> diagnosisList, String filename){
        FileWriter diagnosis_file = null;
        //dir will change directory and specifies file name for writer
        try {
            File file = new File(filename);
            diagnosis_file = new FileWriter(file);
            diagnosis_file.append(header);
            diagnosis_file.append(newline);

            for (PatientDiagnosis patientDiagnosis : diagnosisList) {
                String lineString = patientDiagnosis.getP_ic() + delimiter +
                        patientDiagnosis.getP_doa() + delimiter +
                        formatList(patientDiagnosis.getP_symptoms()) + delimiter +
                        formatField(patientDiagnosis.getP_remark()) + delimiter +
                        formatField(patientDiagnosis.getPractitioner()) + delimiter +
                        formatField(patientDiagnosis.getDiagnosis_code()) + newline;
                if (!lineString.trim().isEmpty() &&
                        !patientDiagnosis.getP_symptoms().isEmpty() &&
                        !patientDiagnosis.getP_remark().isEmpty() &&
                        !patientDiagnosis.getPractitioner().isEmpty() &&
                        !patientDiagnosis.getDiagnosis_code().isEmpty()) {
                    diagnosis_file.write(lineString);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert diagnosis_file != null;
                diagnosis_file.flush();
                diagnosis_file.close();
            } catch (IOException e) {
                // handle exception
                e.printStackTrace();
            }
        }
    }

    private String formatList(List<String> values) {
        if (values != null && !values.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (String value : values) {
                builder.append(value).append(" ");
            }
            return builder.toString().trim();
        }
        return "";
    }

    private String formatField(String value) {
        return value != null ? value : "";
    }
        public void create(PatientDiagnosis p) throws IOException {
        List<PatientDiagnosis> diagnosisList = this.readCSV(PatientDiagnosis.filename);
        diagnosisList.add(p);
        //Collections.sort(diagnosisList);
        writeCSV(diagnosisList, PatientDiagnosis.filename);
    }

    public List<PatientDiagnosis> readCSV(String fileName) {
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<PatientDiagnosis> diagnosisList = new ArrayList<PatientDiagnosis>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
                while ((line = bReader.readLine()) != null) {
                    String[] values = line.split(delimiter);
                    if (values.length >= 3) {
                        ArrayList<String> symptomsList = new ArrayList<>(Collections.singleton(values[2]));
                        // content separated by delimeter (normally, a comma ',')
                        PatientDiagnosis patientDiagnosis = new PatientDiagnosis(values[0], values[1], symptomsList, values[3], values[4], values[5]);
                        diagnosisList.add(patientDiagnosis);
                    }
                }
            }
         catch (FileNotFoundException e) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null)
                    bReader.close();
            } catch (IOException e) {
                // handle exception
                e.printStackTrace();
            }
        }
        return diagnosisList;
    }
}
