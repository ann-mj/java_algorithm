package programmers.DFSBFS.q43164;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[][] tic = {{"ICN", "JFK"},{"HND", "IAD"}, {"JFK", "HND"}};
        String[] ans = solution.solution(tic);
        for (String a : ans) {
            System.out.println(a);
        }
    }

    static class Solution {
        static List<String> answers = new ArrayList<>();
        public String[] solution(String[][] tickets) {
            String[] answer;
            List<Integer> ICNs = new ArrayList<>();

            // ICN 으로 시작해야 한다.
            for (int i = 0; i < tickets.length; i++) {
                if (isEqualsToDepart(tickets[i][0], "ICN")) {
                    ICNs.add(i);
                }
            }

            for (int i = 0; i < ICNs.size(); i++) {
                boolean[] visit = new boolean[tickets.length];
                findTicket(visit, tickets, ICNs.get(i), 0, "ICN" + " ");
            }

            Collections.sort(answers);

            String path = answers.get(0); // 단어 순 정렬
            // 3개 씩 끊어서 answer 에 담기
            answer = path.split(" ");
            return answer;
        }

        public void findTicket(boolean[] visit, String[][] tickets, int idx, int ticketCount, String path) {
            // 해당 idx 방문 처리
            visit[idx] = true;
            ticketCount++;
            
            if (ticketCount == tickets.length) {
                // 마지막 to 를 더해주자.
                answers.add(path.concat(tickets[idx][1]));
                return;
            }

            String to = tickets[idx][1];
            for (int i = 0; i < tickets.length; i++) {
                if(visit[i]) continue;
                String depart = tickets[i][0];
                if (isEqualsToDepart(to, depart)) {
                    boolean[] vis = new boolean[tickets.length];
                    System.arraycopy(visit, 0, vis, 0, tickets.length);
                    vis[i] = true;
                    findTicket(vis, tickets, i, ticketCount, path.concat(depart) + " ");
                }
            }
        }

        private boolean isEqualsToDepart(String to, String depart) {
            return to.equals(depart);
        }
    }
}
