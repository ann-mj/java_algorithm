package programmers.HASH.q42579;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution so = new Solution();
        String[] generes = {"classic", "pop", "classic", "classic", "pop"};
        int[] p = {500, 600, 150, 800, 2500};
        for (int a : so.solution(generes, p)) {
            System.out.println(a);
        }
    }

    static class Solution {
        public int[] solution(String[] genres, int[] plays) {
            List<Integer> answer = new ArrayList<>();

            Map<String, Genre> map = new HashMap<>();

            for (int i = 0; i < genres.length; i++) {
                map.computeIfAbsent(genres[i], k -> new Genre());
                map.get(genres[i]).addSong(new Song(plays[i], i));
            }

            Iterator<Genre> values = map.values().iterator();
            List<Genre> genreList = new ArrayList<>();
            while (values.hasNext()) {
                genreList.add(values.next());
            }

            Collections.sort(genreList);

            for (int i = 0; i < genreList.size(); i++) {
                genreList.get(i).sortSong();

                for (int j = 0; j < Math.min(genreList.get(i).songs.size(), 2); j++) {
                    answer.add(genreList.get(i).songs.get(j).number);
                }
            }

            int[] array = new int[answer.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = answer.get(i);
            }

            return array;
        }
    }

    static class Song implements Comparable<Song>{
        int count;
        int number;

        public Song(int count, int number) {
            this.count = count;
            this.number = number;
        }

        @Override
        public int compareTo(Song o) {
            if (this.count != o.count) {
                return o.count - this.count;
            } else {
                return this.number - o.number;
            }
        }
    }

    static class Genre implements Comparable<Genre> {
        List<Song> songs; // song 의 고유번호
        int totalCount; // 총 재생횟수

        public Genre() {
            songs = new ArrayList<>();
            totalCount = 0;
        }

        public void addTotalCount(int count) {
            this.totalCount += count;
        }

        @Override
        public int compareTo(Genre o) {
            return o.totalCount - this.totalCount;
        }

        public void addSong(Song song) {
            songs.add(song);
            addTotalCount(song.count);
        }

        public void sortSong() {
            Collections.sort(songs);
        }
    }

}


