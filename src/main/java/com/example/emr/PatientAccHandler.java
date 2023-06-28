package com.example.emr;

import com.example.emr.Records.PatientRecord;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientAccHandler {
    private String delimiter = ",";
    private String newline = "\n";
    //need delete id from the header
    private String header = "ic,doa,name,dob,gender,address,allergies,pmr";

    public PatientAccHandler() {
    }

    public void writeCSV(List<PatientRecord> patients, String filename) throws IOException {
        FileWriter patient_file = null;
        //dir will change directory and specifies file name for writer
        try {
            File file = new File(filename);
            patient_file = new FileWriter(file);
            patient_file.append(header);
            patient_file.append(newline);

            for (PatientRecord patientRecord : patients) {
                patient_file.append(patientRecord.getP_Ic());
                patient_file.append(delimiter);
                patient_file.append(patientRecord.getP_Doa().toString());
                patient_file.append(delimiter);
                patient_file.append(patientRecord.getP_Name());
                patient_file.append(delimiter);
                patient_file.append(patientRecord.getP_Dob().toString());
                patient_file.append(delimiter);
                patient_file.append(patientRecord.getP_Gender());
                patient_file.append(delimiter);
                patient_file.append(patientRecord.getP_Address());
                patient_file.append(delimiter);
                patient_file.append(patientRecord.getP_Allergies());
                patient_file.append(delimiter);
                patient_file.append(patientRecord.getP_Pmr());
                patient_file.append(newline);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert patient_file != null;
                patient_file.flush();
                patient_file.close();
            } catch (IOException e) {
                // handle exception
                e.printStackTrace();
            }
        }
    }

    public void create(PatientRecord o) throws IOException {
        List<PatientRecord> patients = this.readCSV(PatientRecord.filename);
        patients.add(o);
        Collections.sort(patients);
        writeCSV(patients, PatientRecord.filename);
    }

    public List<PatientRecord> readCSV(String fileName) {
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<PatientRecord> patients = new ArrayList<PatientRecord>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] values = line.split(delimiter);

                // convert string into Local Date format
                LocalDate doa = LocalDate.parse(values[1]);
                LocalDate dob = LocalDate.parse(values[3]);

                if (values.length > 0) {
                    // content separated by delimeter (normally, a comma ',')
                    PatientRecord patient = new PatientRecord(values[0],doa,values[2],dob,values[4],values[5],values[6],values[7]);
                    patients.add(patient);
                }
            }
        } catch (FileNotFoundException e) {
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
        return patients;
    }

    public void delete(String ic, String fileName) throws IOException {
        List<PatientRecord> patients = readCSV(fileName);
        for (PatientRecord patient: patients) {
            if (patient.getP_Ic().equals(ic)) {
                patients.remove(patient);
                break;
            }
        }
        Collections.sort(patients);
        writeCSV(patients, fileName);
    }


}








