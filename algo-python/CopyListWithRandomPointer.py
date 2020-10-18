from typing import Dict


class Node:
    def __str__(self) -> str:
        return "[value: {}, next: {}, random:{}]"\
            .format(self.val,
                    self.next.val if self.next is not None else None,
                    self.random.val if self.random is not None else None)

    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random


class Solution:
    def copyRandomList(self, head: 'Node') -> 'Node':
        if head is None:
            raise Exception()
        existing: Dict[Node, Node] = dict()
        clone_head = None
        it = head
        while it is not None:
            clone_next = None
            clone_random = None
            if it.next is not None:
                if it.next in existing:
                    clone_next = existing[it.next]
                else:
                    clone_next = Node(it.next.val, None, None)
                    existing[it.next] = clone_next
            if it.random is not None:
                if it.random in existing:
                    clone_random = existing[it.random]
                else:
                    clone_random = Node(it.random.val, None, None)
                    existing[it.random] = clone_random
            if it in existing:
                clone_node = existing[it]
                clone_node.next = clone_next
                clone_node.random = clone_random
            else:
                clone_node = Node(it.val, clone_next, clone_random)
                existing[it] = clone_node
            if clone_head is None:
                clone_head = clone_node
            it = it.next
        return clone_head


def print_list(head: Node):
    it = head
    while it is not None:
        print(it)
        it = it.next


def main():
    s = Solution()
    # l3 = Node(3, None, None)
    # l2 = Node(2, l3, None)
    # l1 = Node(1, l2, None)
    # l0 = Node(0, l1, None)
    # l0.random = l1
    # l1.random = l3
    # l3.random = l1
    # print_list(l0)
    # print()
    # l0_clone = s.copyRandomList(l0)
    # print_list(l0_clone)
    l3 = Node(2, None, None)
    l2 = Node(2, l3, None)
    l1 = Node(1, l2, None)
    l0 = Node(1, l1, None)
    l0.random = l1
    l1.random = l3
    l3.random = l1
    print_list(l0)
    print()
    l0_clone = s.copyRandomList(l0)
    print_list(l0_clone)


if __name__ == '__main__':
    main()

