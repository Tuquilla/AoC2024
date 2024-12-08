package day8;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        InputStream inputStream = Main.class.getResourceAsStream("/day8/productive.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


        String st;
        List<ArrayList<Character>> map = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            char[] chars = st.toCharArray();
            ArrayList<Character> row = new ArrayList<>();
            for (char c : chars) {
                row.add(c);
            }
            System.out.println(row);
            map.add(row);
        }

        task1(map);
        task2(map);

    }

    static void task1(List<ArrayList<Character>> map) {
        List<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int height = 0;
        int length = 0;
        for (ArrayList<Character> row : map) {
            length = 0;
            for (Character c : row) {
                if (c != '.') {
                    List<ArrayList<Integer>> tmp = searchForAntinodes(map, c, height, length);
                    result.addAll(tmp);
                }
                length++;
            }
            height++;
        }
        result = result.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Amount of antinodes: " + result.size());
    }

    static void task2(List<ArrayList<Character>> map) {
        List<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int height = 0;
        int length = 0;
        for (ArrayList<Character> row : map) {
            length = 0;
            for (Character c : row) {
                if (c != '.') {
                    List<ArrayList<Integer>> tmp = searchForAntiNodes2(map, c, height, length);
                    result.addAll(tmp);
                }
                length++;
            }
            height++;
        }
        result = result.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Amount of antinodes 2: " + result.size());
    }

    static List<ArrayList<Integer>> searchForAntinodes(List<ArrayList<Character>> map, char antenna, int startHeight, int startLength) {
        List<ArrayList<Integer>> result = new ArrayList<>();
        //For start line
        for (int length = startLength+1; length < map.get(startHeight).size(); length++) {
            if (map.get(startHeight).get(length) == antenna) {
                //Boundary Check
                int firstHeight = startHeight - startHeight;
                int firstLength = length - startLength;
                int inverseFirstHeight = firstHeight * -1;
                int inverseFirstLength = firstLength * -1;
                //Boundary check found antenna
                if (startHeight + firstHeight >= 0 && startHeight + firstHeight < map.size() && length + firstLength >= 0 && length + firstLength < map.get(startHeight).size()) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(startHeight+firstHeight);
                    tmp.add(length + firstLength);
                    result.add(tmp);

                }
                //Boundary check start antenna
                if (startHeight + inverseFirstHeight >= 0 && startHeight + inverseFirstHeight < map.size() && startLength + inverseFirstLength >= 0 && startLength + inverseFirstLength < map.get(startHeight).size()) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(startHeight+inverseFirstHeight);
                    tmp.add(startLength + inverseFirstLength);
                    result.add(tmp);
                }
            }
        }
        for (int height = startHeight+1; height < map.size(); height++) {
            for (int length = 0; length < map.get(height).size(); length++) {
                if (map.get(height).get(length) == antenna) {
                    //Boundary Check
                    int firstHeight = height - startHeight;
                    int firstLength = length - startLength;
                    int inverseFirstHeight = firstHeight * -1;
                    int inverseFirstLength = firstLength * -1;
                    //Boundary check found antenna
                    if (height + firstHeight >= 0 && height + firstHeight < map.size() && length + firstLength >= 0 && length + firstLength < map.get(height).size()) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(height + firstHeight);
                        tmp.add(length + firstLength);
                        result.add(tmp);
                    }
                    //Boundary check start antenna
                    if (startHeight + inverseFirstHeight >= 0 && startHeight + inverseFirstHeight < map.size() && startLength + inverseFirstLength >= 0 && startLength + inverseFirstLength < map.get(height).size()) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(startHeight+inverseFirstHeight);
                        tmp.add(startLength + inverseFirstLength);
                        result.add(tmp);
                    }
                }
            }
        }
        return result;
    }

    static List<ArrayList<Integer>> searchForAntiNodes2(List<ArrayList<Character>> map, char antenna, int startHeight, int startLength) {
        List<ArrayList<Integer>> result = new ArrayList<>();
        //For start line
        ArrayList<Integer> tmpStart = new ArrayList<>();
        //Add antenna itself
        tmpStart.add(startHeight);
        tmpStart.add(startLength);
        result.add(tmpStart);
        for (int length = startLength+1; length < map.get(startHeight).size(); length++) {
            if (map.get(startHeight).get(length) == antenna) {
                //Add antenna itself
                ArrayList<Integer> antiNodeItself = new ArrayList<>();
                antiNodeItself.add(startHeight);
                antiNodeItself.add(length);
                result.add(antiNodeItself);
                //Boundary Check
                int firstHeight = startHeight - startHeight;
                int firstLength = length - startLength;
                int inverseFirstHeight = firstHeight * -1;
                int inverseFirstLength = firstLength * -1;
                //Boundary check found antenna
                if (startHeight + firstHeight >= 0 && startHeight + firstHeight < map.size() && length + firstLength >= 0 && length + firstLength < map.get(startHeight).size()) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(startHeight + firstHeight);
                    tmp.add(length + firstLength);
                    result.add(tmp);
                    recursiveSearch(result, map, (startHeight+firstHeight), (length+firstLength), firstHeight, firstLength);

                }
                //Boundary check start antenna
                if (startHeight + inverseFirstHeight >= 0 && startHeight + inverseFirstHeight < map.size() && startLength + inverseFirstLength >= 0 && startLength + inverseFirstLength < map.get(startHeight).size()) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(startHeight + inverseFirstHeight);
                    tmp.add(startLength + inverseFirstLength);
                    result.add(tmp);
                    recursiveSearch(result, map, (startHeight+inverseFirstHeight), (startLength+inverseFirstLength), inverseFirstHeight, inverseFirstLength);
                }
            }
        }
        for (int height = startHeight+1; height < map.size(); height++) {
            for (int length = 0; length < map.get(height).size(); length++) {
                if (map.get(height).get(length) == antenna) {
                    //Add antenna itself
                    ArrayList<Integer> antiNodeItself = new ArrayList<>();
                    antiNodeItself.add(height);
                    antiNodeItself.add(length);
                    result.add(antiNodeItself);
                    //Boundary Check
                    int firstHeight = height - startHeight;
                    int firstLength = length - startLength;
                    int inverseFirstHeight = firstHeight * -1;
                    int inverseFirstLength = firstLength * -1;
                    //Boundary check found antenna
                    if (height + firstHeight >= 0 && height + firstHeight < map.size() && length + firstLength >= 0 && length + firstLength < map.get(height).size()) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(height + firstHeight);
                        tmp.add(length + firstLength);
                        result.add(tmp);
                        recursiveSearch(result, map, (height+firstHeight), (length+firstLength), firstHeight, firstLength);
                    }
                    //Boundary check start antenna
                    if (startHeight + inverseFirstHeight >= 0 && startHeight + inverseFirstHeight < map.size() && startLength + inverseFirstLength >= 0 && startLength + inverseFirstLength < map.get(height).size()) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(startHeight+inverseFirstHeight);
                        tmp.add(startLength + inverseFirstLength);
                        result.add(tmp);
                        recursiveSearch(result, map, (startHeight+inverseFirstHeight), (startLength+inverseFirstLength), inverseFirstHeight, inverseFirstLength);
                    }
                }
            }
        }
        return result;
    }

    static void recursiveSearch(List<ArrayList<Integer>> result, List<ArrayList<Character>> map, int height, int length, int firstHeight, int firstLength) {
        if (height + firstHeight >= 0 && height + firstHeight < map.size() && length + firstLength >= 0 && length + firstLength < map.get(height).size()) {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(height + firstHeight);
            tmp.add(length + firstLength);
            result.add(tmp);
            recursiveSearch(result, map, height+firstHeight, length+firstLength, firstHeight, firstLength);
        }
    }

}
