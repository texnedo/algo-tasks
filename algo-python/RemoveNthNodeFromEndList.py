from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        if head is None or n < 0:
            return None
        if n == 0:
            return head
        iter = head
        indexes = dict()
        index = 0
        while iter is not None:
            indexes[index] = iter
            index += 1
            iter = iter.next
        index_to_remove = index - n
        node_to_remove = indexes[index_to_remove]
        if node_to_remove is None:
            return None
        if node_to_remove == head:
            return node_to_remove.next
        previous_node = indexes[index_to_remove - 1]
        if previous_node is None:
            return None
        previous_node.next = node_to_remove.next
        return head
