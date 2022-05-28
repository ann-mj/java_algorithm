package programmers.BINARYSEARCH.q43238;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution so = new Solution();
        int n = 6;
        int[] times = {7, 14};
        System.out.println(so.solution(n, times));
    }

    static class Solution {
        public long solution(int n, int[] times) {
            long answer = 0;

            long left = 1;
            long right = findMax(n, times);  // 가장 오래 걸리는 심사대 * n 일 때가 가장 오래 걸림.

            while (left <= right) {
                long finishedPeopleCount = 0;  // 심사 완료한 사람 수
                long allFinishedTime = (left + right) / 2;

                finishedPeopleCount = getFinishedPeople(allFinishedTime, times);
                if (finishedPeopleCount < (long) n) {
                    left = allFinishedTime + 1;
                } else {
                    // n 보다 크거나 같다면,
                    right = allFinishedTime - 1;
                    answer = allFinishedTime;
                }
            }
            return answer;
        }

        private long getFinishedPeople(long allFinishedTime, int[] times) {
            long numberOfPeople = 0;
            for (int i = 0; i < times.length; i++) {
                numberOfPeople += allFinishedTime / times[i];
            }
            return numberOfPeople;
        }

        private long findMax(int n, int[] times) {
            Arrays.sort(times);
            return (long) n * times[times.length - 1];
        }
    }
}


