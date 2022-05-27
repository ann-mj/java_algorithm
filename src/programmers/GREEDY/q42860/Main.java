package programmers.GREEDY.q42860;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution so = new Solution();
        String name = "JEROEN";
        System.out.println(so.solution(name));
    }

    static class Solution {
        public int solution(String name) {
            int answer = Integer.MAX_VALUE;
            char[] start = new char[name.length()];
            Arrays.fill(start, 'A');

            int cursor = 0; // 0부터 시작
            for (int i = 0; i < start.length; i++) {
                // i 번째부터 좌측으로 전환
                // i 번째 까지는 우측으로
                int count = 0;
                Arrays.fill(start, 'A');
                boolean isSame = false;
                for (int j = 0; j <= i; j++) {
                    cursor = j;
                    int distFromA = name.charAt(cursor) - 'A';
                    // 다르다면 개수만큼 count 를 증가시킨다.
                    if (start[cursor] != name.charAt(cursor)) {
                        count += Math.min(Math.abs(26 - distFromA), distFromA);
                        start[cursor] = name.charAt(cursor);
                    } else if (start[cursor] == name.charAt(cursor)) {
                        if(j == i) {
                            break;
                        }
                    }
                    if (isSame(start, name)) {
                        isSame = true;
                        break;
                    }
                    if (j != i) {
                        count++;
                    }
                }
                if (isSame) {
                    answer = Math.min(answer, count);
                    break;
                }
                // 맨 마지막 문자로 커서를 옮기자.
                count += cursor + 1; // 0번째라면 1번 이동해야 끝으로 간다.
                cursor = name.length() - 1;

//                // cursor 가 제일 마지막에 있는 상태에서 이제는 뒤로 간다.
                for (int j = cursor; j > i; j--) {
                    cursor = j;
                    int distFromA = name.charAt(cursor) - 'A';
                    // 다르다면 개수만큼 count 를 증가시킨다.
                    if (start[cursor] != name.charAt(cursor)) {
                        count += Math.min(Math.abs(26 - distFromA), distFromA);
                        start[cursor] = name.charAt(cursor);
                    }
                    if (isSame(start, name)) {
                        isSame = true;
                        break;
                    }
                    count++;
                }
                answer = Math.min(answer, count);
            }


            for (int i = start.length-1; i >= 0 ; i--) {
                int count = 0;
                Arrays.fill(start, 'A');
                boolean isSame = false;

                // cursor 가 제일 마지막에 있는 상태에서 이제는 뒤로 간다.
                for (int j = start.length-1; j >= i; j--) {
                    cursor = j;
                    int distFromA = name.charAt(cursor) - 'A';
                    // 다르다면 개수만큼 count 를 증가시킨다.
                    if (start[cursor] != name.charAt(cursor)) {
                        count += Math.min(Math.abs(26 - distFromA), distFromA);
                        start[cursor] = name.charAt(cursor);
                    }
                    if (isSame(start, name)) {
                        isSame = true;
                        break;
                    }
                    count++;
                }
                
                if (isSame) {
                    answer = Math.min(answer, count);
                    break;
                }
                // 맨 처음 문자로 커서를 옮기자.
                count += name.length() - cursor; // 0번째라면 1번 이동해야 끝으로 간다.
                cursor = 0;

                for (int j = 0; j < i; j++) {
                    int distFromA = name.charAt(j) - 'A';
                    // 다르다면 개수만큼 count 를 증가시킨다.
                    if (start[j] != name.charAt(j)) {
                        count += Math.min(Math.abs(26 - distFromA), distFromA);
                        start[j] = name.charAt(j);
                    }
                    if (isSame(start, name)) {
                        isSame = true;
                        break;
                    }
                    count++;
                }
                answer = Math.min(answer, count);
            }

            return answer;
        }

        private boolean isSame(char[] start, String name) {
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) != start[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}


