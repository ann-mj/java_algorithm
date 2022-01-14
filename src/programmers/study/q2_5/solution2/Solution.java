package programmers.study.q2_5.solution2;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> p = new HashMap<>();
        Map<String, Integer> c = new HashMap<>();

        for (String part : participant) {
            p.put(part, (p.containsKey(part)) ? p.get(part) + 1 : 1);
        }

        for (String comp : completion) {
            c.put(comp, (c.containsKey(comp)) ? c.get(comp) + 1 : 1);
        }

        for (Map.Entry<String, Integer> entry : p.entrySet()) {

            /* 참가자 명단에는 있는데 완주자 명단에는 없는 경우 */
            if (!c.containsKey(entry.getKey())) {
                answer = entry.getKey();
                break;
            }

            /* 완주자 명단에도 있고, 동명이인이 없으면 해당 이름은 답이 되지 않는다. */
            if (p.get(entry.getKey()) == 1) {
                continue;
            }

            /* 이 외의 경우에는 돋명이인의 수를 비교한다. */
            if (c.get(entry.getKey()) < p.get(entry.getKey())) {
                answer = entry.getKey();
                break;
            }
        }
        return answer;
    }
}