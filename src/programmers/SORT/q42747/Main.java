package programmers.SORT.q42747;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution so = new Solution();
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(so.solution(citations));
    }

    static class Solution {
        public int solution(int[] citations) {
            int answer = 0;
            Arrays.sort(citations);
            int h = citations[citations.length - 1];
            int rightOfH = 0; // H 이상
            int leftOfH = 0; // H 이하
            while (h >= 0) {
                leftOfH = getLeftCount(h, citations);
                rightOfH = getRightCount(h, citations);
                if(leftOfH <= h && rightOfH >= h) {
                    answer = h;
                    break;
                }
                h--;
            }

            return answer;
        }

        private int getRightCount(int h, int[] citations) {
            int count = 0;
            for (int i = 0; i < citations.length; i++) {
                if(h == citations[i]) count++;
                if(h < citations[i]) count++;
            }
            return count;
        }

        private int getLeftCount(int h, int[] citations) {
            int count = 0;
            for (int i = 0; i < citations.length; i++) {
                if(h == citations[i]) count++;
                if(h > citations[i]) count++;
            }
            return count;
        }

        private int getNumberOfH(int h, int[] citations) {
            int result = 0;
            for (int i = 0; i < citations.length; i++) {
                if (h == citations[i]) {
                    result++;
                }
            }
            return result;
        }

        private int getIndexOfH(int h, int[] citations) {
            int result = 0;
            for (int i = 0; i < citations.length; i++) {
                if(h < citations[i]) break;
                result = i; // h 가 같은게 여러개 있다면, 가장 좌측의 h 를 말한다.
            }
            return result;
        }
    }
}


