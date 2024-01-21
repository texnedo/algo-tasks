using System;
namespace algo_cs
{
    public class MaximumScoreFromRemovingSubstrings
    {
        private const string X_TOKEN = "ab";
        private const string Y_TOKEN = "ba";

        public MaximumScoreFromRemovingSubstrings()
        {
        }

        public static int MaximumGain(string s, int x, int y)
        {
            int sumGain = 0;
            int lastBreakIndex = 0;
            var cache = new Dictionary<String, int?>();
            for (int i = 0; i < s.Length; i++)
            {
                char current = s[i];
                if (current != 'a' && current != 'b')
                {
                    int startIndex = lastBreakIndex; 
                    lastBreakIndex = i;
                    var token = s.Substring(startIndex, lastBreakIndex - startIndex);
                    System.Console.WriteLine("Current token: {0}", token);
                    sumGain += MaximumGainInternal(token, x, y, cache);
                    lastBreakIndex++;
                }
            }
            var lastToken = s.Substring(lastBreakIndex);
            System.Console.WriteLine("Current token: {0}", lastToken);
            sumGain += MaximumGainInternal(lastToken, x, y, cache);
            return sumGain;
        }

        private static int MaximumGainInternal(string s, int x, int y, Dictionary<String,int?> cache)
        {
            if (s == null || s.Length == 0)
            {
                return 0;
            }
            int? existing = cache.GetValueOrDefault(s, null);
            if (existing != null)
            {
                System.Console.WriteLine("Gain (cached) for string {0} is {1}", s, existing);
                return (int)existing;
            }
            int xindex = s.IndexOf(X_TOKEN);
            int gainAfterXRemoval = 0;
            int yindex = s.IndexOf(Y_TOKEN);
            int gainAfterYRemoval = 0;
            //TODO: optimize memory with reusing the source string buffer
            if (xindex >= 0)
            {
                string sWithoutX = s.Substring(0, xindex) + s.Substring(xindex + X_TOKEN.Length);
                gainAfterXRemoval = x + MaximumGainInternal(sWithoutX, x, y, cache);
            }
            if (yindex >= 0)
            {
                string sWithoutY = s.Substring(0, yindex) + s.Substring(yindex + Y_TOKEN.Length);
                gainAfterYRemoval = y + MaximumGainInternal(sWithoutY, x, y, cache);
            }
            int maxGain = Math.Max(gainAfterXRemoval, gainAfterYRemoval);
            cache[s] = maxGain;
            System.Console.WriteLine("Gain for string {0} is {1}", s, maxGain);
            return maxGain;
        }
    }
}

