package bruteforce.KimJunYoung;

import java.util.Arrays;
import java.util.Comparator;

public class ProgrammersBruteforce4 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int brown1 = 10;
        int yellow1 = 2;
        System.out.println(Arrays.toString(solved.solution(brown1, yellow1)));
        int brown2 = 24;
        int yellow2 = 24;
        System.out.println(Arrays.toString(solved.solution(brown2, yellow2)));

    }
    static class Solution {
        public int[] solution(int brown, int yellow) {
            int[] answer = {};
            int area = brown + yellow;
            for (int i = 3; i < Math.sqrt(area) + 1; i++) {
                if (area % i != 0) {
                    continue;
                }
                int quotient = area / i;
                int expectedYellow = (i - 2) * (quotient - 2);
                if (expectedYellow == yellow) {
                    answer = new int[]{i, quotient};
                }
            }
            Arrays.sort(answer);
            return new int[]{answer[1], answer[0]};
        }
    }
}
