using algo_cs;

class Program
{
    static void Main(string[] args)
    {
        //Console.WriteLine("Test");
        //System.Console.WriteLine(MaxLengthBetweenEqualCharactersTask.MaxLengthBetweenEqualCharacters("acdfgca"));
        //System.Console.WriteLine(IntegerBreakTask.IntegerBreak(3));
        //System.Console.WriteLine(IntegerBreakTask.IntegerBreak(2));
        //System.Console.WriteLine(IntegerBreakTask.IntegerBreak(10));
        //System.Console.WriteLine(IntegerBreakTask.IntegerBreak(8));
        //System.Console.WriteLine(MostFrequentEvenTask.MostFrequentEven(new int[] { 29, 47, 21, 41, 13, 37, 25, 7 }));
        //System.Console.WriteLine(MostFrequentEvenTask.MostFrequentEven(new int[] { 1, 2, 2, 4, 4, 5 }));
        System.Console.WriteLine(string.Join(",", CheckIfPrerequisiteTask.CheckIfPrerequisite(
            3,
            new int[,] { { 1, 2 }, { 1, 0 }, { 2, 0 } },
            new int[,] { { 1, 0 }, { 1, 2 }}
            )));
    }
}