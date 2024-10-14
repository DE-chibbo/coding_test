package implement.KimJunYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SamsungLastHalfOf2024First {
    private static class Medusa {
        int x, y;
        boolean isArrived;

        public Medusa(int x, int y) {
            this.x = x;
            this.y = y;
            isArrived = false;
        }

        public void move(int[][] distanceMap) {
            int direction = getDirection(distanceMap);
            x += dx[direction];
            y += dy[direction];
            if (warriorMap[x][y] > 0) {
                killWarrior();
            }
            if (x == arrX && y == arrY) {
                isArrived = true;
            }
        }

        private int getDirection(int[][] distanceMap) {
            /*
            상하좌우 칸 중, 도착지로 제일 가까워질 수 있는 거리로 이동,
            만일 여러개의 경우가 있다면 상하좌우 우선순위로 이동
            */
            // 0, 1, 2, 3 : 상, 하, 좌, 우
            // {방향, 도착지까지의 거리}
            // 도착지까지의 거리 오름차순 정렬 -> 방향 상하좌우 순 오름차순 정렬
            PriorityQueue<int[]> directions = new PriorityQueue<>(
                    ((o1, o2) -> {
                        if (o1[1] == o2[1]) {
                            return o1[0] - o2[0];
                        }
                        return o1[1] - o2[1];
                    })
            );

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isOutOfRange(nx, ny)) {
                    continue;
                }
                if (distanceMap[nx][ny] == 1_000_000_000) { // 도로가 아닌 경우
                    continue;
                }
                directions.offer(new int[]{i, distanceMap[nx][ny]});
            }

            return directions.poll()[0];
        }

        public int seeWarriors() {
            Sight pickedSight = pickSightCase();
            sightMap = pickedSight.map;
            bindWarrior();
            return pickedSight.warriorCount;
        }

        private Sight pickSightCase() {
            /*
            메두사가 돌로 만들 수 있는 전사가 가장 많을 수록,
            동일한 경우들은 상하좌우 우선 순위로 케이스를 따짐
             */
            PriorityQueue<Sight> sightCases = new PriorityQueue<>(
                    ((o1, o2) -> {
                        if (o2.warriorCount == o1.warriorCount) {
                            return o1.direction - o2.direction;
                        }
                        return o2.warriorCount - o1.warriorCount;
                    })
            );
            sightCases.offer(seeUp());
            sightCases.offer(seeDown());
            sightCases.offer(seeLeft());
            sightCases.offer(seeRight());

            return sightCases.poll();
        }

        private Sight seeUp() {
            int[][] sightCaseMap = new int[N][N];
            int warriorCount = 0;
            int stage = 1;
            for (int x = this.x - 1; x > -1; x--) {
                for (int y = this.y - stage; y <= this.y + stage; y++) {
                    if (isOutOfRange(x, y)) {
                        continue;
                    }
                    // 이미 메두사의 시야로 표시했거나, 그림자인 경우에는 넘어감
                    if (sightCaseMap[x][y] == 1 || sightCaseMap[x][y] == -1) {
                        continue;
                    }

                    if (warriorMap[x][y] > 0 && sightCaseMap[x][y] == 0) { // 메두사의 시야에 전사가 있는 경우
                        warriorCount += warriorMap[x][y]; // 돌로 굳은 전사 수 세고
                        sightCaseMap[x][y] = 1; // 해당 지점은 메두사의 시야라는걸 표시
                        fillShadeUp(sightCaseMap, x, y); // 해당 전사를 기준으로 뒤에 그림자를 생성
                    } else if (sightCaseMap[x][y] == 0) { // 메두사의 시야에 닿는 빈공간인 경우
                        sightCaseMap[x][y] = 1; // 메두사의 시야라는 걸 표시
                    }
                }
                stage++;
            }
            // 메두사가 위를 바라본 경우의 행렬, 방향 인덱스, 석화된 전사의 수
            return new Sight(sightCaseMap, 0, warriorCount);
        }

        private Sight seeDown() {
            int[][] sightCaseMap = new int[N][N];
            int warriorCount = 0;
            int stage = 1;
            for (int x = this.x + 1; x < N; x++) {
                for (int y = this.y - stage; y <= this.y + stage; y++) {
                    if (isOutOfRange(x, y)) {
                        continue;
                    }
                    // 이미 메두사의 시야로 표시했거나, 그림자인 경우에는 넘어감
                    if (sightCaseMap[x][y] == 1 || sightCaseMap[x][y] == -1) {
                        continue;
                    }

                    if (warriorMap[x][y] > 0 && sightCaseMap[x][y] == 0) { // 메두사의 시야에 전사가 있는 경우
                        warriorCount += warriorMap[x][y]; // 돌로 굳은 전사 수 세고
                        sightCaseMap[x][y] = 1; // 해당 지점은 메두사의 시야라는걸 표시
                        fillShadeDown(sightCaseMap, x, y); // 해당 전사를 기준으로 뒤에 그림자를 생성
                    } else if (sightCaseMap[x][y] == 0) { // 메두사의 시야에 닿는 빈공간인 경우
                        sightCaseMap[x][y] = 1; // 메두사의 시야라는 걸 표시
                    }
                }
                stage++;
            }
            // 메두사가 아래를 바라본 경우의 행렬, 방향 인덱스, 석화된 전사의 수
            return new Sight(sightCaseMap, 1, warriorCount);
        }

        private Sight seeLeft() {
            int[][] sightCaseMap = new int[N][N];
            int warriorCount = 0;
            int stage = 1;
            for (int y = this.y - 1; y > -1; y--) {
                for (int x = this.x - stage; x <= this.x + stage; x++) {
                    if (isOutOfRange(x, y)) {
                        continue;
                    }
                    // 이미 메두사의 시야로 표시했거나, 그림자인 경우에는 넘어감
                    if (sightCaseMap[x][y] == 1 || sightCaseMap[x][y] == -1) {
                        continue;
                    }

                    if (warriorMap[x][y] > 0 && sightCaseMap[x][y] == 0) { // 메두사의 시야에 전사가 있는 경우
                        warriorCount += warriorMap[x][y]; // 돌로 굳은 전사 수 세고
                        sightCaseMap[x][y] = 1; // 해당 지점은 메두사의 시야라는걸 표시
                        fillShadeLeft(sightCaseMap, x, y); // 해당 전사를 기준으로 뒤에 그림자를 생성
                    } else if (sightCaseMap[x][y] == 0) { // 메두사의 시야에 닿는 빈공간인 경우
                        sightCaseMap[x][y] = 1; // 메두사의 시야라는 걸 표시
                    }
                }
                stage++;
            }
            // 메두사가 완쪽 바라본 경우의 행렬, 방향 인덱스, 석화된 전사의 수
            return new Sight(sightCaseMap, 2, warriorCount);
        }

        private Sight seeRight() {
            int[][] sightCaseMap = new int[N][N];
            int warriorCount = 0;
            int stage = 1;
            for (int y = this.y + 1; y < N; y++) {
                for (int x = this.x - stage; x <= this.x + stage; x++) {
                    if (isOutOfRange(x, y)) {
                        continue;
                    }
                    // 이미 메두사의 시야로 표시했거나, 그림자인 경우에는 넘어감
                    if (sightCaseMap[x][y] == 1 || sightCaseMap[x][y] == -1) {
                        continue;
                    }

                    if (warriorMap[x][y] > 0 && sightCaseMap[x][y] == 0) { // 메두사의 시야에 전사가 있는 경우
                        warriorCount += warriorMap[x][y]; // 돌로 굳은 전사 수 세고
                        sightCaseMap[x][y] = 1; // 해당 지점은 메두사의 시야라는걸 표시
                        fillShadeRight(sightCaseMap, x, y); // 해당 전사를 기준으로 뒤에 그림자를 생성
                    } else if (sightCaseMap[x][y] == 0) { // 메두사의 시야에 닿는 빈공간인 경우
                        sightCaseMap[x][y] = 1; // 메두사의 시야라는 걸 표시
                    }
                }
                stage++;
            }
            // 메두사가 오른쪽 바라본 경우의 행렬, 방향 인덱스, 석화된 전사의 수
            return new Sight(sightCaseMap, 3, warriorCount);
        }

        private void fillShadeUp(int[][] sightCaseMap, int wx, int wy) {
            if (wy == y) { // 전사와 메두사가 일직선상에 있는 경우
                for (int x = wx - 1; x > -1; x--) {
                    if (sightCaseMap[x][wy] == 0) {
                        sightCaseMap[x][wy] = -1;
                    }
                }
                return;
            }

            if (wy < y) { // 전사가 메두사보다 왼쪽에 있는 경우
                int stage = 1;
                for (int x = wx - 1; x > -1; x--) {
                    for (int y = wy - stage; y <= wy; y++) {
                        int targetX = x;
                        int targetY = y;
                        if (targetY < 0) {
                            targetY = 0;
                        }
                        if (sightCaseMap[targetX][targetY] == 0) {
                            sightCaseMap[targetX][targetY] = -1;
                        }
                    }
                    stage++;
                }
                return;
            }

            // 전사가 메두사보다 오른쪽에 있는 경우
            int stage = 1;
            for (int x = wx - 1; x > -1; x--) {
                for (int y = wy; y <= wy + stage; y++) {
                    int targetX = x;
                    int targetY = y;
                    if (targetY >= N) {
                        targetY = N - 1;
                    }
                    if (sightCaseMap[targetX][targetY] == 0) {
                        sightCaseMap[targetX][targetY] = -1;
                    }
                }
                stage++;
            }
        }

        private void fillShadeDown(int[][] sightCaseMap, int wx, int wy) {
            if (wy == y) { // 전사와 메두사가 일직선상에 있는 경우
                for (int x = wx + 1; x < N; x++) {
                    if (sightCaseMap[x][wy] == 0) {
                        sightCaseMap[x][wy] = -1;
                    }
                }
                return;
            }

            if (wy < y) { // 전사가 메두사보다 왼쪽에 있는 경우
                int stage = 1;
                for (int x = wx + 1; x < N; x++) {
                    for (int y = wy - stage; y <= wy; y++) {
                        int targetX = x;
                        int targetY = y;
                        if (targetY < 0) {
                            targetY = 0;
                        }
                        if (sightCaseMap[targetX][targetY] == 0) {
                            sightCaseMap[targetX][targetY] = -1;
                        }
                    }
                    stage++;
                }
                return;
            }

            // 전사가 메두사보다 오른쪽에 있는 경우
            int stage = 1;
            for (int x = wx + 1; x < N; x++) {
                for (int y = wy; y <= wy + stage; y++) {
                    int targetX = x;
                    int targetY = y;
                    if (targetY >= N) {
                        targetY = N - 1;
                    }
                    if (sightCaseMap[targetX][targetY] == 0) {
                        sightCaseMap[targetX][targetY] = -1;
                    }
                }
                stage++;
            }
        }

        private void fillShadeLeft(int[][] sightCaseMap, int wx, int wy) {
            if (wx == x) { // 전사와 메두사가 일직선상에 있는 경우
                for (int y = wy - 1; y > -1; y--) {
                    if (sightCaseMap[wx][y] == 0) {
                        sightCaseMap[wx][y] = -1;
                    }
                }
                return;
            }

            if (wx < x) { // 전사가 메두사보다 위에 있는 경우
                int stage = 1;
                for (int y = wy - 1; y > -1; y--) {
                    for (int x = wx; x >= wx - stage; x--) {
                        int targetX = x;
                        int targetY = y;
                        if (targetX < 0) {
                            targetX = 0;
                        }
                        if (sightCaseMap[targetX][targetY] == 0) {
                            sightCaseMap[targetX][targetY] = -1;
                        }
                    }
                    stage++;
                }
                return;
            }

            // 전사가 메두사보다 아래에 있는 경우
            int stage = 1;
            for (int y = wy - 1; y > -1; y--) {
                for (int x = wx; x <= wx + stage; x++) {
                    int targetX = x;
                    int targetY = y;
                    if (targetX >= N) {
                        targetX = N - 1;
                    }
                    if (sightCaseMap[targetX][targetY] == 0) {
                        sightCaseMap[targetX][targetY] = -1;
                    }
                }
                stage++;
            }
        }

        private void fillShadeRight(int[][] sightCaseMap, int wx, int wy) {
            if (wx == x) { // 전사와 메두사가 일직선상에 있는 경우
                for (int y = wy + 1; y < N; y++) {
                    if (sightCaseMap[wx][y] == 0) {
                        sightCaseMap[wx][y] = -1;
                    }
                }
                return;
            }

            if (wx < x) { // 전사가 메두사보다 위에 있는 경우
                int stage = 1;
                for (int y = wy + 1; y < N; y++) {
                    for (int x = wx; x >= wx - stage; x--) {
                        int targetX = x;
                        int targetY = y;
                        if (targetX < 0) {
                            targetX = 0;
                        }
                        if (sightCaseMap[targetX][targetY] == 0) {
                            sightCaseMap[targetX][targetY] = -1;
                        }
                    }
                    stage++;
                }
                return;
            }

            // 전사가 메두사보다 아래에 있는 경우
            int stage = 1;
            for (int y = wy + 1; y < N; y++) {
                for (int x = wx; x <= wx + stage; x++) {
                    int targetX = x;
                    int targetY = y;
                    if (targetX >= N) {
                        targetX = N - 1;
                    }
                    if (sightCaseMap[targetX][targetY] == 0) {
                        sightCaseMap[targetX][targetY] = -1;
                    }
                }
                stage++;
            }
        }

        private void killWarrior() {
            int targetX = x;
            int targetY = y;
            for (Warrior warrior : warriors) {
                if (warriorMap[targetX][targetY] == 0) {
                    break;
                }
                if (warrior.x == targetX && warrior.y == targetY) {
                    warrior.isAlive = false;
                    // warrior는 한 칸에 1명 이상 있을 수 있음
                    warriorMap[targetX][targetY]--;
                }
            }
        }

        private void bindWarrior() {
            for (Warrior warrior : warriors) {
                if (sightMap[warrior.x][warrior.y] == 1) {
                    warrior.canMove = false;
                }
            }
        }
    }

    private static class Warrior {
        int x, y;
        boolean isAlive, canMove;

        public Warrior(int x, int y) {
            this.x = x;
            this.y = y;
            isAlive = true;
            canMove = true;
        }

        public int move(Medusa medusa) {
            if (!canMove || !isAlive) {
                return 0;
            }
            int movedDistance = 0;

            for (int moveCount = 0; moveCount < 2; moveCount++) {
                // 첫번째 이동, 상하좌우순으로 메두사와 거리를 줄일 수 있는 방향으로 이동
                if (moveCount == 0) {
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (isOutOfRange(nx, ny) || sightMap[nx][ny] == 1) {
                            continue;
                        }

                        int curDistance = getDistance(x, y, medusa.x, medusa.y);
                        int nextDistance = getDistance(nx, ny, medusa.x, medusa.y);

                        if (nextDistance < curDistance) {
                            warriorMap[x][y]--;
                            warriorMap[nx][ny]++;
                            x = nx;
                            y = ny;
                            movedDistance++;
                            break;
                        }
                    }
                    continue;
                }

                // 두번째 이동, 좌우상하순으로 메두사와 거리를 줄일 수 있는 방향으로 이동
                int[] dx = {0, 0, -1, 1};
                int[] dy = {-1, 1, 0, 0};
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (isOutOfRange(nx, ny) || sightMap[nx][ny] == 1) {
                        continue;
                    }

                    int curDistance = getDistance(x, y, medusa.x, medusa.y);
                    int nextDistance = getDistance(nx, ny, medusa.x, medusa.y);

                    if (nextDistance < curDistance) {
                        warriorMap[x][y]--;
                        warriorMap[nx][ny]++;
                        x = nx;
                        y = ny;
                        movedDistance++;
                        break;
                    }
                }

            }
            return movedDistance;
        }

        public void attackMedusa() {
            isAlive = false;
            warriorMap[x][y]--;
        }
    }

    private static class Sight {
        int[][] map;
        int direction;
        int warriorCount;

        public Sight(int[][] map, int direction, int warriorCount) {
            this.map = map;
            this.direction = direction;
            this.warriorCount = warriorCount;
        }
    }

    private static class Node {
        int x, y, distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static int[][] warriorMap, sightMap;
    static int arrX, arrY, N;
    static List<Warrior> warriors;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int[][] getDistanceMap(int[][] tileMap) {
        int INF = 1_000_000_000;
        int[][] distanceMap = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(arrX, arrY, 0));
        visited[arrX][arrY] = true;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isOutOfRange(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }

                // 도로가 아닌 경우는 가중치를 최대로
                if (tileMap[nx][ny] == 1) {
                    distanceMap[nx][ny] = INF;
                    continue;
                }
                visited[nx][ny] = true;
                distanceMap[nx][ny] = cur.distance + 1;
                queue.offer(new Node(nx, ny, distanceMap[nx][ny]));
            }
        }

        return distanceMap;
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    private static boolean isOnMedusa(Warrior warrior, Medusa medusa) {
        return warrior.x == medusa.x && warrior.y == medusa.y;
    }

    private static void resetWarriorsStatus() {
        for (Warrior warrior :
                warriors) {
            if (warrior.isAlive && !warrior.canMove) {
                warrior.canMove = true;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int[][] tileMap = new int[N][N];
        warriorMap = new int[N][N];
        sightMap = new int[N][N];
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int mx = Integer.parseInt(st.nextToken());
        int my = Integer.parseInt(st.nextToken());
        Medusa medusa = new Medusa(mx, my);
        arrX = Integer.parseInt(st.nextToken());
        arrY = Integer.parseInt(st.nextToken());

        warriors = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            warriorMap[x][y]++;
            warriors.add(new Warrior(x, y));
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                tileMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] distanceMap = getDistanceMap(tileMap);
        if (distanceMap[medusa.x][medusa.y] == 0 && (medusa.x != arrX || medusa.y != arrY)) {
            System.out.println(-1);
            return;
        }

        StringBuilder scores = new StringBuilder();

        while (!medusa.isArrived) {
            int warriorMovedDistance = 0;
            int bindedWarriors = 0;
            int attackedWarriors = 0;

            // 1. 메두사 이동
            medusa.move(distanceMap);
            // 2. 메두사 가장 많은 전사쪽으로 바라보기
            bindedWarriors = medusa.seeWarriors();
            // 3. 전사들 이동
            for (Warrior warrior : warriors) {
                if (!warrior.isAlive) {
                    continue;
                }
                warriorMovedDistance += warrior.move(medusa);
                if (isOnMedusa(warrior, medusa)) {
                    warrior.attackMedusa();
                    attackedWarriors++;
                }
            }

            if (medusa.isArrived) {
                scores.append(0);
            } else {
                scores.append(String.format("%d ", warriorMovedDistance))
                        .append(String.format("%d ", bindedWarriors))
                        .append(String.format("%d\n", attackedWarriors));
            }
            // 리셋: 메두사의 시야 리셋, 전사들 움직임 상태 리셋
            sightMap = new int[N][N];
            resetWarriorsStatus();
        }
        System.out.println(scores);
    }
}
