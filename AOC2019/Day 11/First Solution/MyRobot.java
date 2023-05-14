package AoC;

import java.util.ArrayList;

enum Direction {
    Up,
    Down,
    Right,
    Left;
}

class Coord {
    int x,y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coord)) return false;
        Coord coord = (Coord) o;
        return x == coord.x &&
                y == coord.y;
    }
}

public class MyRobot {

    Direction direction;
    int x,y,rows,cols;
    char[][] grid;
    ArrayList<Coord> visited;

    public MyRobot() {
        direction = Direction.Up;
        rows = cols = 500;
        x = rows / 2;
        y = cols / 2;
        grid = new char[rows][cols];
        visited = new ArrayList<>();
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                grid[i][j] = '.';
    }

    public void leftRotation() {
        switch (direction) {
            case Up:
                direction = Direction.Left;
                break;
            case Down:
                direction = Direction.Right;
                break;
            case Left:
                direction = Direction.Down;
                break;
            case Right:
                direction = Direction.Up;
                break;
        }
    }

    public void rightRotation() {
        switch (direction) {
            case Up:
                direction = Direction.Right;
                break;
            case Down:
                direction = Direction.Left;
                break;
            case Left:
                direction = Direction.Up;
                break;
            case Right:
                direction = Direction.Down;
                break;
        }
    }

    public void paint(double colour) {
        if (colour == 0) this.grid[y][x] = '.';
        else this.grid[y][x] = '#';
    }

    public void move() {
        switch (direction) {
            case Up:
                y--;
                break;
            case Down:
                y++;
                break;
            case Left:
                x--;
                break;
            case Right:
                x++;
                break;
        }
    }

    public double getColourInput() {
        if(this.grid[y][x] == '.') return 0;
        else return 1;
    }

    public void process(double foutput, double soutput) {
        paint(foutput);
        Coord temp = new Coord(x,y);
        if(!visited.contains(temp)) visited.add(temp);
        if(soutput == 0) leftRotation();
        else rightRotation();
        move();
    }

    private void print() {
        for(int i = 0;i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == y && j == x) {
                    switch (direction) {
                        case Right:
                            System.out.print("> ");
                            break;
                        case Left:
                            System.out.print("< ");
                            break;
                        case Down:
                            System.out.print("v ");
                            break;
                        case Up:
                            System.out.print("^ ");
                            break;
                    }
                } else {
                    if (grid[i][j] == '.')
                        System.out.print(". ");
                    else
                        System.out.print("# ");
                }
            }
            System.out.print("\n");
        }
    }
}
