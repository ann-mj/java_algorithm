package programmers.study.q2_3.solution1;

import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> duringDates = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < progresses.length; i++) {
            duringDates.add((int) Math.ceil((double) (100 - progresses[i]) / speeds[i]));
        }

        int start = 0;
        for (int i = 1; i < duringDates.size(); i++) {
            if (duringDates.get(start) < duringDates.get(i)) {
                result.add(i - start);
                start = i;
            }
            if (start == duringDates.size() - 1) {
                result.add(1);
            }
            if (i == duringDates.size() - 1 && (duringDates.get(start) > duringDates.get(i))) {
                result.add(i - start + 1); /* 끝까지 봤는데도 작은게 없을 수도 있음 */
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
