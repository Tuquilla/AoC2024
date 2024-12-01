package day1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();

        InputStream inputStream = Main.class.getResourceAsStream("/day1/productive.txt");

        BufferedReader br
                = new BufferedReader(new InputStreamReader(inputStream));


        String st;

        while ((st = br.readLine()) != null) {
            String[] inputs = st.split(" {3}");
            left.add(Integer.parseInt(inputs[0]));
            right.add(Integer.parseInt(inputs[1]));
        }

        task1(left, right);
        task2(left, right);

    }

    static void task1(List<Integer> left, List<Integer> right) {
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);
        int result = 0;

        for (int i = 0; i < left.size(); i++) {
            result += Math.abs(left.get(i) - right.get(i));
        }

        System.out.println("Resultat: " + result);
    }

    static void task2(List<Integer> left, List<Integer> right) {

        long result = 0;

        for (Integer leftValue : left) {
            result += leftValue * right.parallelStream().filter(i -> leftValue.equals(i)).count();
        }

        System.out.println("Resultat: " + result);
    }

}
