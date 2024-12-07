package day7;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws Exception {

        InputStream inputStream = Main.class.getResourceAsStream("/day7/productive.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


        String st;
        List<Long> solution = new ArrayList<>();
        List<ArrayList<Long>> values = new ArrayList<>();
        List<ArrayList<Long>> values2 = new ArrayList<>();


        while ((st = br.readLine()) != null) {
            String[] token = st.split(": ");
            solution.add(Long.parseLong(token[0]));
            String[] inputValues = token[1].split(" ");
            ArrayList<Long> rows = new ArrayList<>();
            for (String inputValue : inputValues) {
                rows.add(Long.parseLong(inputValue));
            }
            ArrayList<Long> rows2 = new ArrayList<>(rows);
            values.add(rows);
            values2.add(rows2);
        }

        task1(values, solution);
        task2(values2, solution);

    }

    static void task1(List<ArrayList<Long>> values, List<Long> solutions) {
        BigInteger result = BigInteger.valueOf(0);
        for (int i = 0; i < solutions.size(); i++) {
            Long firstValue = values.get(i).getFirst();
            values.get(i).removeFirst();
            if (calibration(BigInteger.valueOf(firstValue), BigInteger.valueOf(solutions.get(i)), values.get(i), "task1")) {
                result = result.add(BigInteger.valueOf(solutions.get(i)));
            }
        }
        System.out.println("Result first task 1: " + result);
    }

    static void task2(List<ArrayList<Long>> values, List<Long> solutions) {
        BigInteger result = BigInteger.valueOf(0);
        for (int i = 0; i < solutions.size(); i++) {
            Long firstValue = values.get(i).getFirst();
            values.get(i).removeFirst();
            if (calibration(BigInteger.valueOf(firstValue), BigInteger.valueOf(solutions.get(i)), values.get(i), "task2")) {
                result = result.add(BigInteger.valueOf(solutions.get(i)));
            }
        }
        System.out.println("Result first task 2: " + result);
    }

    static boolean calibration(BigInteger start, BigInteger result, ArrayList<Long> values, String task) {
        if (Objects.equals(start, result) && values.isEmpty()) {
            return true;
        }
        if (values.isEmpty()) {
            return false;
        }

        Long element = values.getFirst();
        values.removeFirst();
        BigInteger mult = start.multiply(BigInteger.valueOf(element));
        if (calibration(mult, result, values, task)) {
            values.addFirst(element);
            return true;
        }
        BigInteger add = start.add(BigInteger.valueOf(element));
        if (calibration(add, result, values, task)) {
            values.addFirst(element);
            return true;
        }
        if (task.equals("task2")) {
            String concatString = start.toString().concat(BigInteger.valueOf(element).toString());
            BigInteger concat = new BigInteger(concatString);
            if (calibration(concat, result, values, task)) {
                values.addFirst(element);
                return true;
            }
        }

        values.addFirst(element);
        return false;
    }

}
