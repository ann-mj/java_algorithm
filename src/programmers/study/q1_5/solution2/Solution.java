package programmers.study.q1_5.solution2;

import java.util.Arrays;

public class Solution {

    private static Integer[] chosenNumber = new Integer[3];
    private static int answer;
    static int count = 0;

    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 4,5,6};
        System.out.println(solution(nums));
    }

    public static int solution(int[] nums) {
        combination(nums, 0, 0);
        System.out.println("count = " + count);
        return answer;
    }

    static void combination(int[] nums, int cnt, int start) {
        System.out.print("cnt = " + cnt + " ");
        System.out.print("Start = " + start + " ");
        System.out.println(Arrays.deepToString(chosenNumber));
        count++;
        if (cnt == 3) {
            int sum = getSum(chosenNumber);
            if(isPrime(sum)) answer++;
            return;
        }
        for (int i = start; i < nums.length; i++) {
            chosenNumber[cnt] = nums[i];
            combination(nums, cnt + 1, i + 1);
        }
    }

    private static int getSum(Integer[] chosenNumber) {
        int sum = 0;
        for (int i : chosenNumber) {
            sum += i;
        }
        return sum;
    }

    private static boolean isPrime(int sum) {
        for (int i = 2; i <= Math.sqrt(sum); i++) {
            if (sum % i == 0) return false;
        }
        return true;
    }

}
