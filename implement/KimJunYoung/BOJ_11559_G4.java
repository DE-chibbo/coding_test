package implement.KimJunYoung;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11559_G4 {
    /*
    백준 문제 11559번: Puyo Puyo
     */
    private static final char EMPTY = '.';
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String row = bufferedReader.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = row.charAt(j);
            }
        }
        int result = 0;
        boolean isPopped = false;
        while (true) {
            for (int x = 11; x >= 0; x--) {
                for (int y = 0; y < 6; y++) {
                    if (map[x][y] != EMPTY && isPopArea(x, y)) {
                        popArea(x, y);
                        isPopped = true;
                    }
                }
            }
            if (isPopped) {
                for (int i = 0; i < 6; i++) {
                    gravity(i);
                    isPopped = false;
                }
                result++;
            } else {
                break;
            }
        }
        System.out.println(result);


    }

    private static boolean isPopArea(int startX, int startY) {
        int count = 1;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        char currentColor = map[startX][startY];
        boolean[][] visited = new boolean[12][6];
        Queue<int[]> queue = new LinkedList<>();
        visited[startX][startY] = true;
        queue.offer(new int[]{startX, startY});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            if (count >= 4) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) {
                    continue;
                }
                if (!visited[nx][ny] && map[nx][ny] == currentColor) {
                    visited[nx][ny] = true;
                    count++;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return false;
    }

    private static void popArea(int startX, int startY) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        char currentColor = map[startX][startY];
        boolean[][] visited = new boolean[12][6];
        Queue<int[]> queue = new LinkedList<>();
        visited[startX][startY] = true;
        map[startX][startY] = EMPTY;
        queue.offer(new int[]{startX, startY});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) {
                    continue;
                }
                if (!visited[nx][ny] && map[nx][ny] == currentColor) {
                    visited[nx][ny] = true;
                    map[nx][ny] = EMPTY;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    private static void gravity(int y) {
        int index = 11;
        for (int x = 11; x >= 0; x--) {
            if (map[x][y] == EMPTY) {
                continue;
            }
            map[index][y] = map[x][y];
            if (index != x) {
                map[x][y] = EMPTY;
            }
            index--;
        }

    }
}

