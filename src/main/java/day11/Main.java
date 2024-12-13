package day11;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {

    static HashMap<String, Long> memoization = new HashMap<>();

    public static void main(String[] args) throws Exception {

        ForkJoinPool threadPool = new ForkJoinPool();

        InputStream inputStream = Main.class.getResourceAsStream("/day11/productive.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        List<Long> stones = new ArrayList<>();


        String st;

        while ((st = br.readLine()) != null) {
            String[] arr = st.trim().split(" ");
            for (String x : arr) {
                stones.add(Long.parseLong(x));
            }
        }
        System.out.println(stones);

        task1(25, stones);
        task2(75, stones);

    }

    static void task1(int blinks, List<Long> stones) {
        long result = 0;
        for (long stone : stones) {
            result += recursive(blinks, stone);
        }
        System.out.println("Result task1: " + result);
    }

    static void task2(int blinks, List<Long> stones) {
        long result = 0;
        for (long stone : stones) {
            result += recursive(blinks, stone);
        }
        System.out.println("Result task2: " + result);
    }

    static long recursive(int blinks, long stone) {
        String key = blinks + "_" + stone;
        if (memoization.containsKey(key)) {
            return memoization.get(key);
        }
        if (blinks == 0) {
            return 1;
        }
        if (stone == 0) {
            long result = recursive(blinks - 1, 1);
            memoization.put(key, result);
            return result;
        }
        String stoneString = String.valueOf(stone);
        if (stoneString.length() % 2 == 0) {
            long left = Long.parseLong(stoneString.substring(0, stoneString.length()/2));
            long right = Long.parseLong(stoneString.substring(stoneString.length()/2));
            long result = recursive(blinks - 1, left) + recursive(blinks - 1, right);
            memoization.put(key, result);
            return result;
        }
        long result = recursive(blinks - 1, stone * 2024);
        memoization.put(key, result);
        return result;
    }
}
