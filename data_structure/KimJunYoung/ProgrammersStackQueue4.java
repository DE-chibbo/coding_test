package data_structure.KimJunYoung;

import java.util.*;

public class ProgrammersStackQueue4 {
    public static void main(String[] args) {
        ProgrammersStackQueue4 solved = new ProgrammersStackQueue4();
        int[] priorities1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int location1 = 0;
        System.out.println(solved.solution(priorities1, location1)); // 9
    }
    public int solution(int[] priorities, int location) {
        // 핵심은 제일 우선 순위가 높은 프로세스를 제일 먼저 실행한다는 것.
        // 이러한 특성을 이용하여 우선 순위를 추적하는 내림차순 우선순위큐를 사용
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            int priority = priorities[i];
            queue.offer(new int[]{i, priority});
            pq.offer(priority);
        }
        int count = 0;
        while(!queue.isEmpty() && !pq.isEmpty()) {
            int[] curProcess = queue.poll();
            int processID = curProcess[0];
            int priority = curProcess[1];
            int maxPriority = pq.peek();
            if (priority < maxPriority) {
                queue.offer(new int[]{processID, priority});
            }
            else {
                count++;
                pq.poll();
                if (processID == location) {
                    break;
                }
            }
        }
        return count;
    }
}
