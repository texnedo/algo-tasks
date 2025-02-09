# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def goodNodes(self, root: TreeNode) -> int:
        if root is None:
            return 0
        return self.good_nodes_internal(root, root.val)

    def good_nodes_internal(self, root: TreeNode, max_path_value: int) -> int:
        left_good_nodes = 0
        right_good_nodes = 0
        if root.left is not None:
            left_good_nodes = self.good_nodes_internal(root.left, max(max_path_value, root.val))
        if root.right is not None:
            right_good_nodes = self.good_nodes_internal(root.right, max(max_path_value, root.val))
        return left_good_nodes + right_good_nodes + (1 if root.val >= max_path_value else 0)


if __name__ == '__main__':
    root = TreeNode(3, TreeNode(1, TreeNode(3)), TreeNode(4, TreeNode(1), TreeNode(5)))
    s = Solution()
    print(s.goodNodes(root))
