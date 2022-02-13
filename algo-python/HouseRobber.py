from typing import List, Set, Dict


class Solution:
    def rob(self, nums: List[int]) -> int:
        return self.rob_simple(0, nums, dict())

    def rob_simple(self, index: int, nums: List[int], cache: Dict[int, int]) -> int:
        if index in cache:
            return cache[index]
        if index >= len(nums):
            return 0
        rob_current = nums[index] + self.rob_simple(index + 2, nums, cache)
        not_rob_current = self.rob_simple(index + 1, nums, cache)
        value = max(rob_current, not_rob_current)
        cache[index] = value
        return value

    def rob_internal(self, nums: List[int], visited: Set[int], cache: Dict[tuple, int]):
        visited_hash = tuple(visited)
        if visited_hash in cache:
            return cache[visited_hash]
        if len(visited) == len(nums):
            return 0
        if len(nums) - len(visited) == 1:
            for i in range(0, len(nums)):
                if i not in visited:
                    return nums[i]
        if len(nums) - len(visited) == 2:
            options = []
            for i in range(0, len(nums)):
                if i not in visited:
                    options.append(nums[i])
            return max(options[0], options[1])
        max_value = -1
        for i in range(0, len(nums)):
            if i not in visited:
                options = set()
                options.add(i)
                if i > 0 and i - 1 not in visited:
                    options.add(i - 1)
                if i < len(nums) - 1 and i + 1 not in visited:
                    options.add(i + 1)
                visited.update(options)
                value = nums[i] + self.rob_internal(nums, visited, cache)
                if value > max_value:
                    max_value = value
                visited.difference_update(options)
        # cache[visited_hash] = max_value
        return max_value


if __name__ == '__main__':
    s = Solution()
    print(s.rob([183,219,57,193,94,233,202,154,65,240,97,234,100,249,186,66,90,238,168,128,177,235,50,81,185,165,217,207,88,80,112,78,135,62,228,247,211]))
    print(s.rob([2,7,9,3,1]))
    print(s.rob([1, 3, 1]))

