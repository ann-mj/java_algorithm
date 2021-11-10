package q1068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static FastReader scan = new FastReader();

    static int N;
    static int[] a; // 노드의 상태를 저장하는 배열
    static int remove;
    static int root;
    static ArrayList<Integer>[] parent;

    static void input() {
        N = scan.nextInt();
        a = new int[N];
        parent = new ArrayList[N];
        for(int j=0; j<N; j++) parent[j] = new ArrayList<Integer>();

        root = 0;
        for(int i=0; i<N; i++) {
            a[i] = scan.nextInt();
            if(a[i] == -1) {
                root = a[i];
                continue;
            }
            parent[a[i]].add(i);
        }
        remove = scan.nextInt();
    }

    static void pro() {
        // remove 를 parent 로 하는 모든 노드 제거
        removeNode(remove);
        // n 의 부모 arrayList 에서 n을 제거한다.
        for(int i=0; i<N; i++) {
            for(int j=0; j<parent[i].size(); j++) {
                if(parent[i].get(j) == remove) {
                    parent[i].remove(j);
                }
            }
        }

        int cnt = 0;
        for(int i=0; i<N; i++) {
            if(a[i] == -2) continue;

            if(parent[i].size() == 0) {
                cnt++;
            }
        }

        if(root == remove) {
            System.out.println(0);
        }  else {
            System.out.println(cnt);
        }
    }

    static void removeNode(int n) {
        a[n] = -2;

        for(int j=0; j<parent[n].size(); j++) {
            a[parent[n].get(j)] = -2;
            removeNode(parent[n].get(j));
        }
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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

        String nextLine() {
            String str = "";

            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;

        }
    }
}
