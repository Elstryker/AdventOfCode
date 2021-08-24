package AOC2020.Day1;

import AOC2020.Utilities.InputGetter;

import java.io.IOException;
import java.util.ArrayList;

public class Part1 {

    public static void main(String[] args) {
        try {
            var input = InputGetter.getInput("src/AOC2020/Day1/input.txt");
            find2Numbers(input);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void find2Numbers(ArrayList<String> input) {
        boolean found = false;
        for(int i = 0; i < input.size() && !found; i++) {
            for(int j = i + 1; j < input.size() && !found; j++) {
                if (Integer.parseInt(input.get(i)) + Integer.parseInt(input.get(j)) == 2020) {
                    found = true;
                    System.out.println("The two entries are: " + input.get(i) + " and " + input.get(j) + "\n\n");
                    System.out.println("The product is: " + Integer.parseInt(input.get(i)) * Integer.parseInt(input.get(j)));
                }
            }
        }
    }
}
