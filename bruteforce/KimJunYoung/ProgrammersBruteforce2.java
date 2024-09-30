package bruteforce.KimJunYoung;

import java.util.*;

public class ProgrammersBruteforce2 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int[] answers1 = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(solved.solution(answers1)));
    }
    static class Solution {
        public int[] solution(int[] answers) {
            int[] pattern1 = {1, 2, 3, 4, 5};
            int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5};
            int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
            int[] counts = new int[3];

            for (int i = 0; i < answers.length; i++) {
                int answer = answers[i];
                if (answer == pattern1[i % pattern1.length]) {
                    counts[0]++;
                }
                if (answer == pattern2[i % pattern2.length]) {
                    counts[1]++;
                }
                if (answer == pattern3[i % pattern3.length]) {
                    counts[2]++;
                }
            }

            int max = Math.max(counts[0], Math.max(counts[1], counts[2]));
            List<Integer> winners = new ArrayList<>();
            for (int i = 0; i < counts.length; i++){
                if (counts[i] == max) {
                    winners.add(i + 1);
                }
            }
            return winners.stream().mapToInt(x -> x).toArray();
        }
    }
}
