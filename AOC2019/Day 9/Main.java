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

    public static void process(ArrayList<Double> dataS) {
        int step = 0;
        int opcode, fmode,smode,tmode;
        int[] relative = {0};
        while(step < dataS.size()) {
            if(dataS.get(step) == 99) break;
            else {
                double temp = dataS.get(step);
                double temp1 = temp % 10;
                temp = Math.floor(temp / 10);
                opcode = (int) Math.round((temp % 10) * 10 + temp1);
                temp = Math.floor(temp / 10);
                fmode = (int) Math.round(temp % 10);
                temp = Math.floor(temp / 10);
                smode = (int) Math.round(temp % 10);
                temp = Math.floor(temp / 10);
                tmode = (int) Math.round(temp % 10);
                step = auxProcess(dataS,opcode,fmode,smode,tmode,step,relative);
            }
        }
    }

    public static int auxProcess(ArrayList<Double> dataS, int opcode,int mode1, int mode2,int mode3, int step, int[] relative) {
        double firstParam, secondParam;
        Scanner input = new Scanner(System.in);
        int store,temp;
        if(mode1 != 1) {
            temp = (int) Math.round(dataS.get(step + 1));
            if(mode1 == 2) temp += relative[0];
            while(temp >= dataS.size())
                dataS.add(0.0);
            firstParam = dataS.get(temp);
        }
        else
            firstParam = dataS.get(step + 1);

        if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
            if(mode2 != 1) {
                temp = (int) Math.round(dataS.get(step + 2));
                while(temp >= dataS.size())
                    dataS.add(0.0);
                secondParam = mode1 == 0 ? dataS.get(temp) : dataS.get(temp + relative[0]);
            }
            else
                secondParam = dataS.get(step + 2);
        }
        else secondParam = 0;
        if(opcode == 1) {
            store = (int) Math.round(dataS.get(step+3));
            if(mode3 == 2) store += relative[0];
            while(store >= dataS.size()) {
                dataS.add(0.0);
            }
            dataS.set(store,firstParam + secondParam);
            step += 4;
        }
        else if(opcode == 2) {
            store = (int) Math.round(dataS.get(step+3));
            if(mode3 == 2) store += relative[0];
            while(store >= dataS.size()) {
                dataS.add(0.0);
            }
            dataS.set(store,firstParam * secondParam);
            step += 4;
        }
        else if(opcode == 3) {
            store = (int) Math.round(dataS.get(step + 1));
            if(mode1 == 2) store += relative[0];
            while(store >= dataS.size()) {
                dataS.add(0.0);
            }
            dataS.set(store, input.nextDouble());
            step += 2;
        }
        else if(opcode == 4) {
            System.out.printf("%.0f\n", firstParam);
            step += 2;
        }
        else if(opcode == 5) {
            if(firstParam != 0) step = (int) secondParam;
            else step += 3;
        }
        else if(opcode == 6) {
            if(firstParam == 0) step = (int) secondParam;
            else step += 3;
        }
        else if(opcode == 7) {
            store = (int) Math.round(dataS.get(step + 3));
            if(mode3 == 2) store += relative[0];
            while(store >= dataS.size())
                dataS.add(0.0);
            dataS.set(store,firstParam < secondParam ? (double) 1 : 0.0);
            step += 4;
        }
        else if(opcode == 8) {
            store = (int) Math.round(dataS.get(step + 3));
            if(mode3 == 2) store += relative[0];
            while(store >= dataS.size())
                dataS.add(0.0);
            dataS.set(store,firstParam == secondParam ? (double) 1 : 0.0);
            step += 4;
        }
        else if(opcode == 9) {
            relative[0] += (int) firstParam;
            step += 2;
        }
        return step;
    }

    public static void firstAndSecondSolution() throws IOException {
        String data = read().get(0);
        String[] dataI = data.split(",");
        ArrayList<Double> dataS = new ArrayList<>();
        int step = 0;
        while(step < dataI.length) {
            dataS.add(Double.parseDouble(dataI[step]));
            step++;
        }
        process(dataS);
    }

    public static void main(String[] args) throws IOException {
        firstAndSecondSolution();
    }
}
