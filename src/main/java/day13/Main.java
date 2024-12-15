package day13;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {

        InputStream inputStream = Main.class.getResourceAsStream("/day13/productive.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


        String st;
        int xa = 0;
        int ya = 0;
        int xb = 0;
        int yb = 0;
        int tokens = 0;
        int X = 0;
        int Y = 0;
        while ((st = br.readLine()) != null) {
            if (st.contains("Button A")) {
                xa = Integer.parseInt(st.split("\\+")[1].split(",")[0]);
                ya = Integer.parseInt(st.split("\\+")[st.split("\\+").length - 1]);
            }
            if (st.contains("Button B")) {
                xb = Integer.parseInt(st.split("\\+")[1].split(",")[0]);
                yb = Integer.parseInt(st.split("\\+")[st.split("\\+").length - 1]);
            }
            if (st.contains("Prize")) {
                X = Integer.parseInt(st.split("=")[1].split(",")[0]);
                Y = Integer.parseInt(st.split("=")[st.split("=").length - 1]);
                System.out.println("Prize " + xa + " " + xb + " " + ya + " " + yb + " " + X + " " + Y);

                //
                outerloop:
                for (int i = 1; i <= 100; i++) {

                    for (int j = 1; j <= 100; j++) {
                        int xTimes = i * xa + j * xb;
                        int yTimes = i * ya + j * yb;
                        if (i == 80 && j == 40) {
                            System.out.println("xTimes: " + xTimes + " yTimes: " + yTimes + " X: " + X + " Y: " + Y);
                        }
                        if (xTimes == X && yTimes == Y) {
                            tokens += (3*i + j);
                            break outerloop;
                        }
                    }
                }
                xa = 0;
                xb = 0;
                ya = 0;
                yb = 0;
            }
        }

        System.out.println("Result task1: " + tokens);

        task1();
        task2();

    }

    static void task1() {

    }

    static void task2() {

    }

}
