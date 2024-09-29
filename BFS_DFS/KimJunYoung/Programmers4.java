package BFS_DFS.KimJunYoung;

import java.util.*;

public class Programmers4 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        String begin1 = "hit";
        String target1 = "cog";
        String[] words1 = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(solved.solution(begin1, target1, words1) == 4);
    }

    static class Solution {
        int count = Integer.MAX_VALUE;
        public int solution(String begin, String target, String[] words) {
            int answer = 0;

            Set<String> set  = new LinkedHashSet<>(Arrays.asList(words));
            if (!set.contains(target)) {
                return answer;
            }

            Map<String, Boolean> visited = new HashMap<>();
            for(String word: set) {
                visited.put(word, false);
            }

            dfs(begin, target, set, visited, 0);
            if (count == Integer.MAX_VALUE) {
                return 0;
            }
            answer = count;

            return answer;
        }

        private void dfs(String current, String target, Set<String> words, Map<String, Boolean> visited, int depth) {
            if (current.equals(target)) {
                count = Math.min(count, depth);
                return;
            }
            for (String word: words) {
                if (visited.get(word)) {
                    continue;
                }

                int distance = getDistance(current, word);
                if (distance == 1) {
                    visited.put(word, true);
                    dfs(word, target, words, visited, depth + 1);
                    visited.put(word, false);
                }
            }

        }

        private int getDistance(String word1, String word2) {
            int length = word1.length();
            int distance = length;
            for (int i = 0; i < length; i++) {
                if (word1.charAt(i) == word2.charAt(i)) {
                    distance--;
                }
            }
            return distance;
        }
    }
}
