package AOC2020.Day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Xmas {
    HashSet<Integer> numberSearch;
    int preambleLength;
    LinkedList<Integer> lastNumbers;
    ArrayList<String> input;
    int invalidNumber;

    public Xmas(ArrayList<String> input, int preambleLength) {
        this.preambleLength = preambleLength;
        this.input = input;
        numberSearch = new HashSet<>();
        lastNumbers = new LinkedList<>();
    }

    public int resolvePart1() {
        constructPreamble();
        int retValue = 0;
        for(int i = preambleLength; i < input.size(); i++) {
            int number = Integer.parseInt(input.get(i));
            if(!hasSum(number)) {
                retValue = number;
                break;
            }
            else {
                numberSearch.remove(lastNumbers.remove());
                numberSearch.add(number);
                lastNumbers.add(number);
            }
        }
        return retValue;
    }

    private boolean hasSum(int number) {
        boolean hasSum = false;
        for(Integer num : numberSearch) {
            int complement = number - num;
            if(numberSearch.contains(complement)) {
                hasSum = true;
                break;
            }
        }
        return hasSum;
    }

    private void constructPreamble() {
        for(int i = 0; i < preambleLength; i++) {
            int number = Integer.parseInt(input.get(i));
            lastNumbers.add(number);
            numberSearch.add(number);
        }
    }

    public Xmas(ArrayList<String> input, String invalidNumber) {
        numberSearch = new HashSet<>();
        lastNumbers = new LinkedList<>();
        this.invalidNumber = Integer.parseInt(invalidNumber);
        this.input = input;
    }

    public int resolvePart2() {
        int i,j,solValue = 0;
        for(i = 0; i < input.size(); i++) {
            int sum = Integer.parseInt(input.get(i));
            int smallest = sum, largest = sum;
            for (j = i + 1; j < input.size() && sum <= invalidNumber; j++) {
                int number = Integer.parseInt(input.get(j));
                sum += number;
                smallest = Math.min(number, smallest);
                largest = Math.max(number,largest);
                if(sum == invalidNumber)
                    return smallest + largest;
            }
        }
        return solValue;
    }
}
