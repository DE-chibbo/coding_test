package implement.KimJunYoung;

import java.io.*;
import java.util.*;

public class BOJ_15683_G3 {
    private static class Cam {
        int x, y, type;
        public Cam(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        List<Cam> cams = new ArrayList<>();
        answer = N * M;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cams.add(new Cam(i, j, map[i][j]));
                }
            }
        }
        search(0, map, cams);
        System.out.println(answer);

    }

    private static void search(int depth, int[][] map, List<Cam> cams) {
        if (depth == cams.size()) {
            int count = 0;
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[0].length; y++) {
                    if (map[x][y] == 0) {
                        count++;
                    }
                }
            }
            answer = Math.min(answer, count);
            return;
        }
        Cam curCam = cams.get(depth);
        int type = curCam.type;
        if (type == 1) {
            setCam1(depth, map, cams, curCam);
        } else if (type == 2) {
            setCam2(depth, map, cams, curCam);
        } else if (type == 3) {
            setCam3(depth, map, cams, curCam);
        } else if (type == 4) {
            setCam4(depth, map, cams, curCam);
        } else if (type == 5) {
            setCam5(depth, map, cams, curCam);
        }
    }

    private static void setCam1(int depth, int[][] map, List<Cam> cams, Cam cam) {
        int x = cam.x;
        int y = cam.y;

        fillUp(map, x, y);
        search(depth + 1, map, cams);
        rollBackUp(map, x, y);

        fillRight(map, x, y);
        search(depth + 1, map, cams);
        rollBackRight(map, x, y);

        fillDown(map, x, y);
        search(depth + 1, map, cams);
        rollBackDown(map, x, y);

        fillLeft(map, x, y);
        search(depth + 1, map, cams);
        rollBackLeft(map, x, y);
    }

    private static void setCam2(int depth, int[][] map, List<Cam> cams, Cam cam) {
        int x = cam.x;
        int y = cam.y;

        fillUp(map, x, y);
        fillDown(map, x, y);
        search(depth + 1, map, cams);
        rollBackUp(map, x, y);
        rollBackDown(map, x, y);

        fillRight(map, x, y);
        fillLeft(map, x, y);
        search(depth + 1, map, cams);
        rollBackRight(map, x, y);
        rollBackLeft(map, x, y);
    }

    private static void setCam3(int depth, int[][] map, List<Cam> cams, Cam cam) {
        int x = cam.x;
        int y = cam.y;

        fillUp(map, x, y);
        fillRight(map, x, y);
        search(depth + 1, map, cams);
        rollBackUp(map, x, y);
        rollBackRight(map, x, y);

        fillRight(map, x, y);
        fillDown(map, x, y);
        search(depth + 1, map, cams);
        rollBackRight(map, x, y);
        rollBackDown(map, x, y);

        fillDown(map, x, y);
        fillLeft(map, x, y);
        search(depth + 1, map, cams);
        rollBackDown(map, x, y);
        rollBackLeft(map, x, y);

        fillLeft(map, x, y);
        fillUp(map, x, y);
        search(depth + 1, map, cams);
        rollBackLeft(map, x, y);
        rollBackUp(map, x, y);
    }

    private static void setCam4(int depth, int[][] map, List<Cam> cams, Cam cam) {
        int x = cam.x;
        int y = cam.y;

        fillLeft(map, x, y);
        fillUp(map, x, y);
        fillRight(map, x, y);
        search(depth + 1, map, cams);
        rollBackLeft(map, x, y);
        rollBackUp(map, x, y);
        rollBackRight(map, x, y);

        fillUp(map, x, y);
        fillRight(map, x, y);
        fillDown(map, x, y);
        search(depth + 1, map, cams);
        rollBackRight(map, x, y);
        rollBackUp(map, x, y);
        rollBackDown(map, x, y);

        fillRight(map, x, y);
        fillDown(map, x, y);
        fillLeft(map, x, y);
        search(depth + 1, map, cams);
        rollBackRight(map, x, y);
        rollBackDown(map, x, y);
        rollBackLeft(map, x, y);

        fillDown(map, x, y);
        fillLeft(map, x, y);
        fillUp(map, x, y);
        search(depth + 1, map, cams);
        rollBackDown(map, x, y);
        rollBackLeft(map, x, y);
        rollBackUp(map, x, y);
    }

    private static void setCam5(int depth, int[][] map, List<Cam> cams, Cam cam) {
        int x = cam.x;
        int y = cam.y;

        fillUp(map, x, y);
        fillRight(map, x, y);
        fillDown(map, x, y);
        fillLeft(map, x, y);
        search(depth + 1, map, cams);
        rollBackUp(map, x, y);
        rollBackRight(map, x, y);
        rollBackDown(map, x, y);
        rollBackLeft(map, x, y);
    }

    private static void fillUp(int[][] map, int startX, int startY) {
        int N = map.length;
        if (startX >= N) return;

        for (int x = startX - 1; x > -1; x--) {
            if (map[x][startY] == 6) {
                break;
            }
            if (map[x][startY] <= 0) {
                map[x][startY]--;
            }
        }
    }

    private static void rollBackUp(int[][] map, int startX, int startY) {
        int N = map.length;
        if (startX >= N) return;

        for (int x = startX - 1; x > -1; x--) {
            if (map[x][startY] == 6) {
                break;
            }
            if (map[x][startY] <= 0) {
                map[x][startY]++;
            }
        }
    }


    private static void fillDown(int[][] map, int startX, int startY) {
        int N = map.length;
        for (int x = startX + 1; x < N; x++) {
            if (map[x][startY] == 6) {
                break;
            }
            if (map[x][startY] <= 0) {
                map[x][startY]--;
            }
        }
    }

    private static void rollBackDown(int[][] map, int startX, int startY) {
        int N = map.length;
        for (int x = startX + 1; x < N; x++) {
            if (map[x][startY] == 6) {
                break;
            }
            if (map[x][startY] <= 0) {
                map[x][startY]++;
            }
        }
    }

    private static void fillRight(int[][] map, int startX, int startY) {
        int M = map[0].length;
        for (int y = startY + 1; y < M; y++) {
            if (map[startX][y] == 6) {
                break;
            }
            if (map[startX][y] <= 0) {
                map[startX][y]--;
            }
        }
    }

    private static void rollBackRight(int[][] map, int startX, int startY) {
        int M = map[0].length;
        for (int y = startY + 1; y < M; y++) {
            if (map[startX][y] == 6) {
                break;
            }
            if (map[startX][y] <= 0) {
                map[startX][y]++;
            }
        }
    }

    private static void fillLeft(int[][] map, int startX, int startY) {
        int M = map[0].length;
        if (startY >= M) return;

        for (int y = startY - 1; y > -1; y--) {
            if (map[startX][y] == 6) {
                break;
            }
            if (map[startX][y] <= 0) {
                map[startX][y]--;
            }
        }
    }

    private static void rollBackLeft(int[][] map, int startX, int startY) {
        int M = map[0].length;
        if (startY >= M) return;

        for (int y = startY - 1; y > -1; y--) {
            if (map[startX][y] == 6) {
                break;
            }
            if (map[startX][y] <= 0) {
                map[startX][y]++;
            }
        }
    }
}
