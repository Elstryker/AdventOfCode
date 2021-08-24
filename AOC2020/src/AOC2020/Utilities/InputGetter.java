package AOC2020.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputGetter {
    public static ArrayList<String> getInput(String path) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        File file = new File(path);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        ArrayList<String> data = new ArrayList<>();
        String line;
        while((line = buffer.readLine()) != null)
            data.add(line);
        return data;
    }
}
