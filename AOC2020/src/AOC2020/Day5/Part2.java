package AOC2020.Day5;

import AOC2020.Utilities.InputGetter;
import java.io.IOException;
import java.util.HashSet;

public class Part2 {
    static HashSet<Long> nonEmptySeats = new HashSet<>();

    public static void main(String[] args) throws IOException {
        //var input = InputGetter.getInput("src/AOC2020/Day5/tinyInput.txt");
        var input = InputGetter.getInput("src/AOC2020/Day5/input.txt");
        long max = 0, min = 1000;
        for(String boardingCode : input) {
            BoardingPass boardingPass = new BoardingPass(boardingCode);
            long currentID = boardingPass.getSeatID();
            max = Math.max(max, currentID);
            min = Math.min(min, currentID);
            nonEmptySeats.add(currentID);
        }
        long i;
        for (i = (min + 1); i < max; i++) {
            if(!nonEmptySeats.contains(i)) {
                break;
            }
        }
        System.out.println("Seat: " + i);
    }
}
