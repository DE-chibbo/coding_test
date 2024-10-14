package implement.KimJunYoung;

import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16985_G2 {
    /*
    백준 문제 16985번: Maaaaaaaze
     */
    private static class Node {
        int x, y, z, distance;

        public Node(int x, int y, int z, int distance) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.distance = distance;
        }
    }

    private static final int[][][] boards = new int[5][5][5];
    private static final int[] layerIndex = new int[5];
    private static final boolean[] visited = new boolean[5];
    private static final Node[] startNodes = {
            new Node(0, 0, 0, 0),
            new Node(0, 4, 0, 0),
            new Node(4, 0, 0, 0),
            new Node(4, 4, 0, 0)
    };
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int k = 0; k < 5; k++) {
                    boards[i][j][k] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
        }
        int[][][] map = new int[5][5][5];
        makeMaze(0, map);
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);

    }

    private static void makeMaze(int layer, int[][][] map) {
        if (layer == 5) {
            pickLayer(0, map);
            return;
        }
        for (int i = 0; i < 4; i++) {
            rotate(0, i, layer, map);
            makeMaze(layer + 1, map);
        }
    }

    private static void rotate(int depth, int targetTime, int layer, int[][][] map) {
        if (depth == 0) {
            for (int i = 0; i < 5; i++) {
                System.arraycopy(boards[layer][i], 0, map[layer][i], 0, 5);
            }
        }
        if (depth == targetTime) {
            return;
        }
        int[][] temp = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                temp[j][4 - i] = map[layer][i][j];
            }
        }
        map[layer] = temp;
        rotate(depth + 1, targetTime, layer, map);
    }

    private static void pickLayer(int depth, int[][][] map) {
        if (depth == 5) {
            int[][][] temp = new int[5][5][5];
            for (int i = 0; i < 5; i++) {
                temp[i] = map[layerIndex[i]];
            }
            for (Node start :
                    startNodes) {
                Node end = new Node(4 - start.x, 4 - start.y, 4 - start.z, 0);
                bfs(start, end, temp);
            }
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (!visited[i]) {
                visited[i] = true;
                layerIndex[depth] = i;
                pickLayer(depth + 1, map);
                visited[i] = false;
            }
        }
    }

    private static void bfs(Node startNode, Node endNode, int[][][] map) {
        if (map[startNode.z][startNode.x][startNode.y] == 0 || map[endNode.z][endNode.x][endNode.y] == 0) {
            return;
        }
        int[] dx = {-1, 1, 0, 0, 0, 0};
        int[] dy = {0, 0, -1, 1, 0, 0};
        int[] dz = {0, 0, 0, 0, -1, 1};

        Queue<Node> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[5][5][5];
        queue.offer(startNode);
        visited[startNode.z][startNode.x][startNode.y] = true;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.x == endNode.x && current.y == endNode.y && current.z == endNode.z) {
                if (current.distance == 12) {
                    System.out.println(12);
                    exit(0);
                }
                result = Math.min(result, current.distance);
            }
            for (int i = 0; i < 6; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                int nz = current.z + dz[i];
                if (!canMove(nx, ny, nz)) {
                    continue;
                }
                if (!visited[nz][nx][ny] && map[nz][nx][ny] == 1) {
                    visited[nz][nx][ny] = true;
                    queue.offer(new Node(nx, ny, nz, current.distance + 1));
                }
            }
        }
    }

    private static boolean canMove(int nx, int ny, int nz) {
        return !(nx < 0 || ny < 0 || nz < 0 || nx >= 5 || ny >= 5 || nz >= 5);
    }
}

