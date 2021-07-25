class Solution:
    def removeDuplicateLettersNoOrder(self, s: str) -> str:
        alpha = [0] * (ord('z') - ord('a') + 1)
        result = ''
        for item in s:
            index = ord(item) - ord('a')
            alpha[index] = alpha[index] + 1
        for i in range(0, len(s)):
            if alpha[i] != 0:
                result += chr(ord('a') + i)
        return result


        # c b a c d c b c
        #
        # 0 1 2 3 4 5 6 7
        #
        # с = {0, 3, 5, 7}
        #
        # b = {1, 6}
        #
        # a = {2}
        #
        # d = {4}
        #
        # c b
    def removeDuplicateLetters(self, s: str) -> str:
            size = ord('z') - ord('a') + 1
            count_letters = [0] * size
            is_final = [False] * size
            sequence = list()
            result = ''
            for item in s:
                index = ord(item) - ord('a')
                count_letters[index] += 1
            for i in range(0, len(s)):
                index = ord(s[i]) - ord('a')
                if len(sequence) == 0:
                    sequence.append(s[i])
                    count_letters[index] -= 1
                else:
                    if not is_final[index]:
                        while len(sequence) > 0 and \
                                sequence[-1] >= s[i] and \
                                count_letters[ord(sequence[-1]) - ord('a')] > 0:
                            count_letters[ord(sequence[-1]) - ord('a')] -= 1
                            is_final[ord(sequence[-1]) - ord('a')] = False
                            sequence.pop()
                        sequence.append(s[i])
                        is_final[index] = True
                    count_letters[index] -= 1
            for item in sequence:
                result += item
            return result


def main():
    s = Solution()
    print(s.removeDuplicateLetters("cbacdcbc"))
    print(s.removeDuplicateLetters("bcabc"))


if __name__ == '__main__':
    main()
