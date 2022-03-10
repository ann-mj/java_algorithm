package BOJ.DivedAndConquer.Q2447;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine()); // 입력이 뭔지를 제대로 체크하자.
        for (int i = 1; ; i++) {
            if ((int) Math.pow(3, i) == size) {
                N = i;
                break;
            }
        }

        arr = new char[size + 1][size + 1];

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                arr[i][j] = '*';
            }
        }

        star(1, 1, size, size, N);

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                sb.append(arr[i][j]);
            }

            if (i == size) continue;
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void star(int sy, int sx, int ey, int ex, int k) {
        if(k == 0) return;

        // 별 업애기
        for (int y = sy + (int) Math.pow(3, k - 1); y <= ey - (int) Math.pow(3, k - 1); y++) {
            for (int x = sx + (int) Math.pow(3, k - 1); x <= ex - (int) Math.pow(3, k - 1); x++) {
                arr[y][x] = ' ';
            }
        }

        for (int y = sy; y < ey; y = y + (int) Math.pow(3, k-1)) {
            for (int x = sx; x < ex; x = x + (int) Math.pow(3, k-1)) {
                if (arr[y][x] == ' ') continue;
                star(y, x, y - 1 + (int) Math.pow(3, k - 1), x - 1 + (int) Math.pow(3, k - 1), k - 1);
            }
        }

    }
}
