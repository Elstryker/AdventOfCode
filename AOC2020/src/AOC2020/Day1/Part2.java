package AOC2020.Day1;

import AOC2020.Utilities.InputGetter;

import java.io.IOException;
import java.util.ArrayList;

public class Part2 {
    public static void main(String[] args) {
        try {
            var input = InputGetter.getInput("src/AOC2020/Day1/input.txt");
            find3Numbers(input);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void find3Numbers(ArrayList<String> input) {
        boolean found = false;
        for(int i = 0; i < input.size() && !found; i++) {
            for(int j = 0; j < input.size() && !found; j++) {
                for(int k = 0; k < input.size() && !found; k++) {
                    int first = Integer.parseInt(input.get(i));
                    int second = Integer.parseInt(input.get(j));
                    int third = Integer.parseInt(input.get(k));
                    if (first + second + third == 2020) {
                        found = true;
                        System.out.println("The three entries are: " + input.get(i) + ", " + input.get(j) + " and " + input.get(k) + "\n\n");
                        System.out.println("The product is: " + first * second * third);
                    }
                }
            }
        }
    }
}
