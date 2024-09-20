package data_structure.KimJunYoung.heap;

import java.util.*;

public class ProgrammersHeap1 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int[] sc1 = {1, 2, 3, 9, 10, 12};
        int K1 = 7;
        System.out.println(solved.solution(sc1, K1)); // 2
        int[] sc2 = {1, 2, 3, 4, 5, 6, 7};
        int K2 = 1_000_000_000;
        System.out.println(solved.solution(sc2, K2)); // -1

    }
    static class Solution {
        public int solution(int[] scoville, int K) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
            for (int s : scoville) {
                pq.offer(s);
            }
            int count = 0;
            while (!pq.isEmpty()) {
                int min = pq.poll();
                if (min >= K) {
                    return count;
                }
                if (pq.isEmpty()) {
                    break;
                }
                int second = pq.poll();
                pq.offer(min + second * 2);
                count++;
            }
            return -1;
        }
    }
}
