from typing import List, Set


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        #return self.word_break_internal(s, set(wordDict), 0, 0)
        result = self.word_break_internal_v2(s, set(wordDict))
        return [" ".join(words) for words in result]

    def word_break_internal(self, s: str, words: Set[str], offset, index) -> List[str]:
        sequence = list()
        if index >= len(s):
            return sequence
        result = s[0:offset]
        success = True
        for i in range(index, len(s)):
            token = s[offset:i + 1]
            if token not in words:
                success = False
                continue
            if len(result) != 0:
                result += " "
            result += token
            sequence.extend(self.word_break_internal(s, words, offset, i + 1))
            offset = i + 1
            success = True
        if success:
            sequence.append(result)
        return sequence

    def word_break_internal_v2(self, s: str, words: Set[str]):
        if len(s) == 0:
            return [[]]
        sequence = []
        for i in range(0, len(s)):
            token = s[0:i + 1]
            if token not in words:
                continue
            second_part = self.word_break_internal_v2(s[i + 1:], words)
            for item in second_part:
                sequence.append([token] + item)
        return sequence


def main():
    s = Solution()
    print(s.wordBreak("catsanddog", ["cat","cats","and","sand","dog"]))
    print(s.wordBreak("pineapplepenapple", ["apple","pen","applepen","pine","pineapple"]))


if __name__ == '__main__':
    main()
