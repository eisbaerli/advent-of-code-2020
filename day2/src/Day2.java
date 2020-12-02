import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day2
{
    public static void main(String[] args)
    {
        try
        {
            List<String> input = Files.readAllLines(Paths.get("C:/Users/HOBA/source/repos/advent-of-code-2020/day2/src/input"));
            List<Boolean> collect = input.stream().map((String line) ->
            {
                int index = line.indexOf(":");
                String check = line.substring(0, index);
                String pwd = line.substring(index + 1);

                int i = check.indexOf("-");
                int min = Integer.parseInt(check.substring(0, i));
                String substring = check.substring(i + 1, check.length() - 2);
                int max = Integer.parseInt(substring);
                char charAt = check.charAt(check.length() - 1);

                return Part2(pwd, min, max, charAt);

            }).collect(Collectors.toList());
            long count = collect.stream().filter(p -> p).count();
            System.out.println("Answer is " + count);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static boolean Part1(String pwd, int min, int max, char charAt)
    {
        int sum = 0;
        for (char c : pwd.toCharArray())
        {
            if (c == charAt)
                sum++;
        }

        return min <= sum && sum <= max;
    }

    private static boolean Part2(String pwd, int min, int max, char charAt)
    {
        if (pwd.charAt(min) == charAt)
            return pwd.charAt(max) != charAt;

        return pwd.charAt(max) == charAt;
    }
}
