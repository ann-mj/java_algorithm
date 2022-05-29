package BOJ.BinarySearch.q1920;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static FastReader scan = new FastReader();

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void solution() {
        // arrN 에 특정 숫자가 있는지 알아낸다.
        Arrays.sort(arrN);

        for (int i = 0; i < M; i++) {
            if (isExist(arrM[i])) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    private static boolean isExist(int target) {
        boolean exist = true;

        int left = 0;
        int right = N - 1;
        while (left <= right) {
            int mid = ((left) + right) / 2;

            if (arrN[mid] == target) {
                return true;
            }
            if (arrN[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }


    static int N, M;
    static int[] arrN;
    static int[] arrM;

    public static void input() {
        N = scan.nextInt();
        arrN = new int[N];
        for (int i = 0; i < N; i++) {
            arrN[i] = scan.nextInt();
        }

        M = scan.nextInt();
        arrM = new int[M];
        for (int i = 0; i < M; i++) {
            arrM[i] = scan.nextInt();
        }
    }
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(s));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }

        Double nextDouble() {
            return Double.parseDouble(next());
        }

        Long nextLong() {
            return Long.parseLong(next());
        }

        String readLine() {
            String st = "";
            try {
                st = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st;
        }
    }
}
