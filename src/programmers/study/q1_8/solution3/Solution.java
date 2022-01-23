package programmers.study.q1_8.solution3;

import java.util.Arrays;
import java.util.stream.Collectors;

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
        String[] answer = Arrays.asList(strings)
                .stream().map(a -> new Word(a, n)).sorted()
                .map(Word::getS).toArray(String[]::new);
        return answer;
    }
}