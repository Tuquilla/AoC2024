package day4;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        InputStream inputStream = Main.class.getResourceAsStream("/day4/productive.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


        String st;
        List<List<Character>> input = new ArrayList<>();

        while ((st = br.readLine()) != null) {
            List<Character> line = new ArrayList<>();
            for (char c : st.toCharArray()) {
                line.add(c);
            }
            input.add(line);
        }

        task1(input);
        task2(input);

    }

    static void task1(List<List<Character>> input) {
        int xmasCount = 0;
        for (int height = 0; height < input.size(); height++) {
            for (int length = 0; length < input.get(height).size(); length++) {
                if (input.get(height).get(length) == 'X') {
                    //Start searching for XMAS
                    if(right(input, 'X', height, length)) {
                        xmasCount++;
                    }
                    if (left(input, 'X', height, length)) {
                        xmasCount++;
                    }
                    if (down(input, 'X', height, length)) {
                        xmasCount++;
                    }
                    if (up(input, 'X', height, length)) {
                        xmasCount++;
                    }
                    if (upperRight(input, 'X', height, length)) {
                        xmasCount++;
                    }
                    if (lowerRight(input, 'X', height, length)) {
                        xmasCount++;
                    }
                    if (upperLeft(input, 'X', height, length)) {
                        xmasCount++;
                    }
                    if (lowerLeft(input, 'X', height, length)) {
                        xmasCount++;
                    }
                }
            }
        }
        System.out.println("xmasCount task 1: " + xmasCount);
    }

    static boolean right(List<List<Character>> input, Character inputChar, int height, int length) {
        // Check for out of bounds and break
        if (length + 1 >= input.get(height).size() || inputChar == 'S') {
            if (inputChar == 'S') {
                return true;
            }
            else {
                return false;
            }
        }
        if (inputChar == 'X' && input.get(height).get(length+1) == 'M') {
            return right(input, 'M', height, length+1);
        }
        else if (inputChar == 'M' && input.get(height).get(length+1) == 'A') {
            return right(input, 'A', height, length+1);
        }
        else if (inputChar == 'A' && input.get(height).get(length+1) == 'S') {
            return right(input, 'S', height, length+1);
        }
        else return false;
    }

    static boolean left(List<List<Character>> input, Character inputChar, int height, int length) {
        // Check for out of bounds and break
        if (length - 1 < 0 || inputChar == 'S') {
            if (inputChar == 'S') {
                return true;
            }
            else {
                return false;
            }
        }
        if (inputChar == 'X' && input.get(height).get(length-1) == 'M') {
            return left(input, 'M', height, length-1);
        }
        else if (inputChar == 'M' && input.get(height).get(length-1) == 'A') {
            return left(input, 'A', height, length-1);
        }
        else if (inputChar == 'A' && input.get(height).get(length-1) == 'S') {
            return left(input, 'S', height, length-1);
        }
        else return false;
    }

    static boolean down(List<List<Character>> input, Character inputChar, int height, int length) {
        // Check for out of bounds and break
        if (height + 1 >= input.size() || inputChar == 'S') {
            if (inputChar == 'S') {
                return true;
            }
            else {
                return false;
            }
        }
        if (inputChar == 'X' && input.get(height+1).get(length) == 'M') {
            return down(input, 'M', height+1, length);
        }
        else if (inputChar == 'M' && input.get(height+1).get(length) == 'A') {
            return down(input, 'A', height+1, length);
        }
        else if (inputChar == 'A' && input.get(height+1).get(length) == 'S') {
            return down(input, 'S', height+1, length);
        }
        else return false;
    }

    static boolean up(List<List<Character>> input, Character inputChar, int height, int length) {
        // Check for out of bounds and break
        if (height - 1 < 0 || inputChar == 'S') {
            if (inputChar == 'S') {
                return true;
            }
            else {
                return false;
            }
        }
        if (inputChar == 'X' && input.get(height-1).get(length) == 'M') {
            return up(input, 'M', height-1, length);
        }
        else if (inputChar == 'M' && input.get(height-1).get(length) == 'A') {
            return up(input, 'A', height-1, length);
        }
        else if (inputChar == 'A' && input.get(height-1).get(length) == 'S') {
            return up(input, 'S', height-1, length);
        }
        else return false;
    }

    static boolean upperRight(List<List<Character>> input, Character inputChar, int height, int length) {
        // Check for out of bounds and break
        if (length + 1 >= input.get(height).size() || height-1 < 0 || inputChar == 'S') {
            if (inputChar == 'S') {
                return true;
            }
            else {
                return false;
            }
        }
        if (inputChar == 'X' && input.get(height-1).get(length+1) == 'M') {
            return upperRight(input, 'M', height-1, length+1);
        }
        else if (inputChar == 'M' && input.get(height-1).get(length+1) == 'A') {
            return upperRight(input, 'A', height-1, length+1);
        }
        else if (inputChar == 'A' && input.get(height-1).get(length+1) == 'S') {
            return upperRight(input, 'S', height-1, length+1);
        }
        else return false;
    }

    static boolean lowerRight(List<List<Character>> input, Character inputChar, int height, int length) {
        // Check for out of bounds and break
        if (length + 1 >= input.get(height).size() || height + 1 >= input.size() || inputChar == 'S') {
            if (inputChar == 'S') {
                return true;
            }
            else {
                return false;
            }
        }
        if (inputChar == 'X' && input.get(height+1).get(length+1) == 'M') {
            return lowerRight(input, 'M', height+1, length+1);
        }
        else if (inputChar == 'M' && input.get(height+1).get(length+1) == 'A') {
            return lowerRight(input, 'A', height+1, length+1);
        }
        else if (inputChar == 'A' && input.get(height+1).get(length+1) == 'S') {
            return lowerRight(input, 'S', height+1, length+1);
        }
        else return false;
    }

    static boolean upperLeft(List<List<Character>> input, Character inputChar, int height, int length) {
        // Check for out of bounds and break
        if (length - 1 < 0 || height-1 < 0 || inputChar == 'S') {
            if (inputChar == 'S') {
                return true;
            }
            else {
                return false;
            }
        }
        if (inputChar == 'X' && input.get(height-1).get(length-1) == 'M') {
            return upperLeft(input, 'M', height-1, length-1);
        }
        else if (inputChar == 'M' && input.get(height-1).get(length-1) == 'A') {
            return upperLeft(input, 'A', height-1, length-1);
        }
        else if (inputChar == 'A' && input.get(height-1).get(length-1) == 'S') {
            return upperLeft(input, 'S', height-1, length-1);
        }
        else return false;
    }

    static boolean lowerLeft(List<List<Character>> input, Character inputChar, int height, int length) {
        // Check for out of bounds and break
        if (length - 1 < 0 || height + 1 >= input.size() || inputChar == 'S') {
            if (inputChar == 'S') {
                return true;
            }
            else {
                return false;
            }
        }
        if (inputChar == 'X' && input.get(height+1).get(length-1) == 'M') {
            return lowerLeft(input, 'M', height+1, length-1);
        }
        else if (inputChar == 'M' && input.get(height+1).get(length-1) == 'A') {
            return lowerLeft(input, 'A', height+1, length-1);
        }
        else if (inputChar == 'A' && input.get(height+1).get(length-1) == 'S') {
            return lowerLeft(input, 'S', height+1, length-1);
        }
        else return false;
    }

    static void task2(List<List<Character>> input) {
        int xmasCount = 0;
        for (int height = 1; height < input.size()-1; height++) {
            for (int length = 1; length < input.get(height).size()-1; length++) {
                if (input.get(height).get(length) == 'A') {
                    //Start searching for XMAS
                    if (checkXMAS(input, height, length)) {
                        xmasCount++;
                    }
                }
            }
        }
        System.out.println("xmasCount task 2: " + xmasCount);
    }

    public static boolean checkXMAS(List<List<Character>> input, int height, int length) {
        if ((input.get(height - 1).get(length + 1) == 'S' || input.get(height - 1).get(length + 1) == 'M' )
                && (input.get(height + 1).get(length + 1) == 'S' || input.get(height + 1).get(length + 1) == 'M')
                && (input.get(height - 1).get(length - 1) == 'M' || input.get(height - 1).get(length - 1) == 'S')
                && (input.get(height + 1).get(length - 1) == 'M' || input.get(height + 1).get(length - 1) == 'S'))
        {
            if ((input.get(height - 1).get(length + 1) == 'M' && input.get(height + 1).get(length - 1) == 'M')
                    || (input.get(height - 1).get(length + 1) == 'S' && input.get(height + 1).get(length - 1) == 'S')
                    || (input.get(height - 1).get(length - 1) == 'M' && input.get(height + 1).get(length + 1) == 'M')
                    || (input.get(height - 1).get(length - 1) == 'S' && input.get(height + 1).get(length + 1) == 'S')) {
                return false;
        }
            return true;
        }
        return false;
    }

}
