package com.geekbrains.part2.lesson5;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class AppData {
    private String[] header;
    private Integer[][] data;


    public void readFile(String file) {
        ArrayList<Integer[]> transactionsList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            header = in.readLine().split(";");
            String result ;
            while ((result = in.readLine()) != null) {
                String[] stringsValue = result.split(";");
                Integer[] integersValue = new Integer[stringsValue.length];
                for (int i = 0; i < stringsValue.length; i++) {
                    integersValue[i] = Integer.parseInt(stringsValue[i]);
                }
                transactionsList.add(integersValue);
            }
            data = transactionsList.toArray(new Integer[][]{});
            System.out.println(Arrays.toString(header) + "\n" + Arrays.deepToString(data));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSV(String resultFileName) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFileName), StandardCharsets.UTF_8))) {
            StringBuffer line = new StringBuffer();
            for (int i = 0; i < header.length; i++) {
                line.append(header[i]);
                if (i<header.length-1) {
                    line.append(";");
                }
            }
            writer.write(line.toString());
            writer.newLine();

            for (Integer[] dataLine : data) {
                line.delete(0, line.length());
                for (int j = 0; j < dataLine.length; j++) {
                    line.append(dataLine[j]);
                    if (j < dataLine.length - 1) {
                        line.append(";");
                    }
                }
                writer.write(line.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
