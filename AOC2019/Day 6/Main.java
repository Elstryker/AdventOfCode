package AoC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static List<String> read() throws IOException {
        List<String> ret;
        ret = Files.readAllLines(Paths.get("../Advent of Code/input.txt"));
        return ret;
    }

    public static int recursivePath(String orbit,HashMap<String,List<String>> dataS, int value) {
        int r = value;
        if(dataS.get(orbit) != null) {
            for(String entry : dataS.get(orbit)) {
                r += recursivePath(entry,dataS,value + 1);
            }
        }
        return r;
    }

    public static int recursivePathToSanta(HashMap<String, List<String>> dataS, String previous,String current, int value) {
        int r = -1, temp;
        for(String entry : dataS.get(current)) {
            if(entry.equals("SAN"))
                r = value;
            else if(!entry.equals(previous)) {
                temp = recursivePathToSanta(dataS, current, entry, value + 1);
                if(temp != -1 && (r == -1 || r > temp)) r = temp;
            }
        }
        return r;
    }

    public static void firstSolution() throws IOException {
        List<String> data = read();
        String[] temp;
        List<String> temp1;
        HashMap<String,List<String>> dataS = new HashMap<>();
        for(String s : data) {
            temp = s.split("\\)");
            temp1 = new ArrayList<>();
            temp1.add(temp[1]);
            if((temp1 = dataS.putIfAbsent(temp[0],temp1)) != null) {
                temp1.add(temp[1]);
            }
        }
        int solution = recursivePath("COM",dataS,0);
        System.out.println(solution);
    }

    public static void secondSolution() throws IOException {
        List<String> data = read();
        String[] temp;
        List<String> temp1;
        HashMap<String,List<String>> dataS = new HashMap<>();
        for(String s : data) {
            temp = s.split("\\)");
            temp1 = new ArrayList<>();
            temp1.add(temp[1]);
            if((temp1 = dataS.putIfAbsent(temp[0],temp1)) != null) {
                temp1.add(temp[1]);
            }
            temp1 = new ArrayList<>();
            temp1.add(temp[0]);
            if((temp1 = dataS.putIfAbsent(temp[1],temp1)) != null) {
                temp1.add(temp[0]);
            }
        }
        int solution = recursivePathToSanta(dataS," ","YOU",0);
        System.out.println(solution - 1);
    }

    public static void main(String[] args) throws IOException {
        secondSolution();
    }
}
