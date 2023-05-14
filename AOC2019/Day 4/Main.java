package AoC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static List<String> read() throws IOException {
        List<String> ret;
        ret = Files.readAllLines(Paths.get("../Advent of Code/input.txt"));
        return ret;
    }

    public static boolean isOK(int num) {
        int digit;
        boolean isDouble = false, isOK = true;
        int length = String.valueOf(num).length();
        int anterior = num % 10;
        num /= 10;
        length--;
        while(length != 0) {
            digit = num % 10;
            num /= 10;
            if(anterior == digit) {
                isDouble = true;
            }
            if(anterior < digit) {
                isOK = false;
                break;
            }
            anterior = digit;
            length--;
        }
        return isDouble && isOK;
    }

    public static boolean isOKPart2(String num) {
        int length = num.length();
        int i = 0,digit;
        int anterior = Integer.parseInt(String.valueOf(num.charAt(i)));
        i++;
        boolean isDouble = false, isOK = true;
        while(i < length) {
            digit = Integer.parseInt(String.valueOf(num.charAt(i)));
            if(anterior > digit) {
                isOK = false;
                break;
            }
            if(anterior == digit && !isDouble) {
                if(i == 1) {
                    if (Integer.parseInt(String.valueOf(num.charAt(i + 1))) != anterior) isDouble = true;
                }
                else {
                    boolean b = Integer.parseInt(String.valueOf(num.charAt(i - 2))) != anterior;
                    if((i == length - 1 && b)) isDouble = true;
                    else if(b && Integer.parseInt(String.valueOf(num.charAt(i+1))) != anterior) isDouble = true;
                }
            }
            anterior = digit;
            i++;
        }
        return isDouble && isOK;
    }

    public static void firstSolution() throws IOException {
        List<String> data = read();
        String[] dataProcess = data.get(0).split("-");
        int higher;
        higher = Integer.parseInt(dataProcess[1]);
        int count = 0, number = Integer.parseInt(dataProcess[0]);
        while(number < higher) {
            if(isOK(number)) count++;
            number++;
        }
        System.out.println(count);
    }

    public static void secondSolution() throws IOException {
        List<String> data = read();
        String[] dataProcess = data.get(0).split("-");
        int higher;
        higher = Integer.parseInt(dataProcess[1]);
        int count = 0, number = Integer.parseInt(dataProcess[0]);
        while(number < higher) {
            if(isOKPart2(String.valueOf(number))) {
                count++;
                System.out.println(number);
            }
            number++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        firstSolution();
        secondSolution();
    }
}
