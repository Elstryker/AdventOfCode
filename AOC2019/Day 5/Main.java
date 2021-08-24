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

    public static void process(ArrayList<Integer> dataS) {
        int step = 0;
        int opcode, fmode,smode;
        while(step < dataS.size()) {
            if(dataS.get(step) == 1) {
                step = auxProcess(dataS,1,0,0,step);
            }
            else if(dataS.get(step) == 2) {
                step = auxProcess(dataS,2,0,0,step);
            }
            else if(dataS.get(step) == 3) {
                step = auxProcess(dataS,3,0,0,step);
            }
            else if(dataS.get(step) == 4) {
                step = auxProcess(dataS,4,0,0,step);
            }
            else if(dataS.get(step) == 5) {
                step = auxProcess(dataS,5,0,0,step);
            }
            else if(dataS.get(step) == 6) {
                step = auxProcess(dataS,6,0,0,step);
            }
            else if(dataS.get(step) == 7) {
                step = auxProcess(dataS,7,0,0,step);
            }
            else if(dataS.get(step) == 8) {
                step = auxProcess(dataS,8,0,0,step);
            }
            else if(dataS.get(step) == 99) break;
            else {
                int temp = dataS.get(step);
                int temp1 = temp % 10;
                temp /= 10;
                opcode = ((temp % 10) * 10) + temp1;
                temp /= 10;
                fmode = temp % 10;
                temp /= 10;
                smode = temp % 10;
                step = auxProcess(dataS,opcode,fmode,smode,step);
            }
        }
    }

    public static int auxProcess(ArrayList<Integer> dataS, int opcode,int mode1, int mode2, int step) {
        int firstParam, secondParam;
        Scanner input = new Scanner(System.in);
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
            dataS.set(dataS.get(step + 1), input.nextInt());
            step += 2;
        }
        else if(opcode == 4) {
            System.out.println(firstParam);
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

    public static void firstAndSecondSolution() throws IOException {
        String data = read().get(0);
        String[] dataI = data.split(",");
        ArrayList<Integer> dataS = new ArrayList<>();
        int step = 0;
        while(step < dataI.length) {
            dataS.add(Integer.parseInt(dataI[step]));
            step++;
        }
        process(dataS);
    }

    public static void main(String[] args) throws IOException {
        firstAndSecondSolution();
    }
}
