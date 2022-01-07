package programmers.study.q1_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
        String input = "UD";
        Solution s = new Solution();
        Assertions.assertEquals(1, s.solution(input));
    }
}