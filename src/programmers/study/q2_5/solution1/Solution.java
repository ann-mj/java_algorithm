package programmers.study.q2_5.solution1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] pa = {"leo", "kiki", "eden"};
        String[] co = {"eden", "kiki"};
    }

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> p = new HashMap<>();
        Map<String, Integer> c = new HashMap<>();
        Arrays.sort(participant);
        Arrays.sort(completion);

        for (String part : participant) {
            if (p.containsKey(part)) {
                p.put(part, p.get(part) + 1);
                continue;
            }
            p.put(part, 1);
        }

        for (String comp : completion) {
            if (c.containsKey(comp)) {
                c.put(comp, c.get(comp) + 1);
                continue;
            }
            c.put(comp, 1);
        }

        for (Map.Entry<String, Integer> entry : p.entrySet()) {
            if (!c.containsKey(entry.getKey())) {
                answer = entry.getKey();
                break;
            }
            if (c.get(entry.getKey()) != p.get(entry.getKey())) {
                answer = entry.getKey();
                break;
            }
        }

        return answer;
    }
}