package bruteforce.KimJunYoung;

import java.util.ArrayList;
import java.util.List;

public class ProgrammersBruteforce5 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int K1 = 80;
        int[][] dungeons1 = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solved.solution(K1, dungeons1) == 3);
    }

    static class Solution {
        public int solution(int k, int[][] dungeons) {
            int answer = -1;
            int maxRange = dungeons.length;
            List<List<Integer>> sequences = new ArrayList<>();
            List<Integer> sequence = new ArrayList<>();
            boolean[] visited = new boolean[maxRange];
            getSequences(maxRange, sequence, sequences, visited);

            for(List<Integer> tempSequence: sequences) {
                int curK = k;
                int clearCount = 0;
                // 임의의 순서별로 던전을 돔
                for(int index: tempSequence) {
                    int[] dungeon = dungeons[index];
                    int minK = dungeon[0];
                    int usedK = dungeon[1];
                    // 최소 필요 피로도보다 작은 경우 해당 던전은 못돔
                    if (minK > curK) {
                        continue;
                    }
                    // 던전을 돌 수 있는 경우
                    curK -= usedK;
                    clearCount++;
                }
                answer = Math.max(answer, clearCount);
            }
            return answer;
        }

        private void getSequences(int maxRange, List<Integer> sequence, List<List<Integer>> sequences,boolean[] visited) {
            if (sequence.size() == maxRange) {
                sequences.add(new ArrayList<>(sequence));
                return;
            }
            for (int i = 0; i < maxRange; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    sequence.add(i);
                    getSequences(maxRange, sequence, sequences, visited);
                    visited[i] = false;
                    sequence.remove(sequence.size() - 1);
                }
            }
        }
    }
}
