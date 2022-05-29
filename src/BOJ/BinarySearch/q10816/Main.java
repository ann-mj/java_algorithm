package BOJ.BinarySearch.q10816;
//3
//10 10 10
//1
//10
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        solution();
    }

    private static void solution() {
        // arrN 에 특정 숫자가 있는지 알아낸다.
        Arrays.sort(arr);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            result.add(getCount(arrM[i]));
        }

        for (int a : result) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }

    private static int getCount(int target) {
        int count = 0;
        // target 이 있는지 없는지를 체크하려면, lower 바운드를 찾고, lower 바운드의 값이 target과 일치하지 않으면 0을 리턴하면 됨.

        int lowerIndex = getLowerIndex(target);
        if(arr[lowerIndex] != target) {
            count = 0;
            return count;
        }

        int upperIndex = getUpperIndex(target);
        count = upperIndex - lowerIndex + 1;
        return count;
    }

    private static int getUpperIndex(int target) {
        int left = 0;
        int right = arr.length - 1;
        int index = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) {
                index = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }

    private static int getLowerIndex(int target) {
        int left = 0;
        int right = arr.length - 1;
        int index = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= target) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    static int N, M;
    static int[] arr;
    static int[] arrM;

    public static void input() {
        N = scan.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
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
