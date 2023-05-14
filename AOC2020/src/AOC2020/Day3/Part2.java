package AOC2020.Day3;

import java.io.IOException;
import AOC2020.Utilities.InputGetter;

public class Part2 {
    public static void main(String[] args) throws IOException {
        var input = InputGetter.getInput("src/AOC2020/Day3/input.txt");
        int height = input.size();
        int width = input.get(0).length();
        char[][] actualMap = new char[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                actualMap[i][j] = input.get(i).charAt(j);
            }
        }
        Map map = new Map(height,width,actualMap);
        int one = map.howManyTreesInTraversal(1,1);
        int two = map.howManyTreesInTraversal(3,1);
        int three = map.howManyTreesInTraversal(5,1);
        int four = map.howManyTreesInTraversal(7,1);
        int five = map.howManyTreesInTraversal(1,2);
        long result = (long) one * two * three * four * five;
        System.out.println(result);
    }
}
