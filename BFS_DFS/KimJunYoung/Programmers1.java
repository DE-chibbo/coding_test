package BFS_DFS.KimJunYoung;

public class Programmers1 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int[] numbers1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println(solved.solution(numbers1, target1) == 5);
        int[] numbers2 = {4, 1, 2, 1};
        int target2 = 4;
        System.out.println(solved.solution(numbers2, target2) == 2);
    }
    static class Solution {
        public int solution(int[] numbers, int target) {
            return dfs(target, 0, numbers, 0);
        }

        private int dfs(int target, int depth, int[] numbers, int sum) {
            if (depth == numbers.length) {
                if (sum == target) {
                    return 1;
                }
                return 0;
            }
            return dfs(target, depth + 1, numbers, sum + numbers[depth]) + dfs(target, depth + 1, numbers, sum - numbers[depth]);
        }
    }
}
