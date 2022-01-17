package programmers.study.q3_1.solution1;

import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compareTo);

        for (int a : scoville) {
            pq.add(a);
        }

        while (notOverK(pq, K) && (pq.size() >= 2)) {
            int first = pq.remove();
            int second = pq.remove();
            pq.add(first + (second * 2));
            answer++;
        }

        if(isLastOneNotOverK(pq, K)) answer = -1;
        return answer;
    }

    /* Q의 제일 작은 원소가 K 이하이면 true , 아니면 false */
    private boolean notOverK(PriorityQueue<Integer> Q, int k) {
        return Q.peek() < k;
    }

    /* Q에 마지막 남은 한 개가 k 이하이면 true , 아니면 false */
    private boolean isLastOneNotOverK(PriorityQueue<Integer> Q, int k) {
        return ((Q.size() == 1) && (Q.peek() < k));
    }

}