from typing import List, Dict
import time
import json


# TODO - fix this solution
class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        uniq_counts = {}
        cached_earns = {}
        max_earned = 0
        for i in nums:
            if i in uniq_counts:
                uniq_counts[i] = uniq_counts[i] + 1
            else:
                uniq_counts[i] = 1
        for i in uniq_counts:
            earned = self.deleteAndEarnInternal(cached_earns, uniq_counts, i)
            if earned > max_earned:
                max_earned = earned
        return max_earned

    def deleteAndEarnInternal(self,
                              cached_earns: Dict[str, int],
                              uniq_counts: Dict[int, int],
                              key_to_delete: int) -> int:
        if key_to_delete not in uniq_counts:
            return 0
        uniq_counts_key = json.dumps(uniq_counts)
        if uniq_counts_key in cached_earns:
            return cached_earns[uniq_counts_key]
        uniq_counts_local = dict(uniq_counts)
        count = uniq_counts_local.pop(key_to_delete)
        current_earned = count * key_to_delete
        lower_to_delete = key_to_delete - 1
        upper_to_delete = key_to_delete + 1
        uniq_counts_local.pop(lower_to_delete, None)
        uniq_counts_local.pop(upper_to_delete, None)
        max_earned = 0
        for i in uniq_counts_local:
            earned = self.deleteAndEarnInternal(cached_earns, uniq_counts_local, i)
            if earned > max_earned:
                max_earned = earned
        cached_earns[json.dumps(uniq_counts_local)] = max_earned
        total_earned = current_earned + max_earned
        print("{} - {} - {}".format(key_to_delete, uniq_counts_local, max_earned))
        return total_earned


def main():
    s = Solution()
    nums = [3, 4, 2]
    start = time.monotonic_ns()
    print(s.deleteAndEarn(nums))
    end = time.monotonic_ns()
    print(end - start)

    nums1 = [2, 2, 3, 3, 3, 4]
    start = time.monotonic_ns()
    print(s.deleteAndEarn(nums1))
    end = time.monotonic_ns()
    print(end - start)

    nums2 = [8, 3, 4, 7, 6, 6, 9, 2, 5, 8, 2, 4, 9, 5, 9, 1, 5, 7, 1, 4]
    start = time.monotonic_ns()
    print(s.deleteAndEarn(nums2))
    end = time.monotonic_ns()
    print(end - start)

    nums3 = [12, 32, 93, 17, 100, 72, 40, 71, 37, 92, 58, 34, 29, 78, 11, 84, 77, 90, 92, 35, 12, 5, 27, 92, 91, 23, 65,
             91, 85, 14, 42, 28, 80, 85, 38, 71, 62, 82, 66, 3, 33, 33, 55, 60, 48, 78, 63, 11, 20, 51, 78, 42, 37, 21,
             100, 13, 60, 57, 91, 53, 49, 15, 45, 19, 51, 2, 96, 22, 32, 2, 46, 62, 58, 11, 29, 6, 74, 38, 70, 97, 4,
             22, 76, 19, 1, 90, 63, 55, 64, 44, 90, 51, 36, 16, 65, 95, 64, 59, 53, 93]
    print(s.deleteAndEarn(nums3))


if __name__ == '__main__':
    main()
