package BOJ.BinarySearch.Q2805.wrong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long M;
    static int[] arr;
    static int ret;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();
        System.out.println(sb);
    }

    private static void solve() {
        int L = 0;
        int R = (int) 1e9;
        // TODO ret : 절단기 길이는 최대 10억까지네
        while (L <= R) {
            int mid = (L + R) / 2;
            long height = getTree(mid);
            // TODO 같은 경우가 아닐 수도 있음을 생각하자.
            if (height == M) {
                sb.append(ret);
                return;
            }

            if (height >= M) {
                L = mid + 1;
                ret = mid;
            } else {
                R = mid - 1;
            }
        }
    }

    private static long getTree(int H) {
        long sum = 0;

        for (Integer tree : arr) {
            if(tree < H) continue;
            sum += tree - H;
        }

        return sum;
    }
}
