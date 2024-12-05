package day5;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

//        InputStream inputStream = Main.class.getResourceAsStream("/day5/test.txt");
        InputStream inputStream = Main.class.getResourceAsStream("/day5/productive.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


        String st;
        Map<Integer, ArrayList<Integer>> orderingRules = new HashMap<Integer, ArrayList<Integer>>();
        List<ArrayList<Integer>> update = new ArrayList<ArrayList<Integer>>();
        boolean noChange = true;

        while ((st = br.readLine()) != null) {
            if (st.isEmpty()) {
                noChange = false;
                continue;
            }
            if (noChange) {
                String[] values = st.split("\\|");
                if (orderingRules.containsKey(Integer.parseInt(values[0]))) {
                    orderingRules.get(Integer.parseInt(values[0])).add(Integer.parseInt(values[1]));
                }
                else {

                    ArrayList<Integer> x = new ArrayList<>();
                    x.add(Integer.parseInt(values[1]));
                    orderingRules.put(Integer.parseInt(values[0]), x);
                }
            }
            else {
                ArrayList<Integer> x = new ArrayList<>();
                String[] values = st.split(",");
                for (int i = 0; i < values.length; i++) {
                    x.add(Integer.parseInt(values[i]));
                }
                update.add(x);
            }
        }

        task1(orderingRules, update);
    }

    static void task1(Map<Integer, ArrayList<Integer>> orderingRules, List<ArrayList<Integer>> update) {
        int result = 0;
        List<ArrayList<Integer>> incorrectOrdered = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<Integer> x : update) {
            boolean isBefore = false;
            for (int index = x.size()-1; index >= 0; index--) {
                if (orderingRules.containsKey(x.get(index))) {
                    // value from update correlates with ordering rules key
                    ArrayList<Integer> tmp = orderingRules.get(x.get(index));
                    List<Integer> tmpSublist = x.subList(0, index);
                    for (int tmpInteger : tmp) {
                        if (tmpSublist.contains(tmpInteger)) {
                            isBefore = true;
                            break;
                        }
                    }
                    if (isBefore) {
                        break;
                    }
                }
            }
            if (!isBefore) {
                result += x.get(x.size()/2);
            }
            else {
                incorrectOrdered.add(x);
            }
        }
        System.out.println("Result task1 : " + result);
        task2(orderingRules, incorrectOrdered);
    }

    static void task2(Map<Integer, ArrayList<Integer>> orderingRules, List<ArrayList<Integer>> incorrectOrdered) {
        int result = 0;
        for (ArrayList<Integer> x : incorrectOrdered) {
            boolean notSorted = true;
            while(notSorted) {
                notSorted = false;
                for (int index = 0; index < x.size(); index++) {
                    if (orderingRules.containsKey(x.get(index))) {
                        ArrayList<Integer> tmp = orderingRules.get(x.get(index));
                        List<Integer> tmpSublist = x.subList(0, index);
                        for (int tmpInteger : tmp) {
                            if (tmpSublist.contains(tmpInteger)) {
                                Collections.swap(x, x.indexOf(tmpInteger), index);
                                notSorted = true;
                            }
                        }
                    }
                }
            }
            result += x.get(x.size()/2);
        }
        System.out.println("Result task2 : " + result);
    }

}
