package programmers.study.q4_7.solution2;

import java.util.PriorityQueue;

class Solution {
    class Node implements Comparable{
        private long currentTime = 0; // 누적 시간
        private long time = 0; // 현재 시간

        public Node(long currentTime, long time) {
            this.currentTime = currentTime;
            this.time = time;
        }

        public void addTime() {
            this.currentTime += time;
        }


        @Override
        public int compareTo(Object o) {
            Node node = (Node) o;
            return (int) ((int) this.currentTime - node.currentTime);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] t = {7,8,9,10};
        System.out.println(solution.solution(5,t));
    }
    public long solution(int n, int[] times) {
        long answer = 0;
        long[] result = new long[n+1];
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
