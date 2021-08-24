package AOC2020.Day5;

public class BoardingPass {
    String row, column;

    public BoardingPass(String sequence) {
        this.row = sequence.substring(0,7);
        this.column = sequence.substring(7);
    }

    public long getSeatID() {
        int rowSeat = getRowSeat();
        int columnSeat = getColumnSeat();
        return rowSeat * 8L + columnSeat;
    }

    private int getColumnSeat() {
        int high = 7;
        int bit = 4;
        for(int i = 0; i < 3; i++, bit/=2) {
            char position = column.charAt(i);
            if(position == 'L') {
                high -= bit;
            }
        }
        return high;
    }

    private int getRowSeat() {
        int high = 127;
        int bit = 64;
        for(int i = 0; i < 7; i++, bit/=2) {
            char position = row.charAt(i);
            if(position == 'F') {
                high -= bit;
            }
        }
        return high;
    }
}
