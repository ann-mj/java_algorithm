package programmers.study.q2_3.solution2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> Q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < progresses.length; i++) {
            Q.offer((int) Math.ceil((double) (100 - progresses[i]) / speeds[i]));
        }

        int prev = Q.peek(); /* 이전 작업 */
        int count = 0; /* 이전 작업보다 적게 걸리는 작업의 수 */

        while (!Q.isEmpty()) {
            int next = Q.poll();

            /* 다음 작업이 이전 작업 보다 적게 걸린다면 */
            if (next <= prev) {
                count++;
            } else {

                /* 다음 작업이 이전 작업 보다 많이 걸린다면 */
                result.add(count);
                prev = next;
                count = 1;
            }
        }

        /*
         * 이레 두가지 조건을 충족시키기 위해 이 코드 추가
        *
        * count 가 1인 경우
        * 맨 마지막 2개가 5 10 이라고 하면, 맨 마지막 원소를 뺀다고 하면,
        * next 가 10이고, prev 가 5인 경우 위의 else 문을 통과하면서 count 는 1이되고, Q가 비어서 다음 문장을 실행할 수 없게 된다.
        *
        * count 가 1보다 큰 경우
        * 맨 마지막 2개가 10 4 5 이라고 하면, 맨 마지막 원소를 뺀다고 하면,
        * next 가 5이고, prev 가 10인 경우 위의 if 문을 통과하면서 count 는 2가 되는데, result 에 넣어주질 못했으므로 넣어준다.
        *
        * */
        result.add(count);

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}

