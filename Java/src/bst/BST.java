package bst;

import common.Array;
import queue.ArrayQueue;
import queue.Queue;
import stack.ArrayStack;
import stack.Stack;

public class BST<E extends Comparable> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 插入元素
     * @param e 新插入的元素
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根2的二分搜索树插入元素e，递归算法
     * @param node
     * @param e
     * @return 插入新节点后二分搜索树的根
     */
    private Node add(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else {
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 看二分搜索树中是否包含元素e
     * @param e
     * @return true-包含 false-不包含
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null)
            return false;

        if(e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 二分搜索树前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 二分搜索树的非递归前序遍历
     */
    public void preOrderNR() {
        Stack<Node> stack = new ArrayStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二分搜索树中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);;
    }

    /**
     * 二分搜索树中序遍历非递归实现
     */
    public void inOrderNR() {
        Stack<Node> stack = new ArrayStack<>();
        Array<E> visitedNodes = new Array<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.peek();
            if (!visitedNodes.contains(cur.e)) {
                if (cur.left == null) {
                    System.out.println(cur.e);
                    visitedNodes.addLast(cur.e);
                    stack.pop();
                    if (cur.right != null) {
                        stack.push(cur.right);
                    }
                } else {
                    if (!visitedNodes.contains(cur.left.e)) {
                        stack.push(cur.left);
                    } else {
                        System.out.println(cur.e);
                        visitedNodes.addLast(cur.e);
                        stack.pop();
                        if (cur.right != null) {
                            stack.push(cur.right);
                        }
                    }
                }
            } else {
                stack.pop();
            }
        }
    }

    /**
     * 二分搜索树后续遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        Queue<Node> q = new ArrayQueue<>();
        q.enqueue(root);
        while(!q.isEmpty()) {
            Node cur = q.dequeue();
            System.out.println(cur.e);

            if (cur.left != null) {
                q.enqueue(cur.left);
            }
            if (cur.right != null) {
                q.enqueue(cur.right);
            }
        }
    }

    /**
     * 寻找二分搜索树中最小的元素
     * @return 最小元素
     */
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");

        return minimum(root).e;
    }

    private Node  minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    /**
     * 查找最大值
     * @return 最大值
     */
    public E maxmum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return maxmum(root).e;
    }

    private Node maxmum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maxmum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值
     * @return 最小值
     */
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除最大值
     * @return 最大值
     */
    public E removeMax() {
        E ret = maxmum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            // 待删除节点左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 待删除节点右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空
            // 找到毕待删除节点大的最小节点，及待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;

            return successor;
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);

        return res.toString();
    }

    /**
     * 生成以node为根节点，深度为depth的描述二叉树的字符串, 基于前序遍历
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = { 5, 3, 6, 8, 4, 2};
        for (int num: nums) {
            bst.add(num);
        }
//        bst.preOrder();
//        System.out.println();
//        bst.preOrderNR();
//        System.out.println();
//        bst.inOrder();
//        System.out.println();
//        bst.inOrderNR();
//        System.out.println();
//        bst.postOrder();
//        System.out.println();
        bst.levelOrder();
        System.out.println();
    }
}
