package programmers.study.q2_7.solution1;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static void main(String[] args) {
        Solution a = new Solution();
        String[] b = {"119", "97674223", "1195524421"};
        System.out.println(a.solution(b));
    }
    class Node {
        String value;

        public Node(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj==null) return false;
            Node str = (Node) obj;
            if (value.length() > str.value.length()) {
                return value.startsWith(str.value);
            }
            return str.value.startsWith(value);
        }
    }

    public boolean solution(String[] phone_book) {
        Set<Node> nodes = new HashSet<>();
        for (String a : phone_book) {
            nodes.add(new Node(a));
        }
        return nodes.size() == phone_book.length;
    }
}