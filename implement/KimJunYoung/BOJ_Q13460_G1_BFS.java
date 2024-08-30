package implement.KimJunYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
solved by BFS, much better performance than backTracking
 */
public class BOJ_Q13460_G1_BFS {

    private static final char RED = 'R';
    private static final char BLUE = 'B';
    private static final char WALL = '#';
    private static final char HOLE = 'O';
    private static final char SPACE = '.';
    private static int N, M;
    private static boolean[][][][] visited;

    private static class Board {
        char[][] map;
        int redX, redY;
        int blueX, blueY;
        int count;
        boolean isRedHole;
        boolean isBlueHole;

        public Board() {
            map = new char[N][M];
            count = 0;
            isRedHole = false;
            isBlueHole = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        visited = new boolean[N][M][N][M];
        int answer = -1;
        Board board = new Board();
        for (int i = 0; i < N; i++) {
            String row = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                char ch = row.charAt(j);
                if (ch == RED) {
                    board.redX = i;
                    board.redY = j;
                    board.map[i][j] = SPACE;
                    continue;
                }
                if (ch == BLUE) {
                    board.blueX = i;
                    board.blueY = j;
                    board.map[i][j] = SPACE;
                    continue;
                }
                board.map[i][j] = ch;
            }
        }
        Queue<Board> queue = new LinkedList<>();
        queue.offer(board);
        visited[board.redX][board.redY][board.blueX][board.blueY] = true;
        while (!queue.isEmpty()) {
            Board current = queue.poll();
            if (current.isRedHole && !current.isBlueHole) {
                answer = current.count;
                break;
            }
            if (current.isBlueHole) {
                continue;
            }
            if (current.count >= 10) {
                continue;
            }
            Board up = up(current);
            if (!isVisited(up)) {
                visited[up.redX][up.redY][up.blueX][up.blueY] = true;
                queue.offer(up);
            }

            Board down = down(current);
            if (!isVisited(down)) {
                visited[down.redX][down.redY][down.blueX][down.blueY] = true;
                queue.offer(down);
            }

            Board right = right(current);
            if (!isVisited(right)) {
                visited[right.redX][right.redY][right.blueX][right.blueY] = true;
                queue.offer(right);
            }

            Board left = left(current);
            if (!isVisited(left)) {
                visited[left.redX][left.redY][left.blueX][left.blueY] = true;
                queue.offer(left);
            }
        }

        System.out.println(answer);

    }

    private static boolean isVisited(Board board) {
        return visited[board.redX][board.redY][board.blueX][board.blueY];
    }

    private static Board up(Board board) {
        Board temp = new Board();
        temp.redX = board.redX;
        temp.redY = board.redY;
        temp.blueX = board.blueX;
        temp.blueY = board.blueY;
        temp.count = board.count + 1;
        temp.map = board.map;
        if (temp.redX <= temp.blueX) { // red marble first
            for (int x = board.redX; x >= 1; x--) {
                if (temp.map[x - 1][temp.redY] == WALL) {
                    break;
                }
                if (temp.map[x - 1][temp.redY] == HOLE) {
                    temp.redX--;
                    temp.isRedHole = true;
                    break;
                }
                temp.redX--;
            }

            for (int x = board.blueX; x >= 1; x--) {
                if (temp.map[x - 1][temp.blueY] == WALL) {
                    break;
                }
                if ((x - 1 == temp.redX && temp.blueY == temp.redY) && !temp.isRedHole) {
                    break;
                }
                if (temp.map[x - 1][temp.blueY] == HOLE) {
                    temp.blueX--;
                    temp.isBlueHole = true;
                    break;
                }
                temp.blueX--;
            }
            return temp;
        }

        // blue marble first
        for (int x = board.blueX; x >= 1; x--) {
            if (temp.map[x - 1][temp.blueY] == WALL) {
                break;
            }
            if (temp.map[x - 1][temp.blueY] == HOLE) {
                temp.blueX--;
                temp.isBlueHole = true;
                break;
            }
            temp.blueX--;
        }

        for (int x = board.redX; x >= 1; x--) {
            if (temp.map[x - 1][temp.redY] == WALL || (x - 1 == temp.blueX && temp.blueY == temp.redY)) {
                break;
            }
            if (temp.map[x - 1][temp.redY] == HOLE) {
                temp.redX--;
                temp.isRedHole = true;
                break;
            }
            temp.redX--;
        }

        return temp;
    }

    private static Board down(Board board) {
        Board temp = new Board();
        temp.redX = board.redX;
        temp.redY = board.redY;
        temp.blueX = board.blueX;
        temp.blueY = board.blueY;
        temp.count = board.count + 1;
        temp.map = board.map;
        if (temp.redX >= temp.blueX) { // red marble first
            for (int x = board.redX; x < N - 1; x++) {
                if (temp.map[x + 1][temp.redY] == WALL) {
                    break;
                }
                if (temp.map[x + 1][temp.redY] == HOLE) {
                    temp.redX++;
                    temp.isRedHole = true;
                    break;
                }
                temp.redX++;
            }

            for (int x = board.blueX; x < N - 1; x++) {
                if (temp.map[x + 1][temp.blueY] == WALL) {
                    break;
                }
                if ((x + 1 == temp.redX && temp.blueY == temp.redY) && !temp.isRedHole) {
                    break;
                }
                if (temp.map[x + 1][temp.blueY] == HOLE) {
                    temp.blueX++;
                    temp.isBlueHole = true;
                    break;
                }
                temp.blueX++;
            }
            return temp;
        }

        // blue marble first
        for (int x = board.blueX; x < N - 1; x++) {
            if (temp.map[x + 1][temp.blueY] == WALL) {
                break;
            }
            if (temp.map[x + 1][temp.blueY] == HOLE) {
                temp.blueX++;
                temp.isBlueHole = true;
                break;
            }
            temp.blueX++;
        }

        for (int x = board.redX; x < N - 1; x++) {
            if (temp.map[x + 1][temp.redY] == WALL || (x + 1 == temp.blueX && temp.blueY == temp.redY)) {
                break;
            }
            if (temp.map[x + 1][temp.redY] == HOLE) {
                temp.redX++;
                temp.isRedHole = true;
                break;
            }
            temp.redX++;
        }

        return temp;
    }

    private static Board right(Board board) {
        Board temp = new Board();
        temp.redX = board.redX;
        temp.redY = board.redY;
        temp.blueX = board.blueX;
        temp.blueY = board.blueY;
        temp.count = board.count + 1;
        temp.map = board.map;
        if (temp.redY >= temp.blueY) { // red marble first
            for (int y = board.redY; y < M - 1; y++) {
                if (temp.map[temp.redX][y + 1] == WALL) {
                    break;
                }
                if (temp.map[temp.redX][y + 1] == HOLE) {
                    temp.redY++;
                    temp.isRedHole = true;
                    break;
                }
                temp.redY++;
            }

            for (int y = board.blueY; y < M - 1; y++) {
                if (temp.map[temp.blueX][y + 1] == WALL) {
                    break;
                }
                if ((y + 1 == temp.redY && temp.blueX == temp.redX) && !temp.isRedHole) {
                    break;
                }
                if (temp.map[temp.blueX][y + 1] == HOLE) {
                    temp.blueY++;
                    temp.isBlueHole = true;
                    break;
                }
                temp.blueY++;
            }
            return temp;
        }

        // blue marble first
        for (int y = board.blueY; y < M - 1; y++) {
            if (temp.map[temp.blueX][y + 1] == WALL) {
                break;
            }
            if (temp.map[temp.blueX][y + 1] == HOLE) {
                temp.blueY++;
                temp.isBlueHole = true;
                break;
            }
            temp.blueY++;
        }

        for (int y = board.redY; y < M - 1; y++) {
            if (temp.map[temp.redX][y + 1] == WALL || (y + 1 == temp.blueY && temp.blueX == temp.redX)) {
                break;
            }
            if (temp.map[temp.redX][y + 1] == HOLE) {
                temp.redY++;
                temp.isRedHole = true;
                break;
            }
            temp.redY++;
        }

        return temp;
    }

    private static Board left(Board board) {
        Board temp = new Board();
        temp.redX = board.redX;
        temp.redY = board.redY;
        temp.blueX = board.blueX;
        temp.blueY = board.blueY;
        temp.count = board.count + 1;
        temp.map = board.map;
        if (temp.redY <= temp.blueY) { // red marble first
            for (int y = board.redY; y >= 1; y--) {
                if (temp.map[temp.redX][y - 1] == WALL) {
                    break;
                }
                if (temp.map[temp.redX][y - 1] == HOLE) {
                    temp.redY--;
                    temp.isRedHole = true;
                    break;
                }
                temp.redY--;
            }

            for (int y = board.blueY; y >= 1; y--) {
                if (temp.map[temp.blueX][y - 1] == WALL) {
                    break;
                }
                if ((y - 1 == temp.redY && temp.blueX == temp.redX) && !temp.isRedHole) {
                    break;
                }
                if (temp.map[temp.blueX][y - 1] == HOLE) {
                    temp.blueY--;
                    temp.isBlueHole = true;
                    break;
                }
                temp.blueY--;
            }
            return temp;
        }

        // blue marble first
        for (int y = board.blueY; y >= 1; y--) {
            if (temp.map[temp.blueX][y - 1] == WALL) {
                break;
            }
            if (temp.map[temp.blueX][y - 1] == HOLE) {
                temp.blueY--;
                temp.isBlueHole = true;
                break;
            }
            temp.blueY--;
        }

        for (int y = board.redY; y >= 1; y--) {
            if (temp.map[temp.redX][y - 1] == WALL || (y - 1 == temp.blueY && temp.blueX == temp.redX)) {
                break;
            }
            if (temp.map[temp.redX][y - 1] == HOLE) {
                temp.redY--;
                temp.isRedHole = true;
                break;
            }
            temp.redY--;
        }

        return temp;
    }
}