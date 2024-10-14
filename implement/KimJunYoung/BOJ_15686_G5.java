package implement.KimJunYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686_G5 {
    /*
    백준 문제 15686번: 치킨배달
     */

    private static int N, M, result;
    private static List<int[]> houses;
    private static List<int[]> chickens;
    private static List<int[]> notClosed;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                int e = Integer.parseInt(stringTokenizer.nextToken());
                if (e == 1) {
                    houses.add(new int[]{i, j});
                    continue;
                }
                if (e == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }
        notClosed = new ArrayList<>();
        pickChicken(0, 0);
        System.out.println(result);
    }

    private static void pickChicken(int depth, int start) {
        if (depth == M) {
            checkDistance();
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            int[] chicken = chickens.get(i);
            int x = chicken[0];
            int y = chicken[1];
            notClosed.add(new int[]{x, y});
            pickChicken(depth + 1, i + 1);
            notClosed.remove(notClosed.size() - 1);
        }
    }

    private static void checkDistance() {
        int sum = 0;
        for (int[] house : houses) {
            int distance = Integer.MAX_VALUE;
            int hx = house[0];
            int hy = house[1];
            for (int[] chicken : notClosed) {
                int cx = chicken[0];
                int cy = chicken[1];
                distance = Math.min(
                        distance,
                        Math.abs(hx - cx) + Math.abs(hy - cy)
                );
            }
            sum += distance;
        }
        result = Math.min(result, sum);
    }
}

