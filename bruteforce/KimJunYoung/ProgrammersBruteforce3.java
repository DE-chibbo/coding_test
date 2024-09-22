package bruteforce.KimJunYoung;

import java.util.*;
import java.util.stream.Collectors;

public class ProgrammersBruteforce3 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        String numbers1 = "1231";
        System.out.println(solved.solution(numbers1));
    }
    static class Solution {
        static StringBuilder sb = new StringBuilder();

        public int solution(String numbers) {
            int answer = 0;
            Set<Integer> permutations = new HashSet<>();
            boolean[] visited = new boolean[numbers.length()];
            for (int i = 1; i <= numbers.length(); i++) {
                getPermutations(numbers, i,visited, permutations);
            }
            for (int number: permutations) {
                if (number <= 1) {
                    continue;
                }
                if (isPrime(number)) {
                    answer++;
                }
            }
            return answer;
        }

        public void getPermutations(String numbers, int range, boolean[] visited, Set<Integer> permutations) {
            if (sb.length() == range) {
                return;
            }
            for (int i = 0; i < numbers.length(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    sb.append(numbers.charAt(i));
                    permutations.add(Integer.parseInt(sb.toString()));
                    getPermutations(numbers, range, visited, permutations);
                    visited[i] = false;
                    sb = new StringBuilder(sb.substring(0, sb.length() - 1));
                }
            }
        }

        public boolean isPrime(int number) {
            for (int i = 2; i < (int) Math.sqrt(number) + 1; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
