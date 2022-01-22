package programmers.study.q4_7.solution1;

import java.util.PriorityQueue;

class Solution {
    class Node implements Comparable{
        private int currentTime = 0; // 누적 시간
        private int time = 0; // 현재 시간

        public Node(int currentTime, int time) {
            this.currentTime = currentTime;
            this.time = time;
        }

        public void setCurrentTime(int currentTime) {
            this.currentTime = currentTime;
        }

        public void addTime() {
            this.currentTime += time;
        }


        @Override
        public int compareTo(Object o) {
            Node node = (Node) o;
            return this.currentTime - node.currentTime;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] t = {7,7,7};
        System.out.println(solution.solution(3,t));
    }
    public long solution(int n, int[] times) {
        long answer = 0;
        int[] result = new int[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < times.length; i++) {
            pq.offer(new Node(times[i], times[i]));
        }

        for (int i = 1; i <= n; i++) {
            Node node = pq.poll();
            result[i] = node.currentTime;
            node.addTime();
            pq.offer(node);
        }
        answer = result[n];
        return answer;
    }
}
