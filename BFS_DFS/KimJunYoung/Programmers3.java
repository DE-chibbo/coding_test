package BFS_DFS.KimJunYoung;

import java.util.*;

public class Programmers3 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int[][] maps1 = {{1, 1, 0}, {1, 0, 0}, {1, 1, 1}};
        System.out.println(solved.solution(maps1) == 5);

        int[][] maps2 = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}};
        System.out.println(solved.solution(maps2) == -1);
    }

    static class Solution {
        public int solution(int[][] maps) {
            int n = maps.length;
            int m = maps[0].length;
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, 1, 0, -1};

            boolean[][] visited = new boolean[n][m];
            Queue<Node> queue = new ArrayDeque<>();
            queue.offer(new Node(0, 0, 1));
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                if (cur.x == n - 1 && cur.y == m - 1) {
                    return cur.distance;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        continue;
                    }

                    if (!visited[nx][ny] && maps[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        queue.offer(new Node(nx, ny, cur.distance + 1));
                    }
                }
            }

            return -1;
        }
        class Node {
            int x, y, distance;

            public Node(int x, int y, int distance) {
                this.x = x;
                this.y = y;
                this.distance = distance;
            }
        }
    }
}
