package BOJ.Challenge.q2;

class Solution {
    public static void main(String[] args) {
        int n = 9;
        boolean clockwise = false;
        Solution sol = new Solution();
        int[][] answer = sol.solution(n,clockwise);
//        for(int i=0; i<answer.length; i++) {
//            for(int a : answer[i]) {
//                System.out.print(a + " ");
//            }
//            System.out.println();
//        }
    }
    public int[][] solution(int n, boolean clockwise) {
        int[][] arr = new int[n][n];
        if(n==1) {
            arr = new int[1][1];
            arr[0][0] = 1;
            return arr;
        }
        // 다 채우고
        int sy = 0;
        int sx = 0;
        arr[sy][sx] = 1;
        int amount = 1;
        if(clockwise) {
            boolean seq = true; // 시계 : x , false : y
            boolean[] plus = {true, true, false, false};
            int plusCount = 0;
            while(amount <= n/2) {
                if(seq) {
                    fillX(arr, sy, sx, n, amount, plus[plusCount% 4]);
                    if(plus[plusCount% 4]) {
                        sx = n - amount - 1;
                    } else {
                        sx = amount;
                    }                } else {
                    fillY(arr, sy, sx, n, amount, plus[plusCount% 4]);
                    if(plus[plusCount% 4]) {
                        sy = n - amount - 1;
                    } else {
                        sy = amount;
                    }
                }
                plusCount++;
                amount++;
                seq = !seq;
            }
        } else {
            boolean seq = false; // 시계 : x , false : y
            boolean[] plus = {true, true, false, false};
            int plusCount = 0;
            while(amount <= n/2) {
                if(seq) {
                    fillX(arr, sy, sx, n, amount, plus[plusCount% 4]);
                    if(plus[plusCount% 4]) {
                        sx = n - amount - 1;
                    } else {
                        sx = amount;
                    }
                } else {
                    fillY(arr, sy, sx, n, amount, plus[plusCount% 4]);
                    if(plus[plusCount% 4]) {
                        sy = n - amount - 1;
                    } else {
                        sy = amount;
                    }
                }
                plusCount++;
                amount++;
                seq = !seq;
            }
        }

        // 회전 시키고
        for(int k=0; k<4; k++) {
            for(int y=0; y<n; y++) {
                for(int x=0; x<n; x++) {
                    if(arr[y][x] ==0) continue;
                    arr[x][n-1-y] = arr[y][x];
                }
            }
        }


        // 가운데 값 비었으면 채워주고
        if(arr[n/2][n/2] == 0) {
            arr[n / 2][n / 2] = arr[n / 2][n / 2 - 1] + 1;
        }

//        for(int i=0; i<n; i++) {
//            for(int j=0; j<n; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
        return arr;
    }

    public void fillY(int[][] arr, int sy, int sx, int n, int amount, boolean plus) {
        // 1  1 이 왔다고 치자. ( 실제 정답은 0을 return 해야함. )
        // y 는 1부터 4까지 채워야 한다.
        int startValue = arr[sy][sx];
        if(plus) {
            for(int y=sy; y<n-amount; y++) {
                arr[y][sx] = startValue++;
            }
        } else {
            for(int y=sy; y>=amount; y--) {
                arr[y][sx] = startValue++;
            }
        }

    }
    public void fillX(int[][] arr, int sy, int sx, int n, int amount, boolean plus) {
        // 1  1 이 왔다고 치자. ( 실제 정답은 0을 return 해야함. )
        // y 는 1부터 4까지 채워야 한다.
        int startValue = arr[sy][sx];
        if(plus) {
            for (int x = sx; x < n - amount; x++) {
                arr[sy][x] = startValue++;
            }
        } else {
            System.out.println("sy = " + sy);
            System.out.println("sx = " + sx);
            for (int x = sx; x >=amount; x--) {
                arr[sy][x] = startValue++;
            }
        }
    }
}