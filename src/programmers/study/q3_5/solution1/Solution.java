package programmers.study.q3_5.solution1;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] w = {"hot", "dot", "dog", "lot", "log"};
        String b = "hit";
        String t = "cog";
        System.out.println(solution.solution(b, t, w));
    }

    static boolean[] visited;
    static int[] distance;

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new boolean[words.length];
        distance = new int[words.length];
        int lengthOfWord = begin.length();
        int startIndex = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) {
                startIndex = i;
                break;
            }
        }

        /* 못 찾았다면 */
        if (startIndex == -1) {
            return answer;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startIndex);
        distance[startIndex] = 0; /* 누적 값 */
        visited[startIndex] = true;

        while (!queue.isEmpty()) {
            Integer currentIndex = queue.poll();

            /* begin 과의 거리가 1이라면, 누적 dist 값을 리턴한다. */
            int differentCount = 0;
            for (int i = 0; i < lengthOfWord; i++) {
                if (words[currentIndex].charAt(i) == begin.charAt(i)) {
                    continue;
                }
                differentCount++;
                if (differentCount > 1) {
                    break;
                }
            }
            if (differentCount == 1) {
                answer = distance[currentIndex] + 1;
                break;
            }

            /* 현재 string 과 나머지 string 과의 거리를 계산해서 1인 string 만 queue 에 넣는다. 거리 1 더해서 , 방문한 노드는 제외 */
            for (int i = 0; i < words.length; i++) {
                int count = 0;
                if (visited[i]) continue;
                for (int j = 0; j < lengthOfWord; j++) {
                    if (words[currentIndex].charAt(j) == words[i].charAt(j)) {
                        continue;
                    }
                    count++;
                    if (count > 1) {
                        break;
                    }
                }
                if (count == 1) {
                    queue.offer(i);
                    visited[i] = true;
                    distance[i] = distance[currentIndex] + 1;
                }
            }
        }
        return answer;
    }
}