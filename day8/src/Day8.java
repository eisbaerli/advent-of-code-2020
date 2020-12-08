import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day8
{
    public static void main(String[] args)
    {
        try
        {
            List<String> input = Files.readAllLines(Paths.get("C:/Users/HOBA/source/repos/advent-of-code-2020/day8/src/input"));

            for (int i = 0; i < input.size(); ++i)
            {
                List<String> manipulatedInput = manipulateInput(input, i);
                Optional<Integer> terminated = runProgram(manipulatedInput);
                if (terminated.isPresent())
                {
                    System.out.println("The answer is " + terminated.get());
                    break;
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static List<String> manipulateInput(List<String> input, int i)
    {
        String[] inputArray = new String[input.size()];
        inputArray = input.toArray(input.toArray(inputArray));
        String line = input.get(i);
        if (line.startsWith("jmp"))
        {
            inputArray[i] = line.replaceAll("jmp", "nop");
            return Arrays.asList(inputArray);
        }

        if (line.startsWith("nop"))
        {
            inputArray[i] = line.replaceAll("nop", "jmp");
            return Arrays.asList(inputArray);
        }
        return input;
    }

    private static Optional<Integer> runProgram(List<String> input)
    {
        int accumulator = 0;
        int actualLine = 0;
        Set<Integer> linesVisited = new HashSet<>();
        while(true)
        {
            String line = input.get(actualLine);
            if (line.startsWith("nop"))
            {
                actualLine++;
                boolean isNew = linesVisited.add(actualLine);
                if (!isNew)
                {
                    return Optional.empty();
                }
                if (actualLine >= input.size())
                {
                    return Optional.of(accumulator);
                }
            }
            else if (line.startsWith("acc"))
            {
                actualLine++;
                boolean isNew = linesVisited.add(actualLine);
                if (!isNew)
                {
                    return Optional.empty();
                }
                accumulator = getInt(accumulator, line);
                if (actualLine >= input.size())
                {
                    return Optional.of(accumulator);
                }
            }
            else
            {
                actualLine = getInt(actualLine, line);
                boolean isNew = linesVisited.add(actualLine);
                if (!isNew)
                {
                    return Optional.empty();
                }
                if (actualLine >= input.size())
                {
                    return Optional.of(accumulator);
                }
            }
        }
    }

    private static int getInt(int accumulator, String line)
    {
        String accReplaced = line.replaceAll("acc ", "");
        boolean contains = accReplaced.contains("+");
        int parseInt = Integer.parseInt(accReplaced.replaceAll("[^0-9]", ""));
        if (contains)
        {
            accumulator += parseInt;
        }
        else
        {
            accumulator -= parseInt;
        }
        return accumulator;
    }
}
