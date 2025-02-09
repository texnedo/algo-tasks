"""
// arr = [0:',', 1:'my', 2:'world!', '3:Hello', 4:'beautiful']
// perm = [3,0,1,4,2]
// apply_reorder(arr, perm)
need to get this:
// print(arr) # ['Hello', ',', 'my', 'beautiful', 'world!']
do that inplace
"""

import random


def find_element_with_index(arr, index):
    current_index = index
    while 1:
        item = arr[current_index]
        item_index = int(item.split(":")[0])
        if item_index == index:
            return current_index
        current_index = item_index


def apply_reorder(arr, perm):
    for i in range(0, len(perm)):
        if perm[i] == i:
            continue
        else:
            index = find_element_with_index(arr, perm[i])
            tmp = arr[i]
            arr[i] = arr[index]
            arr[index] = tmp


def generate_test_case(size):
    arr = [f"{i}:value{i}" for i in range(size)]
    perm = list(range(size))
    random.shuffle(perm)
    return arr, perm


def main():
    arr = ['0:,', '1:my', '2:world!', '3:Hello', '4:beautiful']
    perm = [3, 0, 1, 4, 2]
    apply_reorder(arr, perm)
    print(arr)

    arr, perm = generate_test_case(100)
    print("Original array:", arr)
    print("Permutation:", perm)

    apply_reorder(arr, perm)
    print("Reordered array:", arr)


if __name__ == '__main__':
    main()