import sys
from typing import List


class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        cache = [[None for x in range(len(triangle[len(triangle) - 1]))] for y in range(len(triangle))]
        return self.minimumTotalInternal(triangle, 0, 0, cache)

    def minimumTotalInternal(self, triangle: List[List[int]], level: int, index: int, cache: list):
        print("{}_{}".format(level, index))
        if len(triangle) == level or \
                index >= len(triangle[level]) or \
                index < 0:
            return None
        if len(triangle) == level - 1:
            return triangle[level][index]
        if cache[level][index] is not None:
            return cache[level][index]
        left = self.minimumTotalInternal(triangle, level + 1, index, cache)
        right = self.minimumTotalInternal(triangle, level + 1, index + 1, cache)
        if left is None and right is None:
            result = triangle[level][index]
        elif left is None:
            result = triangle[level][index] + right
        elif right is None:
            result = triangle[level][index] + left
        else:
            result = triangle[level][index] + min(right, left)
        cache[level][index] = result
        return result


if __name__ == '__main__':
    s = Solution()
    data = [[2], [3, 4], [6, 5, 7], [4, 1, 8, 3]]
    print(s.minimumTotal(data))
