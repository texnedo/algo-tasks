class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def parse_tree(data: list) -> TreeNode:
    pass

def build(data: list, index: int, parent: TreeNode) -> TreeNode:
    pass

def get_left_child():
    pass

def get_right_child():
    pass

# private static TreeNode build(Integer[] data, Integer index, TreeNode parent) {
# if (index == null || data[index] == null) {
# return null;
# }
# TreeNode node = new TreeNode();
# node.val = data[index];
# node.parent = parent;
# node.left = build(data, getLeftChild(data, index), node);
# node.right = build(data, getRightChild(data, index), node);
# return node;
# }
#
# public static Integer getLeftChild(Integer[] data, int index) {
# int left = index * 2 + 1;
# if (left >= data.length || left < 0) {
# return null;
# }
# return left;
# }
#
# public static Integer getRightChild(Integer[] data, int index) {
# int right = index * 2 + 2;
# if (right >= data.length || right < 0) {
# return null;
# }
# return right;
# }