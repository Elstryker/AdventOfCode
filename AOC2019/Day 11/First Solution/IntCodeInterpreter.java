package AoC;

import java.util.ArrayList;
import java.util.Scanner;

public class IntCodeInterpreter {

    int step,fmode,smode,tmode,relative,whichOut;
    double input,foutput,soutput;
    ArrayList<Double> data;
    MyRobot robot;

    public IntCodeInterpreter(ArrayList<Double> data) {
        this.data = data;
        this.step = 0;
        this.robot = new MyRobot();
        this.whichOut = 0;
    }

    public void process() {
        int opcode;
        while(step < data.size()) {
            if(data.get(step) == 99) break;
            else {
                double temp = data.get(step);
                double temp1 = temp % 10;
                temp = Math.floor(temp / 10);
                opcode = (int) Math.round((temp % 10) * 10 + temp1);
                temp = Math.floor(temp / 10);
                fmode = (int) Math.round(temp % 10);
                temp = Math.floor(temp / 10);
                smode = (int) Math.round(temp % 10);
                temp = Math.floor(temp / 10);
                tmode = (int) Math.round(temp % 10);
                auxProcess(opcode);
            }
        }
    }

    public void auxProcess(int opcode) {
        double firstParam, secondParam;
        Scanner input = new Scanner(System.in);
        int store,temp;
        if(fmode != 1) {
            temp = (int) Math.round(data.get(step + 1));
            if(fmode == 2) temp += relative;
            while(temp >= data.size())
                data.add(0.0);
            firstParam = data.get(temp);
        }
        else
            firstParam = data.get(step + 1);

        if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
            if(smode != 1) {
                temp = (int) Math.round(data.get(step + 2));
                while(temp >= data.size())
                    data.add(0.0);
                secondParam = fmode == 0 ? data.get(temp) : data.get(temp + relative);
            }
            else
                secondParam = data.get(step + 2);
        }
        else secondParam = 0;
        switch(opcode) {
            case 1:
                store = (int) Math.round(data.get(step+3));
                if(tmode == 2) store += relative;
                while(store >= data.size()) {
                    data.add(0.0);
                }
                data.set(store,firstParam + secondParam);
                step += 4;
                break;
            case 2:
                store = (int) Math.round(data.get(step+3));
                if(tmode == 2) store += relative;
                while(store >= data.size()) {
                    data.add(0.0);
                }
                data.set(store,firstParam * secondParam);
                step += 4;
                break;
            case 3:
                store = (int) Math.round(data.get(step + 1));
                if(fmode == 2) store += relative;
                while(store >= data.size()) {
                    data.add(0.0);
                }
                this.input = robot.getColourInput();
                data.set(store, this.input);
                step += 2;
                break;
            case 4:
                switch (whichOut) {
                    case 0:
                        foutput = firstParam;
                        whichOut++;
                        break;
                    case 1:
                        soutput = firstParam;
                        robot.process(foutput,soutput);
                        whichOut = 0;
                        break;
                }
                step += 2;
                break;
            case 5:
                if(firstParam != 0) step = (int) secondParam;
                else step += 3;
                break;
            case 6:
                if(firstParam == 0) step = (int) secondParam;
                else step += 3;
                break;
            case 7:
                store = (int) Math.round(data.get(step + 3));
                if(tmode == 2) store += relative;
                while(store >= data.size())
                    data.add(0.0);
                data.set(store,firstParam < secondParam ? (double) 1 : 0.0);
                step += 4;
                break;
            case 8:
                store = (int) Math.round(data.get(step + 3));
                if(tmode == 2) store += relative;
                while(store >= data.size())
                    data.add(0.0);
                data.set(store,firstParam == secondParam ? (double) 1 : 0.0);
                step += 4;
                break;
            case 9:
                relative += (int) firstParam;
                step += 2;
                break;
        }
    }
}
