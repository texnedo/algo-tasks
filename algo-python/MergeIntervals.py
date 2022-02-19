from typing import List


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        points = []
        for item in intervals:
            points.append((item[0], -1))
            points.append((item[1], 1))
        points.sort(key=lambda x: (x[0], x[1]))
        stack = []
        result = []
        for current in points:
            if len(stack) == 0:
                stack.append(current)
            else:
                last = stack[-1]
                if last[1] == -1 and current[1] == -1:
                    stack.append(current)
                elif last[1] == -1 and current[1] == 1:
                    last = stack.pop()
                    if len(stack) == 0:
                        result.append([last[0], current[0]])
                else:
                    raise Exception()
        return result


if __name__ == '__main__':
    s = Solution()
    print(s.merge([[1,3],[2,6],[8,10],[15,18]]))
    print(s.merge([[1,4],[4,5]]))