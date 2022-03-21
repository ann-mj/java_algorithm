package sdfsdfsdfs.solution1;

import java.util.*;

public class Main {
    static String solve(String g, String[] goods) {
        StringBuilder ans = new StringBuilder();
        Set<String> candi = new HashSet<>();
        candi.clear();
        List<String> goal = new ArrayList<>();
        goal.clear();
        int minLength = 0;
        for(int i=1; i<g.length(); i++) {
             // length 별로 돈다. length : i 이다
            if(minLength >= 1) break;
            for (int k = 0; k + i <= g.length(); k++) {
                String sub = g.substring(k, k + i);
                int cnt = 0;
                for(String d : goods) {
                    if(cnt > 1) break;
                    if(d.contains(sub)) cnt++;
                }
                if(cnt == 1) {
                    minLength = i;
                    if(candi.contains(sub)) continue;
                    candi.add(sub);
                    goal.add(sub);
                }
            }
        }

        Collections.sort(goal);
        for(int i=0; i<goal.size(); i++) {
            String a = goal.get(i);
            if(i < goal.size() -1) {
                ans.append(a).append(" ");
            } else {
                ans.append(a);
            }
        }
        if(minLength == 0) {
            ans.append("None");
        }

        return ans.toString();
    }
    static class Solution {
        public String[] solution(String[] goods) {
            String[] answer = {};
            List<String> candi = new ArrayList<>();

            for(String g : goods) {
                String tok = solve(g, goods);

                candi.add(tok);
            }
            answer = new String[goods.length];
            for(int i=0; i< goods.length; i++) {
                answer[i] = candi.get(i);
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        String[] gg = {"bb","ab","ac","ad"};
        Solution sol = new Solution();
        String[] ans = sol.solution(gg);
        for(String a : ans) {
            System.out.println(a);
        }
    }
}
