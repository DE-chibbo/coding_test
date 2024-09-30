package bruteforce.KimJunYoung;

import java.util.*;

public class ProgrammersBruteforce6 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int n1 = 9;
        int[][] wires1 = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(solved.solution(n1, wires1) == 3);
    }
    static class Solution {
        public int solution(int n, int[][] wires) {
            int answer = n;
            // 순서대로 한 wire씩 지워가며 답을 찾음
            for (int removingIndex = 0; removingIndex < wires.length; removingIndex++) {
                List<Integer>[] graph = new List[n + 1];
                boolean[] visited = new boolean[n + 1];
                for (int i = 0; i < graph.length; i++) {
                    graph[i] = new ArrayList<>();
                }
                for (int index = 0; index < wires.length; index++) {
                    // 지우고자 하는 wire는 그래프에 포함하지 않음
                    if (index == removingIndex) {
                        continue;
                    }
                    int start = wires[index][0];
                    int end = wires[index][1];
                    graph[start].add(end);
                    graph[end].add(start);
                }
                int tempArea = bfs(visited, graph);
                //잘린 다른 한 쪽 그래프의 넓이 =  전체 넓이 - 임의의 한점에서의 넓이
                int remainArea = n - tempArea;
                answer = Math.min(answer, Math.abs(tempArea - remainArea));
            }

            return answer;
        }

        private int bfs(boolean[] visited, List<Integer>[] graph) {
            Queue<Integer> queue = new ArrayDeque<>();
            // 1번 노드에서 출발했을 떄의 너비만 구하면, 잘린 다른 한 쪽 그래프의 넓이를 알 수 있음
            queue.offer(1);
            visited[1] = true;
            int area = 1;
            while(!queue.isEmpty()) {
                int start = queue.poll();
                List<Integer> endNodes = graph[start];
                for (int node: endNodes) {
                    if (!visited[node]) {
                        queue.offer(node);
                        visited[node] = true;
                        area++;
                    }
                }
            }
            return area;
        }
    }
}
