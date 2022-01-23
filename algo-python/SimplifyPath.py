class Solution:
    def simplifyPath(self, path: str) -> str:
        tokens = path.split("/")
        stack = []
        result = ''
        for token in tokens:
            if len(token) == 0:
                continue
            elif token == ".":
                continue
            elif token == "..":
                if len(stack) > 0:
                    stack.pop()
            else:
                stack.append(token)
        for item in stack:
            result += "/" + item
        if len(result) == 0:
            result = "/"
        return result


if __name__ == '__main__':
    s = Solution()
    print(s.simplifyPath("/a/b/.././x//d"))