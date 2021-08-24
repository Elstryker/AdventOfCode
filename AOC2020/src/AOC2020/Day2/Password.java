package AOC2020.Day2;

public class Password {
    int low, high;
    char letter;
    String password;

    public Password(int low, int high, char letter, String password) {
        this.low = low;
        this.high = high;
        this.letter = letter;
        this.password = password;
    }

    public boolean isValid() {
        int count = 0;
        for(int i = 0; i < password.length();i++)
            if(password.charAt(i) == letter)
                count++;
        return count >= low && count <= high;
    }

    public boolean isValid2() {
        boolean first = password.charAt(low-1) == letter;
        boolean second = password.charAt(high-1) == letter;
        return first ^ second;
    }
}
