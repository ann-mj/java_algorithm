package programmers.study.q3_4.lecture.solution1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
        Solution s = new Solution();
        int answer = s.solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        Assertions.assertEquals(2, answer);
    }
}