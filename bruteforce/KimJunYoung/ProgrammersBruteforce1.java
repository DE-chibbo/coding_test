package bruteforce.KimJunYoung;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class ProgrammersBruteforce1 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int[][] sizes1 = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        System.out.println(solved.solution(sizes1) == 4000);
        int[][] sizes2 = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
        System.out.println(solved.solution(sizes2) == 120);
        int[][] sizes3 = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};
        System.out.println(solved.solution(sizes3) == 133);
    }
    static class Solution {
        public int solution(int[][] sizes) {
            PriorityQueue<Integer> largerNumbers = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> smallerNumbers = new PriorityQueue<>(Comparator.reverseOrder());
            for (int[] size: sizes) {
                int w = size[0];
                int h = size[1];
                largerNumbers.offer(Math.max(w, h));
                smallerNumbers.offer(Math.min(w, h));
            }
            return largerNumbers.poll() * smallerNumbers.poll();
        }
    }
}
