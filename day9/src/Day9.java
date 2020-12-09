import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day9
{
    public static void main(String[] args)
    {
        try
        {
            List<String> input = Files.readAllLines(Paths.get("C:/Users/HOBA/source/repos/advent-of-code-2020/day9/src/input"));
            List<Long> numbers = input.stream().map(Long::parseUnsignedLong).collect(Collectors.toList());

            int preamble = 25;
            List<Long> xmas = numbers.subList(0, preamble);

            long part1 = Part1(numbers, preamble, xmas);

            List<Long> sameNumbers = input.stream().map(Long::parseUnsignedLong).collect(Collectors.toList());
            long sum = FindSumPart2(sameNumbers, part1);
            System.out.println("The answer is " + sum);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static long Part1(List<Long> numbers, int preamble, List<Long> xmas)
    {
        for (int i = preamble; i < numbers.size(); ++i)
        {
            final Long currentNumber = numbers.get(i);
            boolean found = FindSum(preamble, xmas, currentNumber);
            if (!found)
            {
                System.out.println("The answer is " + currentNumber);
                return currentNumber;
            }
        }
        return -1;
    }

    private static boolean FindSum(int preamble, List<Long> xmas, Long currentNumber)
    {
        for (long x : xmas.subList(0, preamble))
        {
            for (long y : xmas.subList(0, preamble))
            {
                if (x != y)
                {
                    if (x + y == currentNumber)
                    {
                        Long firstElement = xmas.get(0);
                        xmas.remove(firstElement);
                        xmas.add(currentNumber);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static long FindSumPart2(List<Long> xmas, Long currentNumber)
    {
        for (int i = 0; i < xmas.size(); ++i)
        {
            Set<Long> numbers = new HashSet<>();
            numbers.add(xmas.get(i));
            for (int j = i; i + j < xmas.size(); ++j)
            {
                numbers.add(xmas.get(j));
                Long sum = numbers.stream().reduce(0L, Long::sum);
                if (sum.equals(currentNumber))
                {
                    Long min = numbers.stream().min(Long::compareTo).orElse(0L);
                    Long max = numbers.stream().max(Long::compareTo).orElse(0L);
                    return min + max;
                }
            }
            numbers.clear();
        }
        return -1;
    }
}
