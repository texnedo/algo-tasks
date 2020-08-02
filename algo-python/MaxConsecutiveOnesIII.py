from typing import List


def main():
    print(longestOnes([1,1,1,0,0,0,1,1,1,1,0], 2))
    print(longestOnes([1,1,1,0,0,0,1,1,1,1,0], 1))
    print(longestOnes([0], 1))
    print(longestOnes([0], 0))
    print(longestOnes([1,1,1,0,0,0,1,1,1,1,0], 4))
    print(longestOnes([1,1,1,0,0,0,1,1,1,1,0], 3))


def longestOnesSimple(A: List[int]) -> int:
    max_length = 0
    current_length = 0
    for i in range(0, len(A)):
        if A[i] == 1:
            current_length = current_length + 1
        else:
            if current_length > max_length:
                max_length = current_length
            current_length = 0
    if current_length > max_length:
        max_length = current_length
    return max_length


def longestOnes(A: List[int], K: int) -> int:
    max_length = 0
    window_start = 0
    zero_positions = list()
    for i in range(0, len(A)):
        if A[i] == 1:
            continue
        else:
            if A[i] != 0:
                raise Exception()
            zero_positions.append(i)
            if len(zero_positions) > K:
                windows_length = i - window_start
                print("Window size = {}, [{}, {}]".format(windows_length, window_start, i - 1))
                if windows_length > max_length:
                    max_length = windows_length
                first_zero_position = zero_positions.pop(0)
                window_start = first_zero_position + 1
    windows_length = len(A) - window_start
    if windows_length != 0:
        print("Window size = {}, [{}, {}]".format(windows_length, window_start, len(A) - 1))
    if windows_length > max_length:
        max_length = windows_length
    return max_length


if __name__ == '__main__':
    main()
