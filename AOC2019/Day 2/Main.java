package AoC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static List<String> read() throws IOException {
        List<String> ret;
        ret = Files.readAllLines(Paths.get("../Advent of Code/input.txt"));
        return ret;
    }

    public static void process(ArrayList<Integer> dataS) {
        int step = 0;
        while(step < dataS.size()) {
            if(dataS.get(step) == 1) {
                dataS.set(dataS.get(step+3),(dataS.get(dataS.get(step+1)) + dataS.get(dataS.get(step+2))));
                step += 4;
            }
            else if(dataS.get(step) == 2) {
                dataS.set(dataS.get(step+3),(dataS.get(dataS.get(step+1)) * dataS.get(dataS.get(step+2))));
                step += 4;
            }
            else if(dataS.get(step) == 99) break;
        }
    }

    public static void firstSolution() throws IOException {
        String data = read().get(0);
        String[] dataI = data.split(",");
        ArrayList<Integer> dataS = new ArrayList<>();
        int step = 0;
        while(step < dataI.length) {
            dataS.add(Integer.parseInt(dataI[step]));
            step++;
        }
        process(dataS);
        System.out.println(dataS.get(0));
    }

    public static void secondSolution() throws IOException {
        String data = read().get(0);
        String[] dataI = data.split(",");
        ArrayList<Integer> dataS = new ArrayList<>();
        boolean flag = true;
        int step = 0;
        while(step < dataI.length) {
            dataS.add(Integer.parseInt(dataI[step]));
            step++;
        }
        ArrayList<Integer> original = new ArrayList<>(dataS);
        int noun = 0,verb = 0;
        while(flag && noun < 100) {
            for(verb = 0; verb < 100; verb++) {
                dataS.set(1,noun);
                dataS.set(2,verb);
                process(dataS);
                if(dataS.get(0) == 19690720) {
                    flag = false;
                    break;
                }
                else {
                    dataS.clear();
                    dataS.addAll(original);
                }
            }
            if(flag) noun++;
        }
        System.out.println("Hello");
        System.out.println(100 * noun + verb);
    }

    public static void main(String[] args) throws IOException {
        secondSolution();
    }
}
