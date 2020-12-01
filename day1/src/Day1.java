import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day1
{
	public static void main(String[] args) {

		try
		{
			List<String> result = Files.readAllLines(Paths.get("C:/Users/HOBA/source/repos/advent-of-code-2020/day1/src/input"));
			List<Integer> integers = result.stream().map(Integer::parseUnsignedInt).collect(Collectors.toList());
			for (Integer i : integers)
			{
				for (Integer j : integers)
				{
				    if (i + j == 2020)
					{
						System.out.println("Sum of two found. Answer is " + i * j);
					}

					for (Integer k : integers)
					{
						if (i + j + k == 2020)
						{
							System.out.println("Sum of three found. Answer is " + i * j * k);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
