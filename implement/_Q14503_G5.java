package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _Q14503_G5 {
    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static int N, M, result;
    private static int[][] map;

    private static class Cleaner {
        int x, y;
        int direction;
        boolean isOn = true;

        public Cleaner(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public void clean() {
            if (map[x][y] == 0) {
                map[x][y] = 2;
                result++;
            }
        }

        public void moveBack() {
            int nx = x + (dx[direction] * -1);
            int ny = y + (dy[direction] * -1);
            if (canMove(nx, ny) && map[nx][ny] != 1) {
                x = nx;
                y = ny;
                return;
            }
            isOn = false;
        }

        public void rotate() {
            direction--;
            if (direction < 0) {
                direction = WEST;
            }
        }

        public void moveForward() {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if (canMove(nx, ny) && map[nx][ny] == 0) {
                x = nx;
                y = ny;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[N][M];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        int c = Integer.parseInt(stringTokenizer.nextToken());
        int d = Integer.parseInt(stringTokenizer.nextToken());
        Cleaner cleaner = new Cleaner(r, c, d);
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        while (cleaner.isOn) {
            int x = cleaner.x;
            int y = cleaner.y;
            cleaner.clean();
            boolean canClean = false;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!canMove(nx, ny)) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    canClean = true;
                    cleaner.rotate();
                    cleaner.moveForward();
                    break;
                }
            }
            if (!canClean) {
                cleaner.moveBack();
            }
        }

        System.out.println(result);
    }

    private static boolean canMove(int nx, int ny) {
        return !(nx < 0 || ny < 0 || nx >= N || ny >= M);
    }
}
