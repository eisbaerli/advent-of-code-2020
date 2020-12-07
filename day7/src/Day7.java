import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day7
{
    public static void main(String[] args)
    {
        try
        {
            List<String> input = Files.readAllLines(Paths.get("C:/Users/HOBA/source/repos/advent-of-code-2020/day7/src/input"));
            Map<String, Set<String>> bagMap = new HashMap<>();
            Map<String, Map<String, Integer>> bagMap2 = new HashMap<>();
            for (String line : input)
            {
                int index = line.indexOf("contain");
                int end = line.indexOf(" bag");
                String value = line.substring(0, end);
                String keys = line.substring(index + 8);
                if (keys.contains("no other bags"))
                    continue;

                String[] split = keys.split(",");
                for (String s : split)
                {
                    boolean startsWithBlank = s.charAt(0) == ' ';
                    char c = startsWithBlank ? s.charAt(1) : s.charAt(0);
                    int noOfBags = Integer.parseInt(String.valueOf(c));
                    String digitsRemoved = s.replaceAll("[0-9]", "");
                    int bag = digitsRemoved.indexOf(" bag");
                    int startSubstring = startsWithBlank ? 2 : 1;
                    String bagColor = digitsRemoved.substring(startSubstring, bag);
                    CreateMapPart1(bagMap, value, bagColor);
                    CreateMapPart2(bagMap2, value, bagColor, noOfBags);
                }
            }
            Part1(bagMap);
            Part2(bagMap2);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void Part1(Map<String, Set<String>> bagMap)
    {
        Set<String> bagSet = bagMap.get("shiny gold");
        while (true)
        {
            int size = bagSet.size();
            Set<String> collectBags = new HashSet<>();
            for (String bag : bagSet)
            {
                if (bagMap.containsKey(bag))
                {
                    Set<String> bags = bagMap.get(bag);
                    collectBags.addAll(bags);
                }
            }
            bagSet.addAll(collectBags);
            int newSize = bagSet.size();
            if (newSize == size)
                break;
        }
        System.out.println("Answer is " + bagSet.size());
    }

    private static void Part2(Map<String, Map<String, Integer>> bagMap)
    {
        int magicResult = magic(bagMap, "shiny gold");
        System.out.println("Answer is " + magicResult);
    }

    private static int magic (Map<String, Map<String, Integer>> bagMap, String key)
    {
        Map<String, Integer> map = bagMap.get(key);
        int sum = 0;
        for (Map.Entry<String, Integer> e : map.entrySet())
        {
            if (!bagMap.containsKey(e.getKey()))
                sum += e.getValue();
            else
                sum += magic(bagMap, e.getKey()) * e.getValue() + e.getValue();
        }
        return sum;
    }

    private static void CreateMapPart1(Map<String, Set<String>> bagMap, String value, String bagColor)
    {
        boolean contains = bagMap.containsKey(bagColor);
        if (contains)
            bagMap.get(bagColor).add(value);
        else
        {
            Set<String> set = new HashSet<>();
            set.add(value);
            bagMap.put(bagColor, set);
        }
    }

    private static void CreateMapPart2(Map<String, Map<String, Integer>> bagMap, String value, String bagColor, int noBags)
    {
        boolean contains = bagMap.containsKey(value);
        if (contains)
            bagMap.get(value).put(bagColor, noBags);
        else
        {
            Map<String, Integer> map = new HashMap<>();
            map.put(bagColor, noBags);
            bagMap.put(value, map);
        }
    }
}
