from typing import List


class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        index1 = 0
        index2 = 0
        result_length = m + n
        while index2 < n and index1 < result_length:
            if index1 == m:
                nums1[index1] = nums2[index2]
                index1 = index1 + 1
                index2 = index2 + 1
                m = m + 1
            elif nums1[index1] < nums2[index2]:
                index1 = index1 + 1
            elif nums1[index1] >= nums2[index2]:
                self.move_right(nums1, m, index1)
                nums1[index1] = nums2[index2]
                index1 = index1 + 1
                index2 = index2 + 1
                m = m + 1

    def move_right(self, nums1: List[int], m: int, index: int):
        for i in range(m, index, -1):
            nums1[i] = nums1[i - 1]


def main():
    s = Solution()
    nums1 = [1, 2, 3, 0, 0, 0]
    s.merge(nums1=nums1, m=3, nums2=[2, 5, 6], n=3)
    print(nums1)


if __name__ == '__main__':
    main()
