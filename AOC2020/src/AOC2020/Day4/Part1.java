package AOC2020.Day4;

import AOC2020.Utilities.InputGetter;

import java.io.IOException;
import java.util.Objects;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var input = InputGetter.getInput("src/AOC2020/Day4/input.txt");
        int count = 0;
        StringBuilder passport = new StringBuilder();
        for(String line : input) {
            if(Objects.equals(line, "")) {
                PassPort pass = new PassPort(passport.toString());
                passport = new StringBuilder();
                if(pass.isValid())
                    count++;
            }
            else {
                if (passport.toString() == "") {
                    passport = new StringBuilder(line);
                }
                else
                    passport.append(" ").append(line);
            }
        }
        PassPort pass = new PassPort(passport.toString());
        if(pass.isValid())
            count++;
        System.out.println("Valid passports: " + count);
    }
}
