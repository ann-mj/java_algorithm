package programmers.study.q2_2.solution1;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        /* answer 의 마지막 인덱스는 무조건 0 이니까, length -1 까지만 돈다. */
        for (int from = 0; from < answer.length-1; from++) {
            int lastIndex = 0; // 가격이 떨어지는 시점
            for (int to = from; to < answer.length; to++) {
                lastIndex = to;

                /* 시작부분(From)보다 가격이 떨어지는 시점이면 */
                if (prices[to] < prices[from]) {
                    break;
                }
            }
            answer[from] = Math.max(1, lastIndex - from);
        }

        return answer;
    }
}