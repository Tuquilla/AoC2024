package day2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String[]> data = new ArrayList<>();

        InputStream inputStream = Main.class.getResourceAsStream("/day2/productive.txt");

        BufferedReader br
                = new BufferedReader(new InputStreamReader(inputStream));


        String st;

        while ((st = br.readLine()) != null) {
            String[] line = st.split(" ");
            data.add(line);
        }

        List<Integer> badLines = task1(data);
        int goodLines = data.size() - badLines.size();
        System.out.println("Amount of good lines task 1: " + goodLines);
        task2(data, badLines, goodLines);

    }

    static List<Integer> task1(List<String[]> data) {
        List<Integer> badLines = new ArrayList<>();
        int index = 0;
        //Check if in or decreasing
        int goodLines = 0;
        for (String[] lines : data) {
            if (Integer.parseInt(lines[0]) == Integer.parseInt(lines[lines.length-1])) {
                badLines.add(index);
            }
            else if (Integer.parseInt(lines[0]) < Integer.parseInt(lines[lines.length-1])) {
                boolean allIncreasing = true;
                //increasing
                for (int i = 0; i < lines.length-1; i++) {
                    if (Integer.parseInt(lines[i]) < Integer.parseInt(lines[i+1]) && Integer.parseInt(lines[i+1]) - Integer.parseInt(lines[i]) <= 3) {
                    }
                    else {
                        allIncreasing = false;
                        break;
                    }
                }
                if (allIncreasing) {
                    goodLines++;
                }
                else {
                    badLines.add(index);
                }
            }
            else{
                //decreasing
                boolean allDecreasing = true;
                //increasing
                for (int i = 0; i < lines.length-1; i++) {
                    if (Integer.parseInt(lines[i]) > Integer.parseInt(lines[i+1]) && Integer.parseInt(lines[i]) - Integer.parseInt(lines[i+1]) <= 3) {
                    }
                    else {
                        allDecreasing = false;
                        break;
                    }
                }
                if (allDecreasing) {
                    goodLines++;
                }
                else {
                    badLines.add(index);
                }
            }
            index++;
        }
        return badLines;
    }

    static void task2(List<String[]> data, List<Integer> badLines, int goodLinesTask1) {
        int goodLines = 0;
        for (Integer index : badLines) {
            String[] line = data.get(index);
            String[] lineMinusOne = new String[line.length-1];
            boolean passed = false;
            for (int j = 0; j < line.length; j++) {
                for(int i = 0; i < lineMinusOne.length; i++) {
                    if (i == j) {
                        passed = true;
                    }
                    if (passed == false) {
                        lineMinusOne[i] = line[i];
                    }
                    else {
                        lineMinusOne[i] = line[i+1];
                    }
                }
                passed = false;
                List<String[]> oneLineInput = new ArrayList<>();
                oneLineInput.add(lineMinusOne);
                List<Integer> x = task1(oneLineInput);
                if (x.isEmpty()) {
                    goodLines++;
                    break;
                }
            }
        }
        System.out.println("Amount of good lines task 2: " + goodLines);
        System.out.println("Total amount of good lines: " + (goodLines + goodLinesTask1));
    }


}
