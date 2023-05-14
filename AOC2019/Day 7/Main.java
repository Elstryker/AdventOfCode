package AoC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<String> read() throws IOException {
        List<String> ret;
        ret = Files.readAllLines(Paths.get("../Advent of Code/input.txt"));
        return ret;
    }

    public static int process(ArrayList<Integer> dataS, int phase, int input) {
        int step = 0;
        int opcode, fmode,smode;
        int[] output = new int[1];
        int[] inputs = {phase,input};
        while(step < dataS.size()) {
            if(dataS.get(step) == 99) break;
            else {
                int temp = dataS.get(step);
                int temp1 = temp % 10;
                temp /= 10;
                opcode = ((temp % 10) * 10) + temp1;
                temp /= 10;
                fmode = temp % 10;
                temp /= 10;
                smode = temp % 10;
                step = auxProcess(dataS,opcode,fmode,smode,step,output,inputs);
            }
        }
        return output[0];
    }

    public static int auxProcess(ArrayList<Integer> dataS, int opcode,int mode1, int mode2, int step,int[] output,int[] input) {
        int firstParam, secondParam, inttemp;
        firstParam = mode1 == 0 ? dataS.get(dataS.get(step + 1)) : dataS.get(step + 1);
        if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8)
            secondParam = mode2 == 0 ? dataS.get(dataS.get(step + 2)) : dataS.get(step + 2);
        else secondParam = 0;
        if(opcode == 1) {
            dataS.set(dataS.get(step+3),firstParam + secondParam);
            step += 4;
        }
        else if(opcode == 2) {
            dataS.set(dataS.get(step+3),firstParam * secondParam);
            step += 4;
        }
        else if(opcode == 3) {
            inttemp = input[0] == -1 ? input[1] : input[0];
            input[0] = -1;
            dataS.set(dataS.get(step + 1), inttemp);
            step += 2;
        }
        else if(opcode == 4) {
            output[0] = firstParam;
            step += 2;
        }
        else if(opcode == 5) {
            if(firstParam != 0) step = secondParam;
            else step += 3;
        }
        else if(opcode == 6) {
            if(firstParam == 0) step = secondParam;
            else step += 3;
        }
        else if(opcode == 7) {
            if(firstParam < secondParam) dataS.set(dataS.get(step + 3),1);
            else dataS.set(dataS.get(step + 3),0);
            step += 4;
        }
        else if(opcode == 8) {
            if(firstParam == secondParam) dataS.set(dataS.get(step + 3),1);
            else dataS.set(dataS.get(step + 3),0);
            step += 4;
        }
        return step;
    }

    public static void firstSolution() throws IOException {
        String data = read().get(0);
        String[] dataI = data.split(",");
        ArrayList<Integer> dataS = new ArrayList<>();
        int step = 0,output,tempOutput;
        while(step < dataI.length) {
            dataS.add(Integer.parseInt(dataI[step]));
            step++;
        }
        List<Integer> foo;
        boolean same;
        List<List<Integer>> combinations = new ArrayList<>();
        for(int one = 0; one < 5; one++)
            for(int two = 0; two < 5; two++)
                for(int three = 0; three < 5; three++)
                    for(int four = 0; four < 5; four++)
                        for(int five = 0; five < 5; five++) {
                            foo = new ArrayList<>();
                            same = false;
                            foo.add(one);
                            if(foo.contains(two)) same = true;
                            foo.add(two);
                            if(foo.contains(three)) same = true;
                            foo.add(three);
                            if(foo.contains(four)) same = true;
                            foo.add(four);
                            if(foo.contains(five)) same = true;
                            foo.add(five);
                            if(!same)
                                combinations.add(foo);
                        }
        output = 0;
        tempOutput = 0;
        for(List<Integer> entry : combinations) {
            for(Integer phase : entry) {
                tempOutput = process(dataS,phase,tempOutput);
            }
            if(output < tempOutput) output = tempOutput;
            tempOutput = 0;
        }
        System.out.println(output);
    }

    public static void secondSolution() throws IOException {
        String data = read().get(0);
        String[] dataI = data.split(",");
        ArrayList<Integer> dataS = new ArrayList<>();
        int step = 0,output,i;
        while(step < dataI.length) {
            dataS.add(Integer.parseInt(dataI[step]));
            step++;
        }
        List<Integer> foo;
        boolean same;
        List<List<Integer>> combinations = new ArrayList<>();
        for(int one = 5; one < 10; one++)
            for(int two = 5; two < 10; two++)
                for(int three = 5; three < 10; three++)
                    for(int four = 5; four < 10; four++)
                        for(int five = 5; five < 10; five++) {
                            foo = new ArrayList<>();
                            same = false;
                            foo.add(one);
                            if(foo.contains(two)) same = true;
                            foo.add(two);
                            if(foo.contains(three)) same = true;
                            foo.add(three);
                            if(foo.contains(four)) same = true;
                            foo.add(four);
                            if(foo.contains(five)) same = true;
                            foo.add(five);
                            if(!same)
                                combinations.add(foo);
                        }
        output = 0;
        int[] tempOutput = {0};
        Amplifier[] amplifiers = new Amplifier[5];
        for(List<Integer> entry : combinations) {
            for(i = 0; i < 5; i++) amplifiers[i] = new Amplifier((ArrayList<Integer>) dataS.clone(),entry.get(i),0);
            i=0;
            while(amplifiers[i].process(tempOutput) || i != 4) {
                if(i == 4) i = 0;
                else i++;
            }
            if(output < tempOutput[0]) output = tempOutput[0];
            tempOutput[0] = 0;
        }
        System.out.println(output);
    }

    public static void main(String[] args) throws IOException {
        secondSolution();
    }
}
