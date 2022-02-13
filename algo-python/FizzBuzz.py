from typing import List


class Solution:
    def fizzBuzz(self, n: int) -> List[str]:
        result = []
        for i in range(1, n + 1):
            toAppend = None
            if i % 3 == 0 and i % 5 == 0:
                toAppend = "FizzBuzz"
            elif i % 3 == 0:
                toAppend = "Fizz"
            elif i % 5 == 0:
                toAppend = "Buzz"
            else:
                toAppend = str(i)
            result.append(toAppend)
        return result