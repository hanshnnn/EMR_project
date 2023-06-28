package com.example.emr;

import com.example.emr.Records.PatientRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientProcHandler {
    private String delimiter = ",";
    private String newline = "\n";
    private String header = "IC,Doa, Code, Date, Description, Price";

    public PatientProcHandler() {
    }

    public PatientProcHandler(String header, String delimiter, String newline) {
        this.header = header;
        this.delimiter = delimiter;
        this.newline = newline;
    }

    public void writeCSV(List<PatientProcedureRecord> procedures, String filename) throws IOException {
        FileWriter procedure_file = null;
        PatientRecord patientRecord = new PatientRecord();
        try {
            File file = new File(filename);
            procedure_file = new FileWriter(file);
            procedure_file.append(header);
            procedure_file.append(newline);

            //process content line by line
            for (PatientProcedureRecord patientProcedureRecord : procedures) {
                procedure_file.append(patientProcedureRecord.getP_ic());
                procedure_file.append(delimiter);
                procedure_file.append(patientProcedureRecord.getP_doa());
                procedure_file.append(delimiter);
                procedure_file.append(patientProcedureRecord.getProcedure_code());
                procedure_file.append(delimiter);
                procedure_file.append(patientProcedureRecord.getProcedure_date());
                procedure_file.append(delimiter);
                procedure_file.append(patientProcedureRecord.getProcedure_description());
                procedure_file.append(delimiter);
                procedure_file.append(patientProcedureRecord.getProcedure_price());
                procedure_file.append(newline);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                procedure_file.flush();
                procedure_file.close();
            } catch (IOException e) {
                // handle exception
                e.printStackTrace();
            }
        }
    }
    //need to set the list of fixed procedures first

    public void create(PatientProcedureRecord procedureRecord) throws IOException {
        List<PatientProcedureRecord> procedures = readCSV(PatientProcedureRecord.filename);
        procedures.add(procedureRecord);
        Collections.sort(procedures);
        writeCSV(procedures, PatientProcedureRecord.filename);
    }

    public List<PatientProcedureRecord> readCSV(String fileName) {
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<PatientProcedureRecord> procedures = new ArrayList<PatientProcedureRecord>();

        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] values = line.split(delimiter);
                if (values.length >= 6) {
                    // content separated by delimeter (normally, a comma ',')
                    PatientProcedureRecord procedure = new PatientProcedureRecord(values[0],values[1],values[2],values[3],values[4], values[5]);
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

    public List<PatientProcedureRecord> readFixedProc(String fileName) {
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<PatientProcedureRecord> procedures = new ArrayList<PatientProcedureRecord>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] values = line.split(delimiter);
                if (values.length >= 0) {
                    // content separated by delimeter (normally, a comma ',')
                    PatientProcedureRecord procedure = new PatientProcedureRecord(values[0],values[1],values[2],values[3]);
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
