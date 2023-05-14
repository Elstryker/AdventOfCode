package AOC2020.Day2;

import AOC2020.Utilities.InputGetter;

import java.io.IOException;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var input = InputGetter.getInput("src/AOC2020/Day2/input.txt");
        int count = 0;
        for(int i = 0; i < input.size(); i++) {
            String[] tokens = input.get(i).split(" ");
            String[] range = tokens[0].split("-");
            int low = Integer.parseInt(range[0]);
            int high = Integer.parseInt(range[1]);
            char letter = tokens[1].charAt(0);
            String pass = tokens[2];
            Password password = new Password(low,high,letter,pass);
            if(password.isValid())
                count++;
        }
        System.out.println(count);
    }
}
