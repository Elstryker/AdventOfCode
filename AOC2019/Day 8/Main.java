package AoC;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> read() throws IOException {
        List<String> ret;
        ret = Files.readAllLines(Paths.get("../Advent of Code/input.txt"));
        return ret;
    }

    public static void firstSolution() throws IOException {
        String data = read().get(0);
        List<Integer> listInt = new ArrayList<>();
        int i,nmrOnes = Integer.MAX_VALUE,temp,j,layer = 0;
        for(i = 0; i < data.length(); i++) {
            listInt.add(Integer.parseInt(String.valueOf(data.charAt(i))));
        }
        int nmrPixperLayer = 25 * 6;
        int nmrLayers = listInt.size()/nmrPixperLayer;
        for(i = 0; i < nmrLayers; i++) {
            temp = 0;
            for(j = i * nmrPixperLayer; j < (i+1) * nmrPixperLayer; j++) {
                if(listInt.get(j)==0) temp++;
            }
            if(nmrOnes > temp) {
                nmrOnes = temp;
                layer = i;
            }
        }
        int nmrOfOne = 0, nmrOfTwo = 0;
        for(i = layer * nmrPixperLayer; i < (layer+1) * nmrPixperLayer; i++) {
            if(listInt.get(i) == 1) nmrOfOne++;
            else if(listInt.get(i) == 2) nmrOfTwo++;
        }
        System.out.println(nmrOfOne * nmrOfTwo);
    }

    public static void secondSolution() throws IOException {
        String data = read().get(0);
        List<Integer> listInt = new ArrayList<>();
        int i,nmrOnes = Integer.MAX_VALUE,temp,j,layer = 0;
        for(i = 0; i < data.length(); i++) {
            listInt.add(Integer.parseInt(String.valueOf(data.charAt(i))));
        }
        int nmrPixperLayer = 25 * 6;
        int nmrLayers = listInt.size()/nmrPixperLayer;
        List<Integer> finalImage = new ArrayList<>();
        int pixel;
        for(i = 0; i < nmrPixperLayer; i++) {
            for(j = i; j < nmrLayers * nmrPixperLayer; j+= nmrPixperLayer) {
                if((pixel = listInt.get(j)) != 2) {
                    finalImage.add(pixel);
                    break;
                }
            }
        }
        JFrame window = new JFrame();
        window.setSize(700,500);
        window.setLayout(new GridLayout(6,25));
        JPanel pix;
        for(i = 0; i < 150; i++) {
            pix = new JPanel();
            if(finalImage.get(i) == 0) {
                pix.setBackground(Color.BLACK);
            }
            else pix.setBackground(Color.WHITE);
            window.add(pix);
        }
        window.pack();
        window.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        secondSolution();
    }
}
