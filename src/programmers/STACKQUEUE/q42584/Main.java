package programmers.STACKQUEUE.q42584;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = {1, 2, 3, 2, 3};
        int[] solution1 = solution.solution(prices);
        for (int a : solution1) {
            System.out.println(a);
        }
    }

    static class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];

            for (int i = 0; i < answer.length - 1; i++) {
                answer[i] = calculate(prices, i);
            }

            return answer;
        }

        private int calculate(int[] prices, int start) {
            int period = prices.length - start - 1;

            int value = prices[start];
            for (int i = start; i < prices.length; i++) {
                if(value > prices[i]) {
                    period = i - start;
                    break;
                }
            }

            return period;
        }
    }
}
