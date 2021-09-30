package AOC2020.Day7;

import AOC2020.Utilities.InputGetter;

import java.io.IOException;

public class Part1 {
    public static void main(String[] args) throws IOException {
        var input = InputGetter.getInput("src/AOC2020/Day7/input.txt");
        //var input = InputGetter.getInput("src/AOC2020/Day7/tinyInput.txt");
        DataGraph bagGraph = new DataGraph();
        for (String line : input) {
            Parser lineParser = new Parser(line);

            String motherBag = lineParser.getOriginalBag();
            var children = lineParser.getChildrenBags();

            bagGraph.addChildrenToParent(motherBag, children);
        }
        int totalParents = bagGraph.searchAllParentBags();

        System.out.println("shiny gold bag total parents: " + totalParents);
    }
}
