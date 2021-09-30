package AOC2020.Day7;

import java.util.ArrayList;

public class Parser {
    String originalBag;
    ArrayList<String> childrenBags = new ArrayList<>();
    ArrayList<MyPair<Integer,String>> numChildrenBags = new ArrayList<>();

    public Parser(String originalString) {
        String[] dividedString = originalString.split(" contain ");
        String original = dividedString[0].trim();
        String children = dividedString[1].trim();

        // Get original bag info
        dividedString = original.split(" ");
        originalBag = dividedString[0] + " " + dividedString[1];

        // Get children bags info
        if(!children.equals("no other bags.")) {
            String[] childenStringArray = children.split(", ");
            for (String child : childenStringArray) {
                // It will return number and name in positions 1 and 2 correspondingly
                String[] childrenInfo = parseChildrenBag(child);
                childrenBags.add(childrenInfo[1]);
                MyPair<Integer,String> pair = new MyPair<>(Integer.parseInt(childrenInfo[0]),childrenInfo[1]);
                numChildrenBags.add(pair);
            }
        }
    }

    public String getOriginalBag() {
        return originalBag;
    }

    public ArrayList<String> getChildrenBags() {
        return childrenBags;
    }

    public ArrayList<MyPair<Integer,String>> getNumChildrenBags() {
        return numChildrenBags;
    }

    private String[] parseChildrenBag(String childrenString) {
        String[] tokenizer = childrenString.split(" ");
        return new String[] {tokenizer[0], tokenizer[1] + " " + tokenizer[2]};
    }
}
