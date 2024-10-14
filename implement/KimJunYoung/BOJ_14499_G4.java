package implement.KimJunYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_G4 {
    /*
    백준 문제 14499번: 주사위 굴리기
     */
    private static class Dice {
        int top, bottom, front, back, right, left;
        int x, y;

        public Dice(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // for test, not used in solution
        public Dice(int top, int bottom, int front, int back, int right, int left) {
            this.top = top;
            this.bottom = bottom;
            this.front = front;
            this.back = back;
            this.right = right;
            this.left = left;
        }

        public void roll(int command) {
            switch (command) {
                case 1:
                    toEast();
                    break;
                case 2:
                    toWest();
                    break;
                case 3:
                    toNorth();
                    break;
                case 4:
                    toSouth();
                    break;
            }
        }

        private void toNorth() {
            int temp = top;
            top = front;
            front = bottom;
            bottom = back;
            back = temp;
        }

        private void toSouth() {
            int temp = top;
            top = back;
            back = bottom;
            bottom = front;
            front = temp;
        }

        private void toEast() {
            int temp = top;
            top = left;
            left = bottom;
            bottom = right;
            right = temp;
        }

        private void toWest() {
            int temp = top;
            top = right;
            right = bottom;
            bottom = left;
            left = temp;
        }

    }

    private static final int[] dx = {0, 0, 0, -1, 1};
    private static final int[] dy = {0, 1, -1, 0, 0};
    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[N][M];
        int x = Integer.parseInt(stringTokenizer.nextToken());
        int y = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        Dice dice = new Dice(x, y);
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int command = Integer.parseInt(stringTokenizer.nextToken());
            int nx = dice.x + dx[command];
            int ny = dice.y + dy[command];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }
            dice.x = nx;
            dice.y = ny;
            dice.roll(command);
            if (map[nx][ny] == 0) {
                map[nx][ny] = dice.bottom;
                stringBuilder.append(dice.top).append(System.lineSeparator());
                continue;
            }
            dice.bottom = map[nx][ny];
            map[nx][ny] = 0;
            stringBuilder.append(dice.top).append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }
}

