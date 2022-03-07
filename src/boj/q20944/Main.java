package boj.q20944;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Character> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            answer.add('a');
        }

        if ((N % 2) == 1) {
            answer.set((N - 1) / 2, 'b');
        }

        System.out.println(answer.stream().map(String::valueOf).collect(Collectors.joining()));
    }
}
