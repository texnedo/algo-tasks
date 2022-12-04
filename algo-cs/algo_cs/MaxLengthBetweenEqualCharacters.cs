using System;
namespace algo_cs
{
    public class MaxLengthBetweenEqualCharactersTask
    {
        public MaxLengthBetweenEqualCharactersTask()
        {
        }

        public static int MaxLengthBetweenEqualCharacters(string s)
        {
            int?[] data = new int?[26];
            int maxDistance = -1;
            for (int i = 0; i < s.Length; i++)
            {
                int index = s[i] - 'a';
                if (data[index] == null)
                {
                    data[index] = i;
                }
                else
                {
                    int distance = i - (int)data[index] - 1;
                    if (distance > maxDistance)
                    {
                        maxDistance = distance;
                    }
                }
            }
            return maxDistance;
        }
    }
}

