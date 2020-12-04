import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day4
{
    public static void main(String[] args)
    {
        try
        {
            List<String> input = Files.readAllLines(Paths.get("C:/Users/HOBA/source/repos/advent-of-code-2020/day4/src/input"));

            String passport = "";
            int sum = 0;
            for (int l = 0; l < input.size(); ++l)
            {
                String line = input.get(l);
                if (line.isEmpty())
                {
                    boolean isValid = checkPassportPart2(passport);
                    if (isValid)
                        sum++;
                    passport = "";
                }
                else
                {
                    passport = passport.concat(" ");
                    passport = passport.concat(line);
                    if (l == input.size() - 1)
                    {
                        boolean isValid = checkPassportPart2(passport);
                        if (isValid)
                            sum++;
                    }
                }
            }
            System.out.println("Answer is " + sum);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static boolean checkPassportPart1(String passport)
    {
        boolean byr = passport.contains("byr");
        if (!byr)
            return false;
        boolean iyr = passport.contains("iyr");
        if (!iyr)
            return false;
        boolean eyr = passport.contains("eyr");
        if (!eyr)
            return false;
        boolean hgt = passport.contains("hgt");
        if (!hgt)
            return false;
        boolean hcl = passport.contains("hcl");
        if (!hcl)
            return false;
        boolean ecl = passport.contains("ecl");
        if (!ecl)
            return false;
        boolean pid = passport.contains("pid");
        if (!pid)
            return false;
        boolean cid = passport.contains("cid");
        return true;
    }

    private static boolean checkPassportPart2(String passport)
    {
        if (!checkPassportPart1(passport))
            return false;

        String byr = getSubstring(passport, "byr");
        if (byr.length() != 4)
            return false;
        int byear = Integer.parseInt(byr);
        if (byear < 1920 || byear > 2002)
            return false;

        String iyr = getSubstring(passport, "iyr");
        if (iyr.length() != 4)
            return false;
        int iyear = Integer.parseInt(iyr);
        if (iyear < 2010 || iyear > 2020)
            return false;

        String eyr = getSubstring(passport, "eyr");
        if (eyr.length() != 4)
            return false;
        int eyear = Integer.parseInt(eyr);
        if (eyear < 2020 || eyear > 2030)
            return false;

        String hgt = getSubstring(passport, "hgt");
        if (!(hgt.endsWith("cm") || (hgt.endsWith("in"))))
            return false;
        int height = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
        if (hgt.endsWith("cm"))
            if (height < 150 || height > 193)
                return false;
        if (hgt.endsWith("in"))
            if (height < 59 || height > 76)
                return false;

        String hcl = getSubstring(passport, "hcl");
        if (!hcl.startsWith("#"))
            return false;
        if (hcl.length() != 7)
            return false;

        String digitsReplaced = hcl.substring(1).replaceAll("[0-9]", "");
        String lettersReplaced = digitsReplaced.replaceAll("[a-f]", "");
        if (!lettersReplaced.isEmpty())
            return false;

        String ecl = getSubstring(passport, "ecl");
        if (!(ecl.equals("amb") || ecl.equals("blu") || ecl.equals("brn") || ecl.equals("gry") ||
                ecl.equals("grn") || ecl.equals("hzl") || ecl.equals("oth")))
            return false;

        String pid = getSubstring(passport, "pid");
        return pid.length() == 9;
    }

    private static String getSubstring(String passport, String field)
    {
        int start = passport.indexOf(field);
        int end = passport.indexOf(' ', start);
        if (end == -1)
            return  passport.substring(start + field.length() + 1);

        return passport.substring(start + field.length() + 1, end);
    }
}
