package testex.solution1;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        String[] mm = {"..........", "AAACC.....", ".AAA.....Z", "..AAAA..C.", "...BBBBB..", "....BBB...", "...ZBBB...", "ZZZZAAAC..", ".....CCCC.", "QQ......C.", ".........."};
        String[] mm = {"A.B.C.D", ".B.C.D."};
        int[] answ = solution.solution(mm);
        for (int a : answ) {
            System.out.println(a);
        }
    }

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0,-1, 1};
    static class Country {
        char name;
        Set<Country> adj = new HashSet<>();

        public Country(char name) {
            this.name = name;
        }

        int sizeOfAdj() {
            return adj.size();
        }

        void addAdj(Country c) {
            adj.add(c);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public boolean equals(Object a) {
            Country obj = (Country) a;
            return name == obj.name;
        }
    }

    static class Border {
        Country from;
        Country to;

        public Border(Country from, Country to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from) + Objects.hash(to);
        }

        @Override
        public boolean equals(Object a) {
            Border obj = (Border) a;
            return this.from.equals(obj.from) || this.to.equals(obj.to)
                    || this.from.equals(obj.to) || this.to.equals(obj.from);
        }
    }
    public int[] solution(String[] maps) {
        int[] answer = new int[2];
        int maxY = maps.length;
        int maxX = maps[0].length();
        char[][] board = new char[maxY][maxX];
        // 알파벳 별로 등장횟수를 카운트하자
        // 나라들은 알파벳 대문자
        Country[] alpha = new Country[26]; // 0 : A  ... 25 : Z
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                board[y][x] = maps[y].charAt(x);
            }
        }
        for(int i=0; i<26; i++) {
            alpha[i] = new Country((char) (i + 'A'));
        }

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (board[y][x] == '.') continue;
                Country current = alpha[board[y][x] - 'A'];
                for(int i=0; i<4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if(ny < 0 || ny >= maxY || nx < 0 || nx >= maxX) continue;
                    if(board[ny][nx] == '.') continue;
                    if(board[ny][nx] == current.name) continue;
                    current.addAdj(alpha[board[ny][nx] - 'A']);
                }
            }
        }

        int maxCount = 0;
        Set<Border> borders = new HashSet<>();
        for(int i=0; i<26; i++) {
            for(Country adj : alpha[i].adj) {
                borders.add(new Border(alpha[i], adj));
            }
            maxCount = Math.max(maxCount, alpha[i].sizeOfAdj());
        }
        answer[0] = borders.size();
        answer[1] = maxCount;
        return answer;
    }
}

