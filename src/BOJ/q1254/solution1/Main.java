package BOJ.q1254.solution1;

import java.util.Scanner;

/**
 * 문자열 S -> 0 개 이상의 문자를 문자열 뒤에 추가하여 팰린드롬을 만든다.
 * 가능하면 가장 짧은 문자열을 만든다.
 * 만들 수 있는 가장 짧은 팰린드롬의 길이를 출력하는 프로그램을 작성하자.
 * <p>
 * 팰린드롬 : 앞에서 읽으나, 뒤에서 읽으나 같게 읽히는 문자열을 말한다.
 */

// 해당풀이는 틀린풀이임.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if (s.length() == 0) {
            System.out.println(s.length());
            return;
        }
        StringBuilder sb = new StringBuilder(s);
        char[] input = s.toCharArray();
        // 1. 이 문자가 팰린드롬인지 체크
        if (isPD(s.toCharArray())) {
            System.out.println(input.length);
            return;
        }

        // 2. 팰린드롬이 아니라면 만들자.
        int start = 0;
        String toAdd = "";
        while (true) {

            int end = 0;
            boolean isPrint = true;
            for (int i = 0; i < sb.length(); i++) {
                // 처음과 끝이 같다면
                if (sb.charAt(i) == sb.charAt(sb.length() - 1 - i)) {
                    end++;
                } else {
                    if(end + 1 == sb.length()) break;
                    toAdd = sb.substring(start, end+1);
                    isPrint = false;
                    break;
                }
            }
            // 스트링 더해주기
            sb = new StringBuilder(s);
            for (int i = 0; i < toAdd.length(); i++) {
                sb.append(toAdd.charAt(toAdd.length() - 1 - i));
            }

            if (isPrint) {
                System.out.println(sb.length());
                return;
            }
        }
    }

    private static boolean isPD(char[] input) {
        for (int i = 0; i <= input.length / 2; i++) {
            if (input[i] == input[input.length - 1 - i]) continue;
            return false;
        }
        return true;
    }
}
