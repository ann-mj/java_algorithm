package BOJ.q8958;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        ArrayList<String> inputs = new ArrayList<>();
        for (int i = 0; i < testCount; i++) {
            inputs.add(scanner.next());
        }

        for (String input : inputs) {
            process(input);
        }
    }

    public static void process(String input) {
        char[] str = input.toCharArray();
        int sum = 0;
        int score = 0;
        for (int i = 0; i < str.length; i++) {
            if(str[i] == 'X') {
                score = 0;
                continue;
            }

            if(str[i] == 'O') {
                score++;
            }
            sum += score;
        }
        System.out.println(sum);
    }
}
