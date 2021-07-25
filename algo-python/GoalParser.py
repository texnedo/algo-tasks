class Solution:
    def interpret(self, command: str) -> str:
        result = ''
        i = 0
        while i < len(command):
            if command[i] == 'G':
                result += 'G'
                i = i + 1
            elif command[i] == '(':
                if i == len(command) - 1:
                    raise Exception("Illegal sequence in the command: '{}'".format(command))
                elif command[i + 1] == 'a':
                    if command[i:i+4] != '(al)':
                        raise Exception("Illegal sequence in the command: '{}', token: '{}'"
                                        .format(command, command[i:4]))
                    result += 'al'
                    i = i + 4
                elif command[i + 1] == ')':
                    result += 'o'
                    i = i + 2
                else:
                    raise Exception("Illegal sequence in the command: '{}'".format(command))
        return result


def main():
    s = Solution()
    print(s.interpret("G()(al)"))


if __name__ == '__main__':
    main()