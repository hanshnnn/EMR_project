package com.example.emr;

import com.example.emr.Records.bloodResults;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class bloodCSV {
    private final String delimiter = ",";
    private final String newline = "\n";
    private final String header = "p_ID,p_Doa,p_timeLog,Rbc,Platelets,bloodgluLev,t4lev,thslev,haemoglev,choles_lev,t3lev,WBC,Remarks";

    public bloodCSV() {
    }

    public void writeBlood(List<bloodResults> bloodResultss, String fileName, String timeLog) {
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            for (bloodResults bloodResult : bloodResultss) {
                fileWriter.append(bloodResult.getP_IC());
                fileWriter.append(delimiter);
                fileWriter.append(bloodResult.getP_Doa().toString());
                fileWriter.append(delimiter);
                fileWriter.append(bloodResult.getTimeLog());
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(bloodResult.getRbc()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(bloodResult.getPlatelets_count()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(bloodResult.getBloodGluLev()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(bloodResult.getT4Lev()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(bloodResult.getTshLev()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(bloodResult.getHaemogLev()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(bloodResult.getCholes_Lev()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(bloodResult.getT3Lev()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(bloodResult.getCholes_Lev()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(bloodResult.getRemarks()));
                fileWriter.append(newline);

            }
        } catch (Exception e) {
            // handle any potential exception
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<bloodResults> readBlood(String fileName){
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<bloodResults> bloodResults = new ArrayList<bloodResults>();
        try {
            String line;
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);

                //convert date...
                LocalDate doa = LocalDate.parse(tokens[1]);

                // to convert all the strings back to double...
                double rbc =  Double.parseDouble(tokens[3]);
                double platelets_count = Double.parseDouble(tokens[4]);
                double bloodGluLev = Double.parseDouble(tokens[5]);
                double t4Lev = Double.parseDouble(tokens[6]);
                double tshLev = Double.parseDouble(tokens[7]);
                double haemogLev = Double.parseDouble(tokens[8]);
                double choles_Lev = Double.parseDouble(tokens[9]);
                double t3Lev = Double.parseDouble(tokens[10]);
                double wbc = Double.parseDouble(tokens[11]);

                if (tokens.length > 0){
                    bloodResults bloodLog = new bloodResults(tokens[0], doa, tokens[2], rbc, platelets_count, bloodGluLev, t4Lev, tshLev,
                            haemogLev, choles_Lev, t3Lev, wbc, tokens[12]);
                    bloodResults.add(bloodLog);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
        }

        return bloodResults;

    }

}
