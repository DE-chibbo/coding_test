package BFS_DFS.KimJunYoung;

import java.util.*;

public class Programmers7 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int[][] gameBoard1 = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int[][] table1 = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
        System.out.println(solved.solution(gameBoard1, table1));
    }
    static class Solution {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        public int solution(int[][] game_board, int[][] table) {
            int answer = 0;
            boolean[][] visited = new boolean[table.length][table[0].length];
            List<List<int[]>> blocks = new ArrayList<>();
            // 테이블에 있는 블록들의 좌표를 가져옴
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[0].length; j++) {
                    if (table[i][j] == 1 && !visited[i][j]) {
                        List<int[]> block = getCoordinates(i, j, table, visited, true);
                        // 각 블록을 이루는 좌표를 정렬해주어야함.
                        // x좌표 오름차순, y좌표 오름차순으로 정렬하며,
                        // 정렬된 순서를 이용하여 손쉽게 블록의 좌표를 회전하고, 빈 칸과 대조하여 일치 여부를 판단할 것임
                        // 빈칸의 좌표도 똑같은 조건으로 정렬해주어 이들의 차를 통해
                        // 동일한 모양을 갖는 블록인지 판별할 수 있음(좌표 선형 이동의 성질을 이용)
                        block.sort((o1, o2) -> {
                            if (o1[0] == o2[0]) {
                                return o1[1] - o2[1];
                            }
                            return o1[0] - o2[0];
                        });
                        blocks.add(shiftBlock(block));
                    }
                }
            }

            // 빈칸의 좌표를 가져옴
            List<List<int[]>> blanks = new ArrayList<>();
            visited = new boolean[table.length][table[0].length];
            for (int i = 0; i < game_board.length; i++) {
                for (int j = 0; j < game_board[0].length; j++) {
                    if (game_board[i][j] == 0 && !visited[i][j]) {
                        List<int[]> blank = getCoordinates(i, j, game_board, visited, false);
                        // 각 빈칸을 이루는 좌표도 동일하게 정렬
                        // x좌표 오름차순, y좌표 오름차순으로 정렬
                        // 이는 빈칸의 좌표도 똑같은 조건으로 정렬해주어 이들의 차를 통해
                        // 동일한 모양을 갖는 블록인지 판별할 수 있음(좌표의 선형이동 성질을 이용)
                        blank.sort((o1, o2) -> {
                            if (o1[0] == o2[0]) {
                                return o1[1] - o2[1];
                            }
                            return o1[0] - o2[0];
                        });
                        blanks.add(blank);
                    }
                }
            }
            // 사용한 블록인지 체크하는 배열
            boolean[] isUsed = new boolean[blocks.size()];
            // 빈칸을 최대로 채울 경우의 수는 조건에 빈 칸에 딱 맞는 블록만 넣을 수 있으므로
            // 그냥 빈 칸에 맞는 블록을 바로 바로 끼워 넣음으로써 구할 수 있음
            for (List<int[]> blank :
                    blanks) {
                int filledSize = fillBlank(blank, blocks, isUsed);
                answer += filledSize;

            }

            return answer;
        }

        private int fillBlank(List<int[]> blank, List<List<int[]>> blocks, boolean[] isUsed) {
            for (int idx = 0; idx < blocks.size(); idx++) {
                // 이미 사용한 블록이면 다음 블록을 사용
                if (isUsed[idx]) {
                    continue;
                }
                List<int[]> block = blocks.get(idx);
                // 빈 칸과 블록이 사이즈가 다르면 맞을 리가 없으므로 다른 블록을 사용
                if (blank.size() != block.size()) {
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    // 블록이 빈칸과 일치하면 블록의 사용 여부를 기록하고, 채운 빈칸의 크기를 반환
                    if (isMatch(blank, block)) {
                        isUsed[idx] = true;
                        return block.size();
                    }
                    // 90도 회전은 3번만 하면 되므로 4번째는 무시
                    if (i == 3) {
                        break;
                    }
                    // 블록이 빈칸과 맞지 않다면 90도씩 회전해봄
                    block = rotateBlock(block);
                }

            }
            return 0;
        }

        private boolean isMatch(List<int[]> blank, List<int[]> block) {
            // 빈 칸과 블록의 일치 여부를 반환
            // 빈 칸과 블록은 동일한 방향을 가져야하며, 선형 이동에 따른 차이는 상관 없음
            // 빈 칸과 블록이 서로 선형 이동 관계라 가정하고, 빈 칸과 블록의 모든 좌표가 서로 동일한 선형 이동 관계일 경우
            // 일치하는 것으로 판단함
            int offsetX = blank.get(0)[0] - block.get(0)[0];
            int offsetY = blank.get(0)[1] - block.get(0)[1];
            for (int i = 0; i < blank.size(); i++) {
                int[] blankCood = blank.get(i);
                int[] blockCood = block.get(i);
                // 한 좌표라도 선형 이동 정도가 다르면 빈 칸과 블록은 서로 다른 모양을 갖는 것임
                if (blankCood[0] - blockCood[0] != offsetX || blankCood[1] - blockCood[1] != offsetY) {
                    return false;
                }
            }
            return true;
        }

        private List<int[]> getCoordinates(int startX, int startY, int[][] map, boolean[][] visited, boolean isTable) {
            // 게임보드 혹은 테이블로부터 각각 한 빈 칸, 한 블록의 좌표를 구하여 반환하는 함수
            // BFS를 통해 이어지는 빈 칸 혹은 블록 칸을 탐색
            List<int[]> coordinates = new ArrayList<>();
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{startX, startY});
            visited[startX][startY] = true;
            coordinates.add(new int[]{startX, startY});

            // 테이블인지 게임보드인지에 따라 행렬 조건이 달라지므로 flag를 이용
            int flag = 0;
            if (isTable) {
                flag = 1;
            }

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length) {
                        continue;
                    }
                    if (map[nx][ny] == flag && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        coordinates.add(new int[]{nx, ny});
                    }
                }
            }

            return coordinates;
        }

        private List<int[]> shiftBlock(List<int[]> block) {
            // 블록의 90도 회전을 쉽게 하기 위해 블록의 좌표를 2차원 평면의 x, y축에 접하도록 선형 이동하는 함수
            int minX = block.get(0)[0];
            int minY = Integer.MAX_VALUE;
            for (int[] cood :
                    block) {
                int y = cood[1];
                minY = Math.min(minY, y);
            }
            List<int[]> shiftedBlock = new ArrayList<>();
            for (int[] cood: block) {
                int x = cood[0];
                int y = cood[1];
                shiftedBlock.add(new int[]{x - minX, y - minY});
            }
            return shiftedBlock;
        }

        private List<int[]> rotateBlock(List<int[]> block) {
            // 블록을 90도 회전하는 함수
            // 블록의 좌표가 x좌표 오름차순, y좌표 오름차순 으로 정렬되어 있다고 가정한채로 회전시킴
            int[] maxCood = block.get(block.size() - 1);
            int maxX = maxCood[0];
            List<int[]> rotated = new ArrayList<>();
            for (int[] cood :
                    block) {
                int x = cood[0];
                int y = cood[1];
                rotated.add(new int[]{y, maxX - x});
            }
            // 회전한 블록의 좌표도 동일하게 정렬해주어야함.
            // 안그러면 회전한 블록을 또 회전시킬 경우 의도와 다르게 블록이 이상한 모양으로 바뀌어버림
            rotated.sort((o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            });
            return rotated;
        }
    }
}