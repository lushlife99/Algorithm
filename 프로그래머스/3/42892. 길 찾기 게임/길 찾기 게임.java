/*
특정 노드의 left right -> 부모와 노드의 x좌표의 대소로 결정
부모를 찾는 방법 -> 부모의 후보(y값이 높은 노드들)
*/

import java.util.*;

class Solution {
    public List<List<Integer>> solution(int[][] nodeinfoArr) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Node> tree = new ArrayList<>();
        
        for (int i = 0; i < nodeinfoArr.length; i++) {
            int[] nodeinfo = nodeinfoArr[i];
            Node node = new Node(i+1, nodeinfo[0], nodeinfo[1]);
            tree.add(node);
        }
        
        Node root = initTree(tree);
        answer.add(getPreOrder(root));
        answer.add(getPostOrder(root));

        return answer;
    }
    
    public List<Integer> getPostOrder(Node node) {
        List<Integer> arr = new ArrayList<>();
        if (node.left != null) {
            arr.addAll(getPostOrder(node.left));
        }
        
        if (node.right != null) {
            arr.addAll(getPostOrder(node.right));
        }
        
        arr.add(node.id);
        return arr;
    }
    
    public List<Integer> getPreOrder(Node node) {
        List<Integer> arr = new ArrayList<>();
        arr.add(node.id);
        
        if (node.left != null) {
            arr.addAll(getPreOrder(node.left));
        }
        
        if (node.right != null) {
            arr.addAll(getPreOrder(node.right));
        }
        
        return arr;
    }
    
    public Node initTree(List<Node> nodes) {
        
        Collections.sort(nodes, (n1, n2) -> {
            if (n1.y == n2.y) return n1.x - n2.x;
            return n2.y - n1.y;
        });
        
        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            Node current = nodes.get(i);
            Node parent = root;
            while (parent != null) {
                if (current.x < parent.x) {
                    if (parent.left == null) {
                        parent.left = current;
                        break;
                    } 
                    
                    parent = parent.left;
                }
                else {
                    if (parent.right == null) {
                        parent.right = current;
                        break;
                    }
                    
                    parent = parent.right;
                }
            }
        }
        return root;
    }
    
    class Node {
        int id;
        int x;
        int y;
        Node left;
        Node right;
        
        public Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "id: " + this.id + " x: " + this.x + " y: " + this.y + "\n";
        }
    }
}