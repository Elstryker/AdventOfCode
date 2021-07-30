package AOC2020.Day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Part1 {

    public static void main(String[] args) {
        try {
            var input = getInput();
            find2Numbers(input);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void find2Numbers(ArrayList<String> input) {
        boolean found = false;
        for(int i = 0; i < input.size() && !found; i++) {
            for(int j = i + 1; j < input.size() && !found; j++) {
                if (Integer.parseInt(input.get(i)) + Integer.parseInt(input.get(j)) == 2020) {
                    found = true;
                    System.out.println("The two entries are: " + input.get(i) + " and " + input.get(j) + "\n\n");
                    System.out.println("The product is: " + Integer.parseInt(input.get(i)) * Integer.parseInt(input.get(j)));
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
