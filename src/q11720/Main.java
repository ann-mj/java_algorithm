package q11720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static FastReader scan = new FastReader();

    static int N;
    static String str;
    public static void main(String[] args) {
        input();
        pro();
    }

    static void pro() {
        int a = 0;
        for(int i=0; i<str.length(); i++) {
            a += str.charAt(i) - '0';
        }
        System.out.println(a);
    }

    static void input() {
        N = scan.nextInt();
        str = scan.nextLine();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st==null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }
    }
}
