package data_structure.KimJunYoung.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ProgrammersHeap2 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int[][] jobs1 = {{0, 3}, {1, 9}, {2, 6}};
        System.out.println(solved.solution(jobs1) == 9);
        int[][] jobs2 = {{0, 3}, {10, 1}};
        System.out.println(solved.solution(jobs2) == 2);
        int[][] jobs3 = {{0, 3}, {4, 2}, {2, 5}};
        System.out.println(solved.solution(jobs3) == 5);
        int[][] jobs4 = {{0, 4}, {0, 3}};
        System.out.println(solved.solution(jobs4) == 5);
    }
    static class Solution {
        public int solution(int[][] jobs) {
            // jobs를 요청시간 순으로 정렬
            // 주의할점으로는 요청시간이 동일할 경우 수행시간이 더 짧은순으로 정렬해야함
            Arrays.sort(jobs, (o1, o2) -> {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            });
            // 스케줄러에서는 태스크를 수행시간 순으로 정렬
            // 스케줄러에는 현재 수행중인 태스크가 종료되지 않았을 때 들어온 태스크들이 들어옴
            // 혹은 수행중인 태스크가 없을 때 요청이 들어오는 태스크 하나가 들어옴
            PriorityQueue<int[]> scheduler = new PriorityQueue<>(((o1, o2) -> {
                if (o1[1] == o2[1])
                    return o1[0] - o2[0];
                return o1[1] - o2[1];
            }));

            int jobPointer = 0;
            scheduler.offer(jobs[jobPointer++]);
            int endTime = 0;
            int totalDuration = 0;
            while (!scheduler.isEmpty()) {
                // 현재 수행 중인 태스크를 스케줄러의 제일 앞부터 가져옴
                int[] job = scheduler.poll();
                // 현재 태스크의 종료시간은 두 가지 경우 중 더 느린 시간임
                // 1. 이전 태스크의 종료시간 + 현재 태스크의 수행시간
                // 2. 현재 태스크의 요청시간 + 현재 태스크의 수행시간
                endTime = Math.max(endTime + job[1], job[0] + job[1]);
                // 현재 태스크의 요청부터 완료까지의 시간 = 현재 태스크 종료시간 - 현재 태스크 수행시간
                totalDuration += (endTime - job[0]);
                while (jobPointer < jobs.length) {
                    // 현재 태스크가 종료전에 요청이 들어오는 태스크의 경우
                    // 스케줄러에 삽입, 스케줄러에는 수행시간의 오름차순으로 태스크가 정렬
                    if (jobs[jobPointer][0] <= endTime) {
                        scheduler.offer(jobs[jobPointer++]);
                        continue;
                    }
                    // 다음 태스크가 현재 태스크가 종료 후에 요청되는 경우
                    // 수행항 태스크 하나를 스케줄러에 추가
                    if (scheduler.isEmpty()) {
                        scheduler.offer(jobs[jobPointer++]);
                    }
                    break;
                }
            }
            return totalDuration / jobs.length;
        }
    }
}
