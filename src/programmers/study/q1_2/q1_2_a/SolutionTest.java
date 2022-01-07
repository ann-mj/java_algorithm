package programmers.study.q1_2.q1_2_a;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void solution() {
        Solution solution = new Solution();
        int[] arr = {1, 3, 2, 4, 2};
        int[] expect = {1, 2, 3};
        Assertions.assertArrayEquals(expect, solution.solution(arr));
    }
}