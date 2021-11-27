class Solution:
    def longestPalindrome(self, s: str) -> str:
        last_palindrome = ""
        cache = [[False for x in range(len(s))] for y in range(len(s))]
        for i in range(0, len(s)):
            cache[i][i] = True
            if len(last_palindrome) == 0:
                last_palindrome = s[i]
            if i < len(s) - 1 and s[i] == s[i + 1]:
                cache[i][i + 1] = True
                last_palindrome = s[i:i + 2]
        for i in range(0, len(s)):
            for j in range(i, len(s)):
                if i < len(s) - 1 and \
                        j > 1 and \
                        cache[i + 1][j - 1] and \
                        s[i] == s[j]:
                    last_length = j - i + 1
                    if last_length > len(last_palindrome):
                        last_palindrome = s[i:j + 1]
                    cache[i + 1][j - 1] = True
        return last_palindrome
    

def is_palindrome(s: str, i: int, j: int):
    length = j - i + 1
    if length <= 1:
        return True
    for index in range(0, int(length / 2)):
        if s[i + index] != s[j - index]:
            return False
    return True


def main():
    s = Solution()
    print(s.longestPalindrome("ccaadacc"))
    print(s.longestPalindrome("ccc"))
    print(s.longestPalindrome("bb"))
    print(s.longestPalindrome("a"))
    print(s.longestPalindrome("abcba"))
    print(s.longestPalindrome("cbbd"))
    print(s.longestPalindrome("babad"))


if __name__ == '__main__':
    main()
