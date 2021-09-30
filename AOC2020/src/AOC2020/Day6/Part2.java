package AOC2020.Day6;

import AOC2020.Utilities.InputGetter;

import java.io.IOException;
import java.util.ArrayList;

public class Part2 {
    public static void main(String[] args) throws IOException {
        var input = InputGetter.getInput("src/AOC2020/Day6/input.txt");
        ArrayList<String> questions = new ArrayList<>();
        int numOfQuestions = 0;
        for(String line : input) {
            if(line.equals("")) {
                if (questions.size() != 0) {
                    Questions question = new Questions(questions);
                    // Do logic stuff
                    numOfQuestions += question.getNumOfYesQuestions();
                    questions.clear();
                }
            }
            else {
                line = line.trim();
                questions.add(line);
            }
        }
        // Process last question block
        if (questions.size() != 0) {
            Questions question = new Questions(questions);
            // Do logic stuff
            numOfQuestions += question.getNumOfYesQuestions();
        }
        // Print result
        System.out.println("Submit number: " + numOfQuestions);
    }
}
