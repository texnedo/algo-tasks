from bisect import bisect_left
from typing import List


class Solution:
    def arraysIntersection(self, arr1: List[int], arr2: List[int], arr3: List[int]) -> List[int]:
        data = [arr1, arr2, arr3]
        result = []
        data.sort(key=lambda x: len(x))
        for item in data[0]:
            index1 = bisect_left(data[1], item)
            if index1 == len(data[1]) or item != data[1][index1]:
                index1 = -1
            index2 = bisect_left(data[2], item)
            if index2 == len(data[2]) or item != data[2][index2]:
                index2 = -1
            if index1 >= 0 and index2 >= 0:
                result.append(item)
        return result


if __name__ == '__main__':
    s = Solution()
    print(s.arraysIntersection([1,2,3,4,5],
                               [1,2,5,7,9],
                               [1,3,4,5,8]))