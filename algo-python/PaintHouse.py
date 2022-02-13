import sys
from typing import List, Dict


class Solution:
    def min_cost(self, costs: List[List[int]]) -> int:
        return self.min_cost_internal(costs, 0, -1, dict())

    def min_cost_internal(self, costs: List[List[int]],
                          i: int, prev_j: int, cache: Dict[tuple, int]):
        if i >= len(costs):
            return 0
        option = (i, prev_j)
        if option in cache:
            return cache[option]
        min_cost = None
        for current_j in range(0, len(costs[i])):
            if current_j != prev_j:
                cost = costs[i][current_j] + self.min_cost_internal(costs, i + 1, current_j, cache)
                if min_cost is None or cost < min_cost:
                    min_cost = cost
        cache[option] = min_cost
        return min_cost


