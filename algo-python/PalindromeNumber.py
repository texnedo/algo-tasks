class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0:
            return False
        if x != 0 and x % 10 == 0:
            return False
        number = x
        reverted_number = 0
        while number > 0:
            last_digit = number % 10
            reverted_number = reverted_number * 10 + last_digit
            number //= 10
        return reverted_number == x


def main():
    s = Solution()
    print(s.isPalindrome(121))


if __name__ == '__main__':
    main()
