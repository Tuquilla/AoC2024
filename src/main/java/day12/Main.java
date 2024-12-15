package day12;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static List<ArrayList<Character>> patches = new ArrayList<ArrayList<Character>>();
    static long gardenSize = 0;
    static long fenceSize = 0;

    public static void main(String[] args) throws Exception {

        InputStream inputStream = Main.class.getResourceAsStream("/day12/test.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


        String st;

        while ((st = br.readLine()) != null) {
            patches.add(st.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(ArrayList::new)));
        }

        //task1();
        task2();

    }

    static void task1() {
        long costs = 0;
        for (int height = 0; height < patches.size(); height++) {
            for (int length = 0; length < patches.get(height).size(); length++) {
                if (patches.get(height).get(length) != '.' && patches.get(height).get(length) != ',') {
                    //reset patches
                    for (int i = 0; i < patches.size(); i++) {
                        for (int j = 0; j < patches.get(i).size(); j++) {
                            if (patches.get(i).get(j) == '.') {
                                patches.get(i).set(j, ',');
                            }
                        }
                    }
                    gardenSize = 0;
                    fenceSize = 0;
                    callRecursive(height, length, patches.get(height).get(length));
                    System.out.println("gardenSize " + gardenSize);
                    System.out.println("fenceSize " + fenceSize);
                    System.out.println();
                    costs += (gardenSize * fenceSize);
                }
            }
        }
        System.out.println("Costs task1: " + costs);
    }

    static void task2() {
        long costs = 0;
        for (int height = 0; height < patches.size(); height++) {
            for (int length = 0; length < patches.get(height).size(); length++) {
                if (patches.get(height).get(length) != '.' && patches.get(height).get(length) != ',') {
                    //reset patches
                    for (int i = 0; i < patches.size(); i++) {
                        for (int j = 0; j < patches.get(i).size(); j++) {
                            if (patches.get(i).get(j) == '.') {
                                patches.get(i).set(j, ',');
                            }
                        }
                    }
                    gardenSize = 0;
                    fenceSize = 0;
                    callRecursive2(height, length, patches.get(height).get(length),"none", "none");
                    System.out.println("gardenSize " + gardenSize);
                    System.out.println("fenceSize " + fenceSize);
                    System.out.println();
                    costs += (gardenSize * fenceSize);
                }
            }
        }
        System.out.println("Costs task2: " + costs);
    }

    static void callRecursive(int height, int length, char patchValue) {
        if (patches.get(height).get(length) == patchValue) {
            patches.get(height).set(length, '.');
            gardenSize++;
        }

        // Check up
        if (height -1 >= 0 && patches.get(height-1).get(length) == patchValue) {
            callRecursive(height-1, length, patchValue);
        }
        else if (height -1 >= 0 && patches.get(height-1).get(length) != '.'){
            fenceSize++;
        } else if (height - 1 < 0) {
            fenceSize++;
        }
        // Check right
        if (length + 1 < patches.get(height).size() && patches.get(height).get(length + 1) == patchValue) {
            callRecursive(height, length+1, patchValue);
        }
        else if (length + 1 < patches.get(height).size() && patches.get(height).get(length + 1) != '.'){
            fenceSize++;
        }
        else if (length + 1 >= patches.get(height).size()) {
            fenceSize++;
        }
        // Check down
        if (height+1 < patches.size() && patches.get(height+1).get(length) == patchValue) {
            callRecursive(height+1, length, patchValue);
        }
        else if (height+1 < patches.size() && patches.get(height+1).get(length) != '.'){
            fenceSize++;
        }
        else if (height+1 >= patches.size()){
            fenceSize++;
        }
        // CheckLeft
        if (length-1 >= 0 && patches.get(height).get(length-1) == patchValue) {
            callRecursive(height, length-1, patchValue);
        }
        else if (length-1 >= 0 && patches.get(height).get(length-1) != '.'){
            fenceSize++;
        }
        else if (length-1 < 0){
            fenceSize++;
        }
    }

    static void callRecursive2(int height, int length, char patchValue, String direction, String previousDirection) {
        System.out.println("x: " + length + " y: " + height);
        System.out.println("fenceSize: " + fenceSize);
        System.out.println("direction: " + direction);

        if (patches.get(height).get(length) == patchValue) {
            patches.get(height).set(length, '.');
            gardenSize++;
        }

        // Check up
        if (height -1 >= 0 && patches.get(height-1).get(length) == patchValue) {
            callRecursive2(height-1, length, patchValue, "up", direction);
        }
        else if (height -1 >= 0 && patches.get(height-1).get(length) != '.'){
            fenceSize++;
        }
        else if (height - 1 < 0 && !direction.equals(previousDirection) && direction.equals("up")) {
            System.out.println("letzer");
            fenceSize++;
        }
        // Check right
        if (length + 1 < patches.get(height).size() && patches.get(height).get(length + 1) == patchValue) {
            callRecursive2(height, length+1, patchValue, "right", direction);
        }
        else if (length + 1 < patches.get(height).size() && patches.get(height).get(length + 1) != '.' ){
            fenceSize++;
        }
        else if (length + 1 >= patches.get(height).size() && !direction.equals(previousDirection) & direction.equals("right")) {
            fenceSize++;
        }
        // Check down
        if (height+1 < patches.size() && patches.get(height+1).get(length) == patchValue) {
            callRecursive2(height+1, length, patchValue, "down", direction);
        }
        else if (height+1 < patches.size() && patches.get(height+1).get(length) != '.' ){
            fenceSize++;
        }
        else if (height+1 >= patches.size() && !direction.equals(previousDirection) && direction.equals("down")){
            fenceSize++;
        }
        // CheckLeft
        if (length-1 >= 0 && patches.get(height).get(length-1) == patchValue) {
            callRecursive2(height, length-1, patchValue, "left", direction);
        }
        else if (length-1 >= 0 && patches.get(height).get(length-1) != '.' ){
            fenceSize++;
        }
        else if (length-1 < 0 && !direction.equals(previousDirection) && direction.equals("left")){
            fenceSize++;
        }
    }

}
