from typing import List


class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        for i in range(0, len(candidates)):
            if (candidates[i] < target)
