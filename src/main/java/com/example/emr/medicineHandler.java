package com.example.emr;

import com.example.emr.Records.PatientRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class medicineHandler {




    private String delimiter = ",";
    private String newline = "\n";
    private String header = "IC,DOA,Medicine Code,Duration,Quantity,Prescribed By,Remarks";

    public medicineHandler() {
    }


    public medicineHandler(String header, String delimiter, String newline) {
        this.header = header;
        this.delimiter = delimiter;
        this.newline = newline;
    }


    public void writeTXT(List<medicineRecord> procedures, String filename) throws IOException {
        PatientRecord patientRecord = new PatientRecord();
        FileWriter procedure_file = null;
        try {
            File file = new File(filename);
            procedure_file = new FileWriter(file);
            procedure_file.append(header);
            procedure_file.append(newline);

            for (medicineRecord mRs : procedures) {
                String lineString = mRs.getP_ic() + delimiter + mRs.getP_Doa() + delimiter + mRs.getChosenMC() + delimiter + mRs.getDuration() + delimiter + mRs.getQuantity() + delimiter + mRs.getChosenDoc() +
                        delimiter + mRs.getRemarks() + newline;
                if (!lineString.trim().isEmpty() &&
                        !mRs.getP_ic().isEmpty() &&
                        !mRs.getP_Doa().isEmpty() &&
                        !mRs.getChosenMC().isEmpty() &&
                        !mRs.getDuration().isEmpty() &&
                        !mRs.getQuantity().isEmpty() &&
                        !mRs.getChosenDoc().isEmpty() &&
                        !mRs.getRemarks().isEmpty()) {
                    procedure_file.write(lineString);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert procedure_file != null;
                procedure_file.flush();
                procedure_file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void create(medicineRecord procedureRecord) throws IOException {
        List<medicineRecord> procedures = readTXT(medicineRecord.filename);
        procedures.add(procedureRecord);
        Collections.sort(procedures);
        writeTXT(procedures, medicineRecord.filename);
    }


    public List<medicineRecord> readTXT(String fileName) {
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<medicineRecord> procedures = new ArrayList<medicineRecord>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] values = line.split(delimiter);
                if (values.length >= 0) {
                    // content separated by delimeter (normally, a comma ',')
                    medicineRecord procedure = new medicineRecord(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
                    procedures.add(procedure);
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
        return procedures;
    }


    public List<medicineRecord> readFixedProc(String fileName) {
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<medicineRecord> procedures = new ArrayList<medicineRecord>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] values = line.split(delimiter);
                if (values.length >= 0) {
                    // content separated by delimeter (normally, a comma ',')
                    medicineRecord procedure = new medicineRecord(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
                    procedures.add(procedure);
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
        return procedures;
    }




}