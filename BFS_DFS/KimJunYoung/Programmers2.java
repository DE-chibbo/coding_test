package BFS_DFS.KimJunYoung;

import java.util.*;

public class Programmers2 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int n1 = 3;
        int[][] computers1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solved.solution(n1, computers1) == 2);

        int n2 = 3;
        int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        System.out.println(solved.solution(n2, computers2) == 1);
    }
    static class Solution {
        public int solution(int n, int[][] computers) {
            int answer = 0;
            boolean[] visited = new boolean[n];
            for (int start = 0; start < n; start++) {
                if (!visited[start]) {
                    bfs(start, computers, visited);
                    answer++;
                }
            }
            return answer;
        }

        private void bfs(int start, int[][] computers, boolean[] visited) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(start);
            visited[start] = true;
            while (!queue.isEmpty()) {
                int from = queue.poll();
                int[] nodes = computers[from];
                for (int to = 0; to < nodes.length; to++) {
                    if (to == from) {
                        continue;
                    }
                    if (!visited[to] && nodes[to] == 1) {
                        queue.offer(to);
                        visited[to] = true;
                    }
                }
            }
        }
    }
}
