package day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws Exception {

        InputStream inputStream = Main.class.getResourceAsStream("/day6/productive.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


        String st;
        List<ArrayList<Character>> map = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            ArrayList<Character> row = new ArrayList<>();
            for (char c : st.toCharArray()) {
                row.add(c);
            }
            map.add(row);
        }

        int startHeight = 0;
        int startLength = 0;
        for(int height = 0; height < map.size(); height++) {
            for(int length = 0; length < map.get(height).size(); length++) {
                if (map.get(height).get(length) == '^') {
                    startHeight = height;
                    startLength = length;
                }
            }
        }

        task1(map);
        System.out.println("Count task1 : " + countPaths(map));
        for (ArrayList<Character> row : map) {
            System.out.println(row);
        }
        task2(map, startHeight, startLength);
    }

    static void task1(List<ArrayList<Character>> map) {
        turnMatrix90DegreesRight(map);
        boolean finish = false;
        int repeats = 0;
        while (!finish) {
            repeats++;
            int start = map.get(getStartHeight(map)).indexOf('^');
            int obstacle = -1;
            for (int i = start; i < map.get(getStartHeight(map)).size(); i++) {
                if (map.get(getStartHeight(map)).get(i) == '#') {
                    obstacle = i;
                    break;
                }
            }
            if (obstacle == -1 || obstacle <= start) {
                //Finish
                List<Character> list = map.get(getStartHeight(map));
                IntStream.range(start, map.get(getStartHeight(map)).size())
                        .parallel()
                        .forEach(i -> list.set(i, 'X'));
                finish = true;
                if (repeats % 2 != 0) {
                    turnMatrix90DegreesRight(map);
                }
            }
            else {
                List<Character> list = map.get(getStartHeight(map));
                IntStream.range(start, obstacle-1)
                        .parallel()
                        .forEach(i -> list.set(i, 'X'));
                list.set(obstacle-1, '^');
                turnMatrix90DegreesLeft(map);
            }
        }
    }

    static void task2(List<ArrayList<Character>> map, int startHeight, int startLength) {
        int result = 0;
        System.out.println("Task 2");
        List<ArrayList<Integer>> countMap = new ArrayList<>();
        List<ArrayList<Character>> copyMap = new ArrayList<>();

        for (ArrayList<Character> row : map) {
            ArrayList<Integer> rowInt = new ArrayList<>();
            for (int i = 0; i < row.size(); i++) {
                rowInt.add(0);
            }
            countMap.add(rowInt);
        }

        for (int height = 0; height < map.size(); height++) {
            for (int length = 0; length < map.get(height).size(); length++) {

                cleanIntList(countMap);
                copyMap.clear();
                for (ArrayList<Character> row : map) {
                    ArrayList<Character> tmp = new ArrayList<>();
                    tmp.addAll(row);
                    copyMap.add(tmp);
                }
                copyMap.get(startHeight).set(startLength, '^');
                if (copyMap.get(height).get(length) != 'O' && copyMap.get(height).get(length) != '#' && copyMap.get(height).get(length) != '^') {
                    char previous = copyMap.get(height).get(length);
                    copyMap.get(height).set(length, '#');

                    if (isLoop(copyMap, countMap)) {
                        result++;
                        copyMap.get(height).set(length, previous);
                    }
                    else {
                        copyMap.get(height).set(length, previous);
                    }
                }
            }

        }
        System.out.println("Count task2: " + result);
    }

    static boolean isLoop(List<ArrayList<Character>> map, List<ArrayList<Integer>> countMap) {

        turnMatrix90DegreesRight(map);
        boolean finish = false;
        int repeats = 0;
        while (!finish) {
            repeats++;
            int startHeight = getStartHeight(map);
            int start = map.get(startHeight).indexOf('^');
            int obstacle = -1;
            for (int i = start; i < map.get(getStartHeight(map)).size(); i++) {
                if (map.get(getStartHeight(map)).get(i) == '#') {
                    obstacle = i;
                    break;
                }
            }
            // If a guard is run through a point multiple time, break
            for (ArrayList<Integer> rows : countMap) {
                if (rows.parallelStream().max(Integer::compare).get() >= 10) {
                    map.get(startHeight).set(start, '.');
                    return true;
                }
            }

            // Update counter List
            countMap.get(startHeight).set(start, countMap.get(startHeight).get(start) + 1);

            if (obstacle == -1 || obstacle <= start) {
                //Finish
                List<Character> list = map.get(getStartHeight(map));
                IntStream.range(start, map.get(getStartHeight(map)).size())
                        .parallel()
                        .forEach(i -> list.set(i, '.'));
                return false;
            }
            else {
                List<Character> list = map.get(getStartHeight(map));
                IntStream.range(start, obstacle-1)
                        .parallel()
                        .forEach(i -> list.set(i, '.'));
                list.set(obstacle-1, '^');
                turnMatrix90DegreesLeft(map);
            }
        }
        return false;
    }

    static void turnMatrix90DegreesRight(List<ArrayList<Character>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = i+1; j < matrix.get(i).size(); j++) {
                char temp = matrix.get(i).get(j);
                matrix.get(i).set(j, matrix.get(j).get(i));
                matrix.get(j).set(i, temp);
            }
            Collections.reverse(matrix.get(i));
        }
    }

    static void turnMatrix90DegreesLeft(List<ArrayList<Character>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = i + 1; j < matrix.get(i).size(); j++) {
                char temp = matrix.get(i).get(j);
                matrix.get(i).set(j, matrix.get(j).get(i));
                matrix.get(j).set(i, temp);
            }
        }

        for (int i = 0; i < matrix.size() / 2; i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                char temp = matrix.get(i).get(j);
                matrix.get(i).set(j, matrix.get(matrix.size() - 1 - i).get(j));
                matrix.get(matrix.size() - 1 - i).set(j, temp);
            }
        }
    }

    static int getStartHeight(List<ArrayList<Character>> matrix) {
        int startHeight = 0;
        int startLength = 0;
        for (int i = 0; i < matrix.size(); i++) {
            if (matrix.get(i).contains('^')) {
                startHeight = i;
                return startHeight;
            }
        }
        return -1;
    }

    static long countPaths(List<ArrayList<Character>> matrix) {
        long result = 0;
        for (ArrayList<Character> row : matrix) {
            result += row.parallelStream().filter(i -> i == 'X').count();
        }
        return result;
    }

    static void cleanIntList(List<ArrayList<Integer>> intList) {
        for (ArrayList<Integer> row : intList) {
            for (int i = 0; i < row.size(); i++) {
                row.set(i, 0);
            }
        }
    }

}
