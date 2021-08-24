package AoC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static List<String> read() throws IOException {
        List<String> ret;
        ret = Files.readAllLines(Paths.get("../Advent of Code/input.txt"));
        return ret;
    }

    public static int greatestCommonDivisor(int a, int b) {
        while(a > 0 && b > 0 && a != b) {
            if(a > b) a -= b;
            else b -= a;
        }
        return Math.min(a, b);
    }

    public static void resetMatrix(char[][] newOne, char[][] model, int rows, int columns) {
        for(int i = 0; i < rows; i++) {
            if (columns >= 0) System.arraycopy(model[i], 0, newOne[i], 0, columns);
        }
    }

    public static int[] firstSolution() throws IOException {
        List<String> data = read();
        int rows, columns,i,j,temp,num = 0,finalRow = 0,finalCol = 0;
        columns = data.get(0).length();
        rows = data.size();
        char[][] baseMatrix = new char[rows][columns];
        char[][] currentMatrix = new char[rows][columns];
        for(i = 0; i < rows; i++) {
            for(j = 0; j < columns; j++) {
                baseMatrix[i][j] = data.get(i).charAt(j);
            }
        }
        resetMatrix(currentMatrix,baseMatrix,rows,columns);
        for(i = 0; i < rows; i++) {
            for(j = 0; j < columns; j++) {
                if(currentMatrix[i][j] != '.') {
                    temp = analyze(currentMatrix, i, j, rows, columns);
                    if(temp > num) {
                        num = temp;
                        finalRow = i;
                        finalCol = j;
                    }
                    resetMatrix(currentMatrix,baseMatrix,rows,columns);
                }
            }
        }
        System.out.println("Coords: " + finalCol + "," + finalRow + "   with " + num + " asteroids detected");
        return new int[] {finalCol,finalRow};
    }

    private static int analyze(char[][] currentMatrix, int row, int col, int rows, int columns) {
        int i,j,count = 0;
        for(i = 0; i < rows; i++) {
            for(j = 0; j < columns; j++) {
                if(currentMatrix[i][j] != '.' && (i != row || j != col)) {
                    removeUnseen(currentMatrix,row,col,rows,columns,i - row,j - col);
                }
            }
        }
        for(i = 0; i < rows; i++) {
            for(j = 0; j < columns; j++) {
                if(currentMatrix[i][j] != '.' && (i != row || j != col)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void removeUnseen(char[][] currentMatrix,int row, int col, int rows, int columns, int rowDif, int colDif) {
        int anaRow, anaCol,gcd,count = 0;
        int i,j;
        gcd = greatestCommonDivisor(Math.abs(rowDif),Math.abs(colDif));
        if(rowDif == 0) {
            anaRow = 0;
            anaCol = colDif / Math.abs(colDif);
        }
        else if(colDif == 0) {
            anaCol = 0;
            anaRow = rowDif / Math.abs(rowDif);
        }
        else {
            anaCol = colDif / gcd;
            anaRow = rowDif / gcd;
        }
        for(i = row + anaRow,j = col + anaCol; i < rows && j < columns && i >= 0 && j >= 0; i += anaRow, j += anaCol) {
            if(currentMatrix[i][j] != '.') {
                if(count == 1)
                    currentMatrix[i][j] = '.';
                else count++;
            }
        }
    }

    public static void secondSolution() throws IOException {
        int[] astCoord = firstSolution();
        Asteroid main = new Asteroid(astCoord[0],astCoord[1]);
        Asteroid temp;
        LinkedList<Asteroid> listTemp;
        List<String> data = read();
        TreeMap<Double, LinkedList<Asteroid>> angleData = new TreeMap<>();
        int rows, columns,i,j;
        columns = data.get(0).length();
        rows = data.size();
        char[][] baseMatrix = new char[rows][columns];
        for(i = 0; i < rows; i++) {
            for(j = 0; j < columns; j++) {
                baseMatrix[i][j] = data.get(i).charAt(j);
            }
        }
        double angle;
        int astx,asty;
        for(i = main.y - 1; i >= 0; i--) {
            for(j = 0; j < columns; j++) {
                if(baseMatrix[i][j] != '.') {
                    temp = new Asteroid(j,i);
                    astx = j - main.x;
                    asty = main.y - i;
                    angle = Math.toDegrees(Math.atan2(astx,asty));
                    if(angle < 0) angle += 360;
                    if((listTemp = angleData.putIfAbsent(angle,new LinkedList<>(Collections.singletonList(temp)))) != null) {
                        listTemp.add(temp);
                    }
                }
            }
        }
        for(i = main.y + 1; i < rows; i++) {
            for(j = 0; j < columns; j++) {
                if(baseMatrix[i][j] != '.') {
                    temp = new Asteroid(j,i);
                    astx = j - main.x;
                    asty = main.y - i;
                    angle = Math.toDegrees(Math.atan2(astx,asty));
                    if(angle < 0) angle += 360;
                    if((listTemp = angleData.putIfAbsent(angle,new LinkedList<>(Collections.singletonList(temp)))) != null) {
                        listTemp.add(temp);
                    }
                }
            }
        }
        for(i = main.x - 1; i >= 0; i--) {
            if(baseMatrix[main.y][i] != '.') {
                temp = new Asteroid(i,main.y);
                astx = i - main.x;
                asty = 0;
                angle = Math.toDegrees(Math.atan2(astx,asty));
                if(angle < 0) angle += 360;
                if((listTemp = angleData.putIfAbsent(angle,new LinkedList<>(Collections.singletonList(temp)))) != null) {
                    listTemp.add(temp);
                }
            }
        }
        for(i = main.x + 1; i < columns; i++) {
            if (baseMatrix[main.y][i] != '.') {
                temp = new Asteroid(i, main.y);
                astx = i - main.x;
                asty = 0;
                angle = Math.toDegrees(Math.atan2(astx,asty));
                if(angle < 0) angle += 360;
                if ((listTemp = angleData.putIfAbsent(angle, new LinkedList<>(Collections.singletonList(temp)))) != null) {
                    listTemp.add(temp);
                }
            }
        }
        int counter = 0;
        int asteroid = 0;
        ArrayList<Double> repeated = new ArrayList<>();
        while(angleData.size() > 0) {
            for(Map.Entry<Double,LinkedList<Asteroid>> entry : angleData.entrySet()) {
                counter++;
                temp = entry.getValue().poll();
                if(counter == 200) {
                    assert temp != null;
                    asteroid = temp.x * 100 + temp.y;
                }
                if(entry.getValue().size() == 0)
                    repeated.add(entry.getKey());
            }
            for(Double key : repeated)
                angleData.remove(key);
            repeated.clear();
        }
        System.out.println(asteroid);
    }

    public static void main(String[] args) throws IOException {
        secondSolution();
    }
}

class Asteroid {
    int x,y;

    public Asteroid(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
