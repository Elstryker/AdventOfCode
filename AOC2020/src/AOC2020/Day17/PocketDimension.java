package AOC2020.Day17;

import java.util.ArrayList;
import java.util.HashSet;

public class PocketDimension {
    ArrayList<Coords> activeCells;
    int cycle;
    final int initialRows, initialCols;

    public PocketDimension(ArrayList<String> input) {
        this.activeCells = new ArrayList<>();
        this.cycle = 1;
        this.initialRows = input.size();
        this.initialCols = input.get(0).length();
        for(int i = 0; i < input.size(); i++) {
            for(int j = 0; j < input.get(i).length(); j++) {
                if(input.get(i).charAt(j) == '#') {
                    Coords newActive = new Coords(j,i,0,0);
                    activeCells.add(newActive);
                }
            }
        }
    }

    public ArrayList<Coords> getActiveNeighbours(Coords target) {
        ArrayList<Coords> neighbours = new ArrayList<>();
        for(int x = target.x - 1; x <= target.x + 1; x++) {
            for(int y = target.y - 1; y <= target.y + 1; y++) {
                for(int z = target.z - 1; z <= target.z + 1; z++) {
                    if(x == target.x && y == target.y && z == target.z)
                        continue;
                    Coords newCoord = new Coords(x,y,z);
                    if(activeCells.contains(newCoord))
                        neighbours.add(newCoord);
                }
            }
        }
        return neighbours;
    }

    public int firstPart() {
        while(cycle < 7) {
            generateNewCycle();
            cycle++;
        }
        return activeCells.size();
    }

    private void generateNewCycle() {
        ArrayList<Coords> newActive = new ArrayList<>();
        // Iterate every layer
        for(int z = -cycle; z <= cycle; z++) {
            // Iterate every row adding extra rows
            for(int row = -cycle; row < initialRows + cycle; row++) {
                // Iterate every column adding extra ones
                for(int col = -cycle; col < initialCols + cycle; col++) {
                    // Get neighbours
                    var neighBours = getActiveNeighbours(new Coords(row,col,z));
                    Coords currentCell = new Coords(row,col,z);
                    // Check if it's active or not
                    if(activeCells.contains(currentCell)) {
                        // It's active
                        if(neighBours.size() == 2 || neighBours.size() == 3) {
                            newActive.add(currentCell);
                        }
                    }
                    else {
                        // It's inactive
                        if(neighBours.size() == 3) {
                            newActive.add(currentCell);
                        }
                    }
                }
            }
        }
        this.activeCells = newActive;
    }

    public int secondPart() {
        while(cycle < 7) {
            generateNewCycle2();
            cycle++;
        }
        return activeCells.size();
    }

    private void generateNewCycle2() {
        ArrayList<Coords> newActive = new ArrayList<>();
        // Iterate every dimension
        for(int w = -cycle; w <= cycle; w++) {
            // Iterate every layer
            for (int z = -cycle; z <= cycle; z++) {
                // Iterate every row adding extra rows
                for (int row = -cycle; row < initialRows + cycle; row++) {
                    // Iterate every column adding extra ones
                    for (int col = -cycle; col < initialCols + cycle; col++) {
                        // Get neighbours
                        var neighBours = getActiveNeighbours2(new Coords(row, col, z, w));
                        Coords currentCell = new Coords(row, col, z, w);
                        // Check if it's active or not
                        if (activeCells.contains(currentCell)) {
                            // It's active
                            if (neighBours.size() == 2 || neighBours.size() == 3) {
                                newActive.add(currentCell);
                            }
                        } else {
                            // It's inactive
                            if (neighBours.size() == 3) {
                                newActive.add(currentCell);
                            }
                        }
                    }
                }
            }
        }
        this.activeCells = newActive;
    }

    public ArrayList<Coords> getActiveNeighbours2(Coords target) {
        ArrayList<Coords> neighbours = new ArrayList<>();
        for(int x = target.x - 1; x <= target.x + 1; x++) {
            for(int y = target.y - 1; y <= target.y + 1; y++) {
                for(int w = target.w - 1; w <= target.w + 1; w++) {
                    for (int z = target.z - 1; z <= target.z + 1; z++) {
                        if (x == target.x && y == target.y && z == target.z && w == target.w)
                            continue;
                        Coords newCoord = new Coords(x, y, z, w);
                        if (activeCells.contains(newCoord))
                            neighbours.add(newCoord);
                    }
                }
            }
        }
        return neighbours;
    }

}
