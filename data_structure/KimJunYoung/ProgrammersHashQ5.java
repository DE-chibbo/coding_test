package data_structure.KimJunYoung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
프로그래머스 코딩테스트 고득점 kit 해시, 문제 5번: 베스트앨범
 */
public class ProgrammersHashQ5 {
    public static void main(String[] args) {
        ProgrammersHashQ5 solved = new ProgrammersHashQ5();
        String[] genres = {"classic", "classic", "classic", "classic", "pop"};
        int[] plays = {50, 60, 100, 30, 8000};
        System.out.println(Arrays.toString(solved.solution(genres, plays))); // answer: [4, 2, 1]
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<int[]>> music = new HashMap<>(); // key: genre, value: music info(id, play time) of key
        for (int i = 0; i < genres.length; i++) {
            if (music.containsKey(genres[i])) {
                music.get(genres[i]).add(new int[]{i, plays[i]});
            } else {
                List<int[]> playsInfo = new ArrayList<>();
                playsInfo.add(new int[]{i, plays[i]});
                music.put(genres[i], playsInfo);
            }
        }

        // sort music of each genre by play time DESC and by id ASC when play time is same
        for (List<int[]> values : music.values()) {
            values.sort((o1, o2) -> {
                if (o2[1] == o1[1]) {
                    return o1[0] - o2[0];
                }
                return o2[1] - o1[1];
            });
        }
        // sort genre by total music play time DESC
        List<String> genreArr = new ArrayList<>(music.keySet());
        genreArr.sort((o1, o2) -> {
            return sum(music.get(o2)) - sum(music.get(o1));
        });

        // board to return
        List<Integer> answer = new ArrayList<>();
        int index = 0;
        // max count of plays on board
        int maxLength = music.keySet().size() * 2;

        // in sorted genres
        for (String genre : genreArr) {
            // get sorted plays of current genre
            List<int[]> value = music.get(genre);
            int count = 0;
            // get two top plays or get one if current genre has only one
            for (int[] play : value) {
                // get two plays or (one or nothing) : when remain space in board is under 2
                if (count == 2 || index >= maxLength) {
                    break;
                }
                answer.add(play[0]);
                index++;
                count++;
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int sum(List<int[]> value) {
        int sum = 0;
        for (int[] play :
                value) {
            sum += play[1];
        }
        return sum;
    }
}

