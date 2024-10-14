package implement.KimJunYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_G4 {
    /*
    백준 문제 3190번: 뱀
     */
    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;
    private static final char RIGHT = 'D';
    private static final char LEFT = 'L';
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static class Command {
        int time;
        char direction;

        public Command(int time, char direction) {
            this.time = time;
            this.direction = direction;
        }
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Snake {
        LinkedList<Node> body = new LinkedList<>();
        int direction;
        boolean isAlive;

        public Snake(int x, int y) {
            body.add(new Node(x, y));
            board[x][y] = 4;
            direction = EAST;
            isAlive = true;
        }

        public void moveForward() {
            int nx = body.getLast().x + dx[direction];
            int ny = body.getLast().y + dy[direction];
            if (!canMove(nx, ny)) {
                isAlive = false;
                return;
            }
            body.add(new Node(nx, ny));
            if (board[nx][ny] == 1) {
                board[nx][ny] = 4;
                return;
            }
            board[nx][ny] = 4;
            board[body.getFirst().x][body.getFirst().y] = 0;
            body.removeFirst();
        }

        public void rotate(char direction) {
            if (direction == RIGHT) {
                this.direction++;
            } else if (direction == LEFT) {
                this.direction--;
            }

            if (this.direction > 3) {
                this.direction = NORTH;
            } else if (this.direction < 0) {
                this.direction = WEST;
            }
        }

        private boolean canMove(int nx, int ny) {
            // snake = 4
            return !(nx <= 0 || ny <= 0 || nx > N || ny > N || board[nx][ny] == 4);
        }
    }

    private static int N;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        board = new int[N + 1][N + 1];
        int K = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            board[x][y] = 1; // apple = 1
        }

        int L = Integer.parseInt(bufferedReader.readLine());
        Queue<Command> commands = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int time = Integer.parseInt(stringTokenizer.nextToken());
            char direction = stringTokenizer.nextToken().charAt(0);
            commands.offer(new Command(time, direction));
        }

        int time = 0;
        Snake snake = new Snake(1, 1);
        while (snake.isAlive) {
            if (!commands.isEmpty() && commands.peek().time == time) {
                Command command = commands.poll();
                snake.rotate(command.direction);
            }
            snake.moveForward();
            time++;
        }
        System.out.println(time);
    }
}

