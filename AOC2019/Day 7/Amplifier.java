package AoC;

import java.util.ArrayList;

public class Amplifier {
    ArrayList<Integer> data;
    int phase;
    int step;

    public Amplifier(ArrayList<Integer> data, int phase, int step) {
        this.data = data;
        this.phase = phase;
        this.step = step;
    }

    public boolean process(int[] output) {
        int opcode, fmode,smode;
        boolean control = true;
        while(step < data.size()) {
            if(data.get(step) == 99) {
                control = false;
                break;
            }
            else {
                int temp = data.get(step);
                int temp1 = temp % 10;
                temp /= 10;
                opcode = ((temp % 10) * 10) + temp1;
                temp /= 10;
                fmode = temp % 10;
                temp /= 10;
                smode = temp % 10;
                this.auxProcess(opcode,fmode,smode,output);
                if(opcode == 4) break;
            }
        }
        return control;
    }

    public void auxProcess(int opcode, int mode1, int mode2, int[] output) {
        int firstParam, secondParam, inttemp;
        firstParam = mode1 == 0 ? data.get(data.get(step + 1)) : data.get(step + 1);
        if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8)
            secondParam = mode2 == 0 ? data.get(data.get(step + 2)) : data.get(step + 2);
        else secondParam = 0;
        if(opcode == 1) {
            data.set(data.get(step+3),firstParam + secondParam);
            this.step += 4;
        }
        else if(opcode == 2) {
            data.set(data.get(step+3),firstParam * secondParam);
            this.step += 4;
        }
        else if(opcode == 3) {
            inttemp = phase == -1 ? output[0] : phase;
            phase = -1;
            data.set(data.get(step + 1), inttemp);
            this.step += 2;
        }
        else if(opcode == 4) {
            output[0] = firstParam;
            this.step += 2;
        }
        else if(opcode == 5) {
            if(firstParam != 0) step = secondParam;
            else this.step += 3;
        }
        else if(opcode == 6) {
            if(firstParam == 0) step = secondParam;
            else this.step += 3;
        }
        else if(opcode == 7) {
            if(firstParam < secondParam) data.set(data.get(step + 3),1);
            else data.set(data.get(step + 3),0);
            this.step += 4;
        }
        else if(opcode == 8) {
            if(firstParam == secondParam) data.set(data.get(step + 3),1);
            else data.set(data.get(step + 3),0);
            this.step += 4;
        }
    }
}
