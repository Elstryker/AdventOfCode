package AOC2020.Day6;

import java.util.ArrayList;
import java.util.HashSet;

public class Questions {
    private final int numOfYesQuestions;

    public Questions(String questions) {
        HashSet<Character> questionsAnsweredYes = new HashSet<>();
        for(int i = 0; i < questions.length(); i++)
            questionsAnsweredYes.add(questions.charAt(i));
        numOfYesQuestions = questionsAnsweredYes.size();
    }

    public Questions(ArrayList<String> questions) {
        HashSet<Character> questionsAnsweredYes = new HashSet<>();
        HashSet<Character> backup = new HashSet<>();
        // Insert first set of answers
        for(int i = 0; i < questions.get(0).length(); i++) {
            questionsAnsweredYes.add(questions.get(0).charAt(i));
        }
        // Filter common answers
        for(int i = 1; i < questions.size(); i++) {
            for(int j = 0; j < questions.get(i).length(); j++) {
                if (questionsAnsweredYes.contains(questions.get(i).charAt(j))) {
                    backup.add(questions.get(i).charAt(j));
                }
            }
            questionsAnsweredYes = backup;
            backup = new HashSet<>();
        }
        numOfYesQuestions = questionsAnsweredYes.size();
    }

    public int getNumOfYesQuestions() {
        return numOfYesQuestions;
    }
}
