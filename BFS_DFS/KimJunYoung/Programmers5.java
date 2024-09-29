package BFS_DFS.KimJunYoung;

import java.util.*;

public class Programmers5 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int[][] rectangle1 = {{1,1,5,7}};
        System.out.println(solved.solution(rectangle1, 1, 1, 4, 7));

        int[][] rectangle2 = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        System.out.println(solved.solution(rectangle2, 1, 3, 7, 8));

    }

    static class Solution {
        static class Node {
            int x, y, distance;

            public Node(int x, int y, int distance) {
                this.x = x;
                this.y = y;
                this.distance = distance;
            }
        }
        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY)    {
            int[][] map = new int[101][101];
            for (int[] square: rectangle) {
                drawSquare(square, map);
            }

            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, 1, 0, -1};
            boolean[][] visited = new boolean[101][101];
            Queue<Node> queue = new ArrayDeque<>();

            queue.offer(new Node(characterX * 2, characterY * 2, 0));
            visited[characterX * 2][characterY * 2] = true;

            while(!queue.isEmpty()) {
                Node cur = queue.poll();
                if (cur.x == itemX * 2 && cur.y == itemY * 2) {
                    return cur.distance / 2;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (isOutOfMap(nx, ny)) {
                        continue;
                    }
                    if (!visited[nx][ny] && map[nx][ny] == 1) {
                        queue.offer(new Node(nx, ny, cur.distance + 1));
                        visited[nx][ny] = true;
                    }
                }
            }

            return 0;
        }

        private void drawSquare(int[] square, int[][] map) {
            // square = [좌측 하단 x, 좌측 하단 y, 우측 상단 x, 우측 상단 y]
            int lx = square[0] * 2;
            int ly = square[1] * 2;
            int rx = square[2] * 2;
            int ry = square[3] * 2;

            for (int x = lx; x <= rx; x++) {
                for (int y = ly; y <= ry; y++) {
                    // 테두리인 경우
                    if (x == lx || y == ly || x == rx || y == ry) {
                        // 테두리는 이미 그려지지 않은 지점만 그림
                        if (map[x][y] == 0) {
                            map[x][y] = 1;
                        }
                        continue;
                    }
                    // 사각형 내부는 무조건 2로 채워줌
                    map[x][y] = 2;
                }
            }
        }

        private boolean isOutOfMap(int x, int y) {
            return x < 0 || y < 0 || x > 100 || y > 100;
        }
    }
}
