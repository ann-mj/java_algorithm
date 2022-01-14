package programmers.study.q1_8.solution1;

import java.util.ArrayList;
import java.util.Collections;

class Solution {
    class Word implements Comparable<Word> {
        private String s;
        private int sortedIndex;   /* 정렬 기준 */

        public String getS() {
            return s;
        }

        public Word(String s, int sortedIndex) {
            this.s = s;
            this.sortedIndex = sortedIndex;
        }

        @Override
        public int compareTo(Word o) {
            if (this.s.charAt(sortedIndex) == o.s.charAt(sortedIndex)) {
                return this.s.compareTo(o.s);
            }
            return this.s.charAt(sortedIndex) - o.s.charAt(sortedIndex);
        }
    }

    public String[] solution(String[] strings, int n) {
        String[] answer = new String[strings.length];
        ArrayList<Word> ans = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            ans.add(new Word(strings[i], n));
        }

        Collections.sort(ans);
        for (int i = 0; i < strings.length; i++) {
            answer[i] = ans.get(i).getS();
        }

        return answer;
    }
}