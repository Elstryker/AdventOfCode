package AOC2020.Day4;

import java.util.regex.Pattern;

public class PassPort {
    public String byr = "",iyr = "",eyr = "",hgt = "",hcl = "",ecl = "",pid = "",cid = "";



    public boolean isValid() {
        return byr != "" && iyr != "" && eyr != "" && hgt != "" && hcl != "" && ecl != "" && pid != "";
    }

    public boolean isValid2() {
        boolean valid = false;
        if (byr != "" && iyr != "" && eyr != "" && hgt != "" && hcl != "" && ecl != "" && pid != "") {
            boolean a = checkbyr(),
                    b = checkiyr(),
                    c = checkeyr(),
                    d = checkhgt(),
                    e = checkhcl(),
                    f = checkecl(),
                    g = checkpid();
            valid = a && b && c && d && e && f && g;
            if (!valid) {
                int lol = 1;
            }
        }
        return valid;
    }

    private boolean checkpid() {
        Pattern pattern = Pattern.compile("^[0-9]{9}$");
        return pattern.matcher(pid).find();
    }

    private boolean checkecl() {
        Pattern pattern = Pattern.compile("amb|blu|brn|gry|grn|hzl|oth");
        return pattern.matcher(ecl).find();
    }

    private boolean checkhcl() {
        Pattern pattern = Pattern.compile("^#[0-9a-f]{6}$");
        return pattern.matcher(hcl).find();
    }

    private boolean checkhgt() {
        int value = Integer.parseInt(hgt.replaceAll("[^0-9]",""));
        String med = hgt.replaceAll("[0-9]","");
        if(med.equals("cm")) {
            return value >= 150 && value <= 193;
        }
        else if( med.equals("in")) {
            return value >= 59 && value <= 76;
        }
        return false;
    }

    private boolean checkeyr() {
        int inteyr = Integer.parseInt(eyr);
        return inteyr >= 2020 && inteyr <= 2030;
    }

    private boolean checkbyr() {
        int intbyr = Integer.parseInt(byr);
        return intbyr >= 1920 && intbyr <= 2002;
    }

    private boolean checkiyr() {
        int intiyr = Integer.parseInt(iyr);
        return intiyr >= 2010 && intiyr <= 2020;
    }

    public PassPort(String passport) {
        String[] tokens = passport.split(" ");
        for (String tuple : tokens) {
            String[] pairKeyValue = tuple.split(":");
            switch (pairKeyValue[0]) {
                case "byr" -> byr = pairKeyValue[1];
                case "iyr" -> iyr = pairKeyValue[1];
                case "eyr" -> eyr = pairKeyValue[1];
                case "hgt" -> hgt = pairKeyValue[1];
                case "hcl" -> hcl = pairKeyValue[1];
                case "ecl" -> ecl = pairKeyValue[1];
                case "pid" -> pid = pairKeyValue[1];
                case "cid" -> cid = pairKeyValue[1];
            }
        }
    }
}
