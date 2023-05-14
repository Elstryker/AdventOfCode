package AOC2020.Day17;

import AOC2020.Utilities.InputGetter;

import java.io.IOException;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var input = InputGetter.getInput("src/AOC2020/Day17/input.txt");
        //var input = InputGetter.getInput("src/AOC2020/Day17/tinyInput.txt");
        PocketDimension pd = new PocketDimension(input);
        System.out.println("Active cells: " + pd.firstPart());
    }
}
