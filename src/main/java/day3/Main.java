package day3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {

        InputStream inputStream = Main.class.getResourceAsStream("/day3/productive.txt");

        BufferedReader br
                = new BufferedReader(new InputStreamReader(inputStream));


        String st;
        String concatSt = null;
        List<String> mults = new ArrayList<>();

        while ((st = br.readLine()) != null) {
            concatSt += st;

        }
        String regex = "\\w*mul\\((\\d{1,3}),(\\d{1,3})\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(concatSt);

        while (matcher.find()) {
            mults.add(matcher.group(1) + "," + matcher.group(2));
        }

        int resultTask1 = task1(mults);
        System.out.println("Result of task1: " + resultTask1);
        int resultTask2 = task2(concatSt, mults);
        System.out.println("Result of task2: " + resultTask2);
    }

    static int task1(List<String> mults) {
        int result = 0;
        for(String pair : mults) {
            String[] value = pair.split(",");
            try {
                result += Integer.parseInt(value[1]) * Integer.parseInt(value[0]);
            }
            catch (Exception e) {

            }
        }
        return result;
    }

    static int task2(String concatStr, List<String> mults) {
        List<Integer> indexDo = new ArrayList<>();
        List<Integer> indexDont = new ArrayList<>();
        List<Integer> multsWithMult = new ArrayList<>();
        Map<Integer, Integer> indexResult = new HashMap<>();

        //Do
        int index = concatStr.indexOf("do()");
        while (index >= 0) {
            indexDo.add(index);
            index = concatStr.indexOf("do()", index + 1);
        }

        //Don't
        index = concatStr.indexOf("don't()");
        while (index >= 0) {
            indexDont.add(index);
            index = concatStr.indexOf("don't()", index + 1);
        }

        //mults
        for (String pair : mults) {
            String value = "mul(" + pair + ")";
            String[] leftRight = pair.split(",");
            index = concatStr.indexOf(value);
            while (index >= 0) {
                multsWithMult.add(index);
                indexResult.put(index, (Integer.parseInt(leftRight[0]) * Integer.parseInt(leftRight[1])));
                index = concatStr.indexOf(value, index + 1);
            }
        }

        //Check if do or dont is closer
        int result2 = 0;
        boolean count = true;
        for(int i = 0; i < concatStr.length(); i++) {
            if (indexDo.contains(i)) {
                count = true;
            }
            if (indexDont.contains(i)) {
                count = false;
            }
            if (count) {
                if (indexResult.containsKey(i)) {
                    result2 += indexResult.get(i);
                }
            }
        }
        return result2;
    }

}
