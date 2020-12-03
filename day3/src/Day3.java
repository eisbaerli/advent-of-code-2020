import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3
{
    public static void main(String[] args)
    {
        try
        {
            List<String> input = Files.readAllLines(Paths.get("C:/Users/HOBA/source/repos/advent-of-code-2020/day3/src/input"));
            long numberOfTrees1 = getNumberOfTrees(input, 1, 1);
            long numberOfTrees2 = getNumberOfTrees(input, 3, 1);
            long numberOfTrees3 = getNumberOfTrees(input, 5, 1);
            long numberOfTrees4 = getNumberOfTrees(input, 7, 1);
            long numberOfTrees5 = getNumberOfTrees(input, 1, 2);
            long product = numberOfTrees1 * numberOfTrees2 * numberOfTrees3 * numberOfTrees4 * numberOfTrees5;
            System.out.println("Answer is " + product);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static long getNumberOfTrees(List<String> input,
                                        int right,
                                        int down)
    {
        int numberOfTrees = 0;
        int c = right;
        for(int i = down; i < input.size(); c = c + right, i = i + down)
        {
            String row = input.get(i);
            int mod = c % row.length();
            char charAt = row.charAt(mod);
            if (charAt == '#')
            {
                numberOfTrees++;
            }
        }
        return numberOfTrees;
    }
}