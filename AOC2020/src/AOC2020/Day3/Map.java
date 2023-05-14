package AOC2020.Day3;

public class Map {
    int height, width;
    char[][] map;

    public Map(int height, int width, char[][] map) {
        this.height = height;
        this.width = width;
        this.map = map;
    }

    public int howManyTreesInTraversal(int right, int down) {
        int count = 0;
        int x = 0,y = 0;
        for(;y < height;y += down,x += right) {
            x %= width;
            if(map[y][x] == '#')
                count++;
        }
        return count;
    }
}
