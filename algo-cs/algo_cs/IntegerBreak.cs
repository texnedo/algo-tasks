using System;
using System.Text;

namespace algo_cs
{
    public class IntegerBreakTask
    {
        public IntegerBreakTask()
        {
        }

        public static int IntegerBreak(int n)
        {
            if (n == 2)
            {
                return 1;
            }
            if (n == 3)
            {
                return 2;
            }
            int count = n / 3;
            int rest = n % 3;
            int product = (int)Math.Pow(3, count);
            if (rest != 0)
            {
                int productWithMultRest = product * rest;
                int productWithSumRest = (product / 3) * (3 + rest);
                product = Math.Max(productWithMultRest, productWithSumRest);
            }
            return product;
        }

        public static int IntegerBreakBad(int n)
        {
            int maxProduct = 1;
            for (int i = 2; i <= n; i++)
            {
                int round = n / i;
                int rest = n % i;
                int product = (int)Math.Pow(round, i);
                if (rest != 0)
                {
                    int productWithMultRest = product * rest;
                    int productWithSumRest = (product / round) * (round + rest);
                    product = Math.Max(productWithMultRest, productWithSumRest);
                }
                if (product > maxProduct)
                {
                    maxProduct = product;
                }
            }
            return maxProduct;
        }
    }
}

