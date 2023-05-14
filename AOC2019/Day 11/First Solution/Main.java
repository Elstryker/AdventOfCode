package AoC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> read() throws IOException {
        List<String> ret;
        ret = Files.readAllLines(Paths.get("../Advent of Code/input.txt"));
        return ret;
    }

    public static void firstAndSecondSolution() throws IOException {
        String data = read().get(0);
        String[] dataI = data.split(",");
        ArrayList<Double> dataS = new ArrayList<>();
        int step = 0;
        while(step < dataI.length) {
            dataS.add(Double.parseDouble(dataI[step]));
            step++;
        }
        IntCodeInterpreter main = new IntCodeInterpreter(dataS);
        main.process();
        System.out.println(main.robot.visited.size());
    }

    public static void main(String[] args) throws IOException {
        firstAndSecondSolution();
    }
}
