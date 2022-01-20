package programmers.study.q2_5.solution3;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> humans = new HashMap<>();

        for (String part : participant) {
            humans.put(part, humans.getOrDefault(part, 0) + 1);
        }

        for (String comp : completion) {
            humans.put(comp, humans.get(comp)-1);
        }

        for (Map.Entry<String, Integer> entry : humans.entrySet()) {
            if (entry.getValue() == 1) {
                answer = entry.getKey();
            }
        }
        return answer;
    }
}
