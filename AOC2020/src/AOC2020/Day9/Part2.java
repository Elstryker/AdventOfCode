package AOC2020.Day9;

import AOC2020.Utilities.InputGetter;

import java.io.IOException;

public class Part2 {
    public static void main(String[] args) throws IOException {
        var input = InputGetter.getInput("src/AOC2020/Day9/input.txt");
        //var input = InputGetter.getInput("src/AOC2020/Day9/tinyInput.txt");
        //Xmas xmas = new Xmas(input,"127");
        Xmas xmas = new Xmas(input, "57195069");
        System.out.println("Solution Value: " + xmas.resolvePart2());
    }
}
