package com.example.emr;

import com.example.emr.Records.physicalResults;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PhysicalCSV {
    private final String delimiter = ",";
    private final String newline = "\n";
    private final String header = "p_ID,p_Doa,p_timeLog,bloodPressure,heartRate,bodyTemp,Height,height_index,BMI,Weight,weight_index,Remark";


    public PhysicalCSV() {
    }

    public void writePhysical(List<physicalResults> physicalResultss, String filename, String timeLog) {
        FileWriter fileWriter = null;
        try {
            File file = new File(filename);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            for (physicalResults physicalResults : physicalResultss) {
                fileWriter.append(physicalResults.getP_IC());
                fileWriter.append(delimiter);
                fileWriter.append(physicalResults.getP_Doa().toString());
                fileWriter.append(delimiter);
                fileWriter.append(physicalResults.getTimeLog());
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(physicalResults.getBloodPressure()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(physicalResults.getHeartRate()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(physicalResults.getBodyTemp()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(physicalResults.getHeight()));
                fileWriter.append(delimiter);
                fileWriter.append(physicalResults.getHeight_index());
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(physicalResults.getBMI()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(physicalResults.getWeight()));
                fileWriter.append(delimiter);
                fileWriter.append(physicalResults.getWeight_index());
                fileWriter.append(delimiter);
                fileWriter.append(physicalResults.getRemark());
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

    public List<physicalResults> readPhysical(String fileName){
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<physicalResults> physical_results = new ArrayList<physicalResults>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                //convert date...
                LocalDate doa = LocalDate.parse(tokens[1]);

                // to convert all the strings back to double...
                double bloodPressure = Double.parseDouble(tokens[3]);
                double heartRate = Double.parseDouble(tokens[4]);
                double bodyTemp = Double.parseDouble(tokens[5]);
                double Height = Double.parseDouble(tokens[6]);
                double BMI = Double.parseDouble(tokens[8]);
                double Weight = Double.parseDouble(tokens[9]);

                if (tokens.length > 0){
                    physicalResults physicalLog = new physicalResults(tokens[0], doa, tokens[2], bloodPressure, heartRate, bodyTemp,
                            Height, tokens[7], BMI, Weight, tokens[10], tokens[11]);
                    physical_results.add(physicalLog);
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
        } return physical_results;
    }
}

