package AOC2020.Day6;

import AOC2020.Day5.BoardingPass;
import AOC2020.Utilities.InputGetter;

import java.io.IOException;
import java.util.ArrayList;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var input = InputGetter.getInput("src/AOC2020/Day6/input.txt");
        StringBuilder questions = new StringBuilder();
        int numOfQuestions = 0;
        for(String line : input) {
            if(line.equals("")) {
                if (questions.length() != 0) {
                    Questions question = new Questions(questions.toString());
                    // Do logic stuff
                    numOfQuestions += question.getNumOfYesQuestions();
                    questions.setLength(0);
                }
            }
            else {
                line = line.trim();
                questions.append(line);
            }
        }
        // Process last question block
        if (questions.length() != 0) {
            Questions question = new Questions(questions.toString());
            // Do logic stuff
            numOfQuestions += question.getNumOfYesQuestions();
        }
        // Print result
        System.out.println("Submit number: " + numOfQuestions);
    }
}
