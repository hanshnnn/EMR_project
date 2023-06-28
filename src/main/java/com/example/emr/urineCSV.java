package com.example.emr;

import com.example.emr.Records.urineResults;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class urineCSV {
    private final String delimiter = ",";
    private final String newline = "\n";
    private final String header = "p_ID,p_Doa,p_timeLog,glucConcent,Gravity,Nitrities,Protein,RBC,Remarks,UrineClarity,UrineColour,UrineOdour";

    public urineCSV(){
    }

    public void writeUrine(List<urineResults> urineResultss, String fileName, String timeLog) {
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            for (urineResults urineResults : urineResultss) {
                fileWriter.append(urineResults.getP_IC());
                fileWriter.append(delimiter);
                fileWriter.append(urineResults.getP_Doa().toString());
                fileWriter.append(delimiter);
                fileWriter.append(urineResults.getTimeLog());
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(urineResults.getGlucConcent()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(urineResults.getGravity()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(urineResults.getNitrities()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(urineResults.getProtein()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(urineResults.getRBC()));
                fileWriter.append(delimiter);
                fileWriter.append(urineResults.getRemarks());
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(urineResults.getUrineClarity()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(urineResults.getUrineColour()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(urineResults.getUrineOdour()));
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

    public List<urineResults> readUrine(String fileName){
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<urineResults> urine_results = new ArrayList<urineResults>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);

                //convert date...
                LocalDate doa = LocalDate.parse(tokens[1]);

                // to convert all the strings back to double...
                double glucConcent = Double.parseDouble(tokens[3]);
                double Gravity = Double.parseDouble(tokens[4]);

                if (tokens.length > 0){
                    urineResults urineLog = new urineResults(tokens[0], doa, tokens[2], glucConcent, Gravity, tokens[5], tokens[6]
                            , tokens[7], tokens[8], tokens[9], tokens[10], tokens[11]);
                    urine_results.add(urineLog);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
        }
        return urine_results;
    }
}