package AoC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int process(int num) {
        return (num/3)-2;
    }

    public static List<String> read() throws IOException {
        List<String> ret;
        ret = Files.readAllLines(Paths.get("../Advent of Code/input.txt"));
        return ret;
    }

    public static void firstSolution() throws IOException {
        int x = 0;
        List<String> data = read();
        for(String s : data) {
            x += process(Integer.parseInt(s));
        }
        System.out.println(x);
    }

    public static void secondSolution() throws IOException {
        int x = 0;
        List<String> data = read();
        for(String s : data) {
            x += auxiliar(Integer.parseInt(s));
        }
        System.out.println(x);
    }

    private static int auxiliar(int num) {
        int x = 0;
        num = process(num);
        while(num > 0) {
            x += num;
            num = process(num);
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        firstSolution();
        secondSolution();
    }
}
