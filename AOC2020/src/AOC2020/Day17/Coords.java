package AOC2020.Day17;

public class Coords {
    int x,y,z,w;

    public Coords(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 0;
    }

    public Coords(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coords)) return false;
        Coords coords = (Coords) o;
        return x == coords.x && y == coords.y && z == coords.z && w == coords.w;
    }
}
