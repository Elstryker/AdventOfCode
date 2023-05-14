package AOC2020.Day3;

import AOC2020.Utilities.InputGetter;

import java.io.IOException;

public class Part1 {
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
        System.out.println(map.howManyTreesInTraversal(3,1));
    }
}
