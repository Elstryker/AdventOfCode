package AOC2020.Day5;

import AOC2020.Utilities.InputGetter;
import java.io.IOException;

public class Part1 {
    public static void main(String[] args) throws IOException {
        //var input = InputGetter.getInput("src/AOC2020/Day5/tinyInput.txt");
        var input = InputGetter.getInput("src/AOC2020/Day5/input.txt");
        long max = 0;
        for(String boardingCode : input) {
            BoardingPass boardingPass = new BoardingPass(boardingCode);
            long currentID = boardingPass.getSeatID();
            //System.out.println("Current ID: " + currentID);
            max = Math.max(max, currentID);
        }
        System.out.println("Max ID: " + max);
    }
}
