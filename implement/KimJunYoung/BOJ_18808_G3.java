package implement.KimJunYoung;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18808_G3 {
    /*
    백준 문제 18808번: 스티커 붙이기
     */
    private static int M, N, result;
    private static int[][] laptop;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        laptop = new int[N][M];
        int K = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int R = Integer.parseInt(stringTokenizer.nextToken());
            int C = Integer.parseInt(stringTokenizer.nextToken());
            int[][] sticker = new int[R][C];
            for (int j = 0; j < R; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int k = 0; k < C; k++) {
                    sticker[j][k] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            int dir = 0;
            boolean isSticked = false;
            while (dir < 4) { // 4. if can't stick in all way, then drop current sticker
                // 1. from 0,0 to N-1, M-1 check is possible to stick
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        if (isPossible(j, k, sticker)) { // 2. if it is possible, stick on laptop
                            stickTo(j, k, sticker);
                            isSticked = true;
                            break;
                        }
                    }
                    if (isSticked) {
                        break;
                    }
                }
                if (isSticked) {
                    break;
                }
                // 3. rotate sticker and go to step 1.
                sticker = rotate(sticker);
                dir++;
            }

        }
        // 5. result
        System.out.println(result);

    }

    private static boolean isPossible(int x, int y, int[][] sticker) {
        if (sticker.length > N || sticker[0].length > M) {
            return false;
        }
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (i + x >= N || j + y >= M) {
                    return false;
                }
                if (sticker[i][j] == 1 && laptop[i + x][j + y] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void stickTo(int x, int y, int[][] sticker) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 1) {
                    laptop[i + x][j + y] = sticker[i][j];
                    result++; // count attached area
                }
            }
        }
    }

    private static int[][] rotate(int[][] sticker) {
        int[][] rotated = new int[sticker[0].length][sticker.length];
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                rotated[j][sticker.length - 1 - i] = sticker[i][j];
            }
        }
        return rotated;
    }
}

