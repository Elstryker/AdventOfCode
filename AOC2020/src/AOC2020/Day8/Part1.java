package AOC2020.Day8;

import AOC2020.Day7.DataGraph;
import AOC2020.Day7.Parser;
import AOC2020.Utilities.InputGetter;

import java.io.IOException;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var input = InputGetter.getInput("src/AOC2020/Day8/input.txt");
        //var input = InputGetter.getInput("src/AOC2020/Day8/tinyInput.txt");
        BootCode bootCode = new BootCode(input);
        System.out.println("Accumulator: " + bootCode.runCode());
    }
}
