package implement.KimJunYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
solved by backTracking
 */
public class BOJ_Q13460_G1 {
    private static final char RED = 'R';
    private static final char BLUE = 'B';
    private static final char WALL = '#';
    private static final char HOLE = 'O';
    private static final char SPACE = '.';
    private static int N, M, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        char[][] map = new char[N][M];
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            String row = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
            }
        }
        up(0, map);
        down(0, map);
        right(0, map);
        left(0, map);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer + 1);
    }

    private static void up(int depth, char[][] map) {
        if (depth == 10) {
            return;
        }
        char[][] temp = new char[N][M];
        for (int y = 1; y < M - 1; y++) {
            boolean isRedIn = false;
            for (int x = 1; x < N - 1; x++) {
                temp[x][y] = map[x][y];
                if (temp[x][y] != RED && temp[x][y] != BLUE) {
                    continue;
                }
                for (int i = x - 1; i >= 1; i--) {
                    if (temp[i][y] == HOLE && map[x][y] == RED) {
                        temp[i + 1][y] = SPACE;
                        isRedIn = true;
                        break;
                    }
                    // if blue marble is in hole, stop search and return, even if red marble is already in hole.
                    if (temp[i][y] == HOLE && map[x][y] == BLUE) {
                        temp[i + 1][y] = SPACE;
                        return;
                    }
                    if (temp[i][y] != SPACE) {
                        break;
                    }
                    temp[i][y] = map[x][y];
                    temp[i + 1][y] = SPACE;
                }
            }
            if (isRedIn) { // Red marble in hole and blue marble not in hole.
                answer = Math.min(answer, depth);
                return;
            }
        }
        up(depth + 1, temp);
        down(depth + 1, temp);
        right(depth + 1, temp);
        left(depth + 1, temp);
    }

    private static void down(int depth, char[][] map) {
        if (depth == 10) {
            return;
        }
        char[][] temp = new char[N][M];
        for (int y = 1; y < M - 1; y++) {
            boolean isRedIn = false;
            for (int x = N - 2; x >= 1; x--) {
                temp[x][y] = map[x][y];
                if (temp[x][y] != RED && temp[x][y] != BLUE) {
                    continue;
                }
                for (int i = x + 1; i < N - 1; i++) {
                    if (temp[i][y] == HOLE && map[x][y] == RED) {
                        temp[i - 1][y] = SPACE;
                        isRedIn = true;
                        break;
                    }
                    if (temp[i][y] == HOLE && map[x][y] == BLUE) {
                        temp[i - 1][y] = SPACE;
                        return;
                    }
                    if (temp[i][y] != SPACE) {
                        break;
                    }
                    temp[i][y] = map[x][y];
                    temp[i - 1][y] = SPACE;
                }
            }
            if (isRedIn) {
                answer = Math.min(answer, depth);
                return;
            }
        }
        up(depth + 1, temp);
        down(depth + 1, temp);
        right(depth + 1, temp);
        left(depth + 1, temp);
    }

    private static void right(int depth, char[][] map) {
        if (depth == 10) {
            return;
        }
        char[][] temp = new char[N][M];
        for (int x = 1; x < N - 1; x++) {
            boolean isRedIn = false;
            for (int y = M - 2; y >= 1; y--) {
                temp[x][y] = map[x][y];
                if (temp[x][y] != RED && temp[x][y] != BLUE) {
                    continue;
                }
                for (int i = y + 1; i < M - 1; i++) {
                    if (temp[x][i] == HOLE && map[x][y] == RED) {
                        temp[x][i - 1] = SPACE;
                        isRedIn = true;
                        break;
                    }
                    if (temp[x][i] == HOLE && map[x][y] == BLUE) {
                        temp[x][i - 1] = SPACE;
                        return;
                    }
                    if (temp[x][i] != SPACE) {
                        break;
                    }
                    temp[x][i] = map[x][y];
                    temp[x][i - 1] = SPACE;
                }
            }
            if (isRedIn) {
                answer = Math.min(answer, depth);
                return;
            }
        }
        up(depth + 1, temp);
        down(depth + 1, temp);
        right(depth + 1, temp);
        left(depth + 1, temp);
    }

    private static void left(int depth, char[][] map) {
        if (depth == 10) {
            return;
        }
        char[][] temp = new char[N][M];
        for (int x = 1; x < N - 1; x++) {
            boolean isRedIn = false;
            for (int y = 1; y < M - 1; y++) {
                temp[x][y] = map[x][y];
                if (temp[x][y] != RED && temp[x][y] != BLUE) {
                    continue;
                }
                for (int i = y - 1; i >= 1; i--) {
                    if (temp[x][i] == HOLE && map[x][y] == RED) {
                        temp[x][i + 1] = SPACE;
                        isRedIn = true;
                        break;
                    }
                    if (temp[x][i] == HOLE && map[x][y] == BLUE) {
                        temp[x][i + 1] = SPACE;
                        return;
                    }
                    if (temp[x][i] != SPACE) {
                        break;
                    }
                    temp[x][i] = map[x][y];
                    temp[x][i + 1] = SPACE;
                }
            }
            if (isRedIn) {
                answer = Math.min(answer, depth);
                return;
            }
        }
        up(depth + 1, temp);
        down(depth + 1, temp);
        right(depth + 1, temp);
        left(depth + 1, temp);
    }
}
