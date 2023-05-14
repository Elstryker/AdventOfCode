package AoC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    public static List<String> read() throws IOException {
        List<String> ret;
        ret = Files.readAllLines(Paths.get("../Advent of Code/input.txt"));
        return ret;
    }

    public static int whichCoord(Point a, Point b) {
        if(a.x - b.x == 0) return 2;
        else return 1;
    }

    public static int distance(Point a, Point b) {
        if(a.x - b.x == 0) return Math.abs(a.y - b.y);
        else return Math.abs(a.x - b.x);
    }

    public static boolean isIntersection(Point one,Point two, Point three, Point four) {
        if(whichCoord(one,two) == 1) {
            if(one.x > two.x) {
                if(three.y < four.y) {
                    return three.y < one.y && four.y > one.y && three.x < one.x && three.x > two.x;
                }
                else {
                    return four.y < one.y && three.y > one.y && three.x > two.x && three.x < one.x;
                }
            }
            else {
                if(three.y < four.y) {
                    return three.y < one.y && four.y > one.y && three.x < two.x && three.x > one.x;
                }
                else {
                    return four.y < one.y && three.y > one.y && three.x < two.x && three.x > one.x;
                }
            }
        }
        else {
            if(one.y > two.y) {
                if(three.x < four.x) {
                    return three.x < one.x && four.x > one.x && two.y < three.y && one.y > three.y;
                }
                else {
                    return four.x < one.x && three.x > one.x && two.y < three.y && one.y > three.y;
                }
            }
            else {
                if(three.x < four.x) {
                    return three.x < one.x && four.x > one.x && two.y > three.y && one.y < three.y;
                }
                else {
                    return four.x < one.x && three.x > one.x && two.y > three.y && one.y < three.y;
                }
            }
        }
    }

    public static List<Point> intersections(List<Point> wire1, List<Point> wire2, List<Integer> steps) {
        List<Point> ret = new ArrayList<>();
        int step1 = 0, step2;
        int i,j,temp, x,y;
        for(i = 1; i < wire1.size();i++) {
            step2 = 0;
            for(j = 1; j < wire2.size();j++) {
                if(isIntersection(wire1.get(i-1),wire1.get(i),wire2.get(j-1),wire2.get(j))) {
                    if(whichCoord(wire1.get(i-1),wire1.get(i)) == 1) {
                        y = wire1.get(i).y;
                        x = wire2.get(j).x;
                    }
                    else {
                        x = wire1.get(i).x;
                        y = wire2.get(j).y;
                    }
                    ret.add(new Point(x,y));
                    int temp1 = distance(wire1.get(i-1),new Point(x,y)),temp2 = distance(wire2.get(j-1),new Point(x,y));
                    steps.add(step1 + temp1 + step2 + temp2);
                }
                step2 += distance(wire2.get(j-1),wire2.get(j));
            }
            step1 += distance(wire1.get(i-1),wire1.get(i));
        }
        return ret;
    }

    public static List<Point> importWire(String data) {
        String[] dataSplit = data.split(",");
        List<Point> ret = new ArrayList<>();
        int i = 0, temp;
        Point p;
        ret.add(new Point(0,0));
        for(String x : dataSplit) {
            temp = Integer.parseInt(x.substring(1));
            p = new Point(ret.get(i).x,ret.get(i).y);
            switch (x.charAt(0)) {
                case 'R':
                    ret.add(new Point(p.x + temp,p.y));
                    i++;
                    break;
                case 'L':
                    ret.add(new Point(p.x - temp,p.y));
                    i++;
                    break;
                case 'D':
                    ret.add(new Point(p.x,p.y - temp));
                    i++;
                    break;
                case 'U':
                    ret.add(new Point(p.x,p.y + temp));
                    i++;
                    break;
                default:
                    break;
            }
        }
        return ret;
    }

    public static void firstSolution() throws IOException {
        List<String> data = read();
        List<Point> wire1 = importWire(data.get(0));
        List<Point> wire2 = importWire(data.get(1));
        List<Integer> n = new ArrayList<>();
        List<Point> inters = intersections(wire1,wire2,n);
        System.out.println(minDistance(inters));
    }

    private static int minDistance(List<Point> inters) {
        int minDistance = Integer.MAX_VALUE;
        for(Point a:inters) {
            if(Math.abs(a.x) + Math.abs(a.y) < minDistance) minDistance = Math.abs(a.x) + Math.abs(a.y);
        }
        return minDistance;
    }

    public static void secondSolution() throws IOException {
        List<String> data = read();
        List<Point> wire1 = importWire(data.get(0));
        List<Point> wire2 = importWire(data.get(1));
        List<Integer> n = new ArrayList<>();
        List<Point> a = intersections(wire1,wire2,n);
        System.out.println(minSteps(n));
    }

    private static int minSteps(List<Integer> n) {
        return Collections.min(n);
    }

    public static void main(String[] args) throws IOException {
        firstSolution();
        secondSolution();
    }
}
