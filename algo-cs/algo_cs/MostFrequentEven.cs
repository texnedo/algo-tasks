using System;
using System.Collections;

namespace algo_cs
{
    public class MostFrequentEvenTask
    {
        public MostFrequentEvenTask()
        {
        }

        public static int MostFrequentEven(int[] nums)
        {
            if (nums == null)
            {
                throw new ArgumentException();
            }
            if (nums.Length == 0)
            {
                return -1;
            }
            var dict = new Dictionary<Int32, Int32>();
            for (int i = 0; i < nums.Length; i++)
            {
                int value = nums[i];
                if (value % 2 == 0)
                {
                    if (dict.ContainsKey(value))
                    {
                        dict[value]++;
                    }
                    else
                    {
                        dict[value] = 1;
                    }
                }
            }
            if (dict.Count == 0)
            {
                return -1;
            }
            var result = new List<KeyValuePair<Int32, Int32>>();
            foreach (var item in dict)
            {
                result.Add(item);
            }
            result.Sort((obj1, obj2) =>
            {
                int result = -obj1.Value.CompareTo(obj2.Value);
                if (result == 0)
                {
                    return obj1.Key.CompareTo(obj2.Key);
                }
                return result;
            });
            return result[0].Key;
        }
    }
}

