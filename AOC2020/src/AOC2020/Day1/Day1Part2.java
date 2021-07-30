package AOC2020.Day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1Part2 {
    public static void main(String[] args) {
        try {
            var input = getInput();
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

    private static ArrayList<String> getInput() throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        File file = new File("input.txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        ArrayList<String> data = new ArrayList<>();
        String line;
        while((line = buffer.readLine()) != null)
            data.add(line);
        return data;
    }
}
