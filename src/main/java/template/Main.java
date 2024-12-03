package template;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {

        InputStream inputStream = Main.class.getResourceAsStream("/day../test.txt");

        BufferedReader br
                = new BufferedReader(new InputStreamReader(inputStream));


        String st;

        while ((st = br.readLine()) != null) {
        }

        task1();
        task2();

    }

    static void task1() {

    }

    static void task2() {

    }

}
