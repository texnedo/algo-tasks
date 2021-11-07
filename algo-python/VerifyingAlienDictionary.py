from typing import List


class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        letter_mapping = {}
        for i in range(0, len(order)):
            letter_mapping[order[i]] = i
        for i in range(1, len(words)):
            if not check_valid_order(words[i - 1], words[i], letter_mapping):
                return False
        return True


def check_valid_order(w1: str, w2: str, mapping: dict):
    min_length = min(len(w1), len(w2))
    for i in range(0, min_length):
        v1 = mapping[w1[i]]
        v2 = mapping[w2[i]]
        if v2 > v1:
            return True
        elif v1 == v2:
            continue
        else:
            return False
    return len(w2) >= len(w1)


def main():
    s = Solution()
    print(s.isAlienSorted(["hello","leetcode"], "hlabcdefgijkmnopqrstuvwxyz"))
    print(s.isAlienSorted(["apple","app"], "abcdefghijklmnopqrstuvwxyz"))


if __name__ == '__main__':
    main()