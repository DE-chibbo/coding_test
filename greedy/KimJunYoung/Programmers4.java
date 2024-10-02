package greedy.KimJunYoung;

import java.util.*;

public class Programmers4 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int[] people1 = {10, 10, 10, 10, 90};
        int limit1 = 100;
        System.out.println(solved.solution(people1, limit1) == 3);
    }

    static class Solution {
        public int solution(int[] people, int limit) {
            Arrays.sort(people);
            int light = 0;
            for (int i = people.length - 1; i > light; i--) {
                if (people[light] + people[i] <= limit) {
                    light++;
                }
            }
            // 1명씩 태우는 경우의 수에서 2명씩 태우는 경우의 수를 빼주면
            // 최대 2명씩 탈 수 있는 보트를 최소로 사용한 경우의 수를 구할 수 있음
            return people.length - light;
        }
    }
}
