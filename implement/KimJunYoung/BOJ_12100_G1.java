package implement.KimJunYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12100_G1 {
    /*
    백준 문제 12100번: 2048(easy)
     */
    private static int N, result;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        up(0, map);
        right(0, map);
        left(0, map);
        down(0, map);

        System.out.println(result);
    }

    private static void up(int depth, int[][] map) {
        if (depth == 5) {
            result = Math.max(result, countMax(map));
            return;
        }
        int[][] temp = new int[N][N];
        for (int y = 0; y < N; y++) {
            int idx = 0;
            for (int x = 0; x < N; x++) {
                if (map[x][y] == 0) {
                    continue;
                }
                if (temp[idx][y] == 0) {
                    temp[idx][y] = map[x][y];
                    continue;
                }
                if (temp[idx][y] == map[x][y]) {
                    temp[idx++][y] *= 2;
                    continue;
                }
                temp[++idx][y] = map[x][y];
            }
        }

        up(depth + 1, temp);
        right(depth + 1, temp);
        left(depth + 1, temp);
        down(depth + 1, temp);
    }

    private static void down(int depth, int[][] map) {
        if (depth == 5) {
            result = Math.max(result, countMax(map));
            return;
        }
        int[][] temp = new int[N][N];
        for (int y = 0; y < N; y++) {
            int idx = N - 1;
            for (int x = N - 1; x >= 0; x--) {
                if (map[x][y] == 0) {
                    continue;
                }
                if (temp[idx][y] == 0) {
                    temp[idx][y] = map[x][y];
                    continue;
                }
                if (temp[idx][y] == map[x][y]) {
                    temp[idx--][y] *= 2;
                    continue;
                }
                temp[--idx][y] = map[x][y];
            }
        }

        up(depth + 1, temp);
        right(depth + 1, temp);
        left(depth + 1, temp);
        down(depth + 1, temp);
    }

    private static void left(int depth, int[][] map) {
        if (depth == 5) {
            result = Math.max(result, countMax(map));
            return;
        }
        int[][] temp = new int[N][N];
        for (int x = 0; x < N; x++) {
            int idx = 0;
            for (int y = 0; y < N; y++) {
                if (map[x][y] == 0) {
                    continue;
                }
                if (temp[x][idx] == 0) {
                    temp[x][idx] = map[x][y];
                    continue;
                }
                if (temp[x][idx] == map[x][y]) {
                    temp[x][idx++] *= 2;
                    continue;
                }
                temp[x][++idx] = map[x][y];
            }
        }

        up(depth + 1, temp);
        right(depth + 1, temp);
        left(depth + 1, temp);
        down(depth + 1, temp);
    }

    private static void right(int depth, int[][] map) {
        if (depth == 5) {
            result = Math.max(result, countMax(map));
            return;
        }
        int[][] temp = new int[N][N];
        for (int x = 0; x < N; x++) {
            int idx = N - 1;
            for (int y = N - 1; y >= 0; y--) {
                if (map[x][y] == 0) {
                    continue;
                }
                if (temp[x][idx] == 0) {
                    temp[x][idx] = map[x][y];
                    continue;
                }
                if (temp[x][idx] == map[x][y]) {
                    temp[x][idx--] *= 2;
                    continue;
                }
                temp[x][--idx] = map[x][y];
            }
        }

        up(depth + 1, temp);
        right(depth + 1, temp);
        left(depth + 1, temp);
        down(depth + 1, temp);
    }

    private static int countMax(int[][] map) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }
}


