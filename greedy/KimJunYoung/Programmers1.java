package greedy.KimJunYoung;

import java.util.*;
import java.util.stream.Collectors;

public class Programmers1 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int n1 = 3;
        int[] lost1 = {1, 2};
        int[] reserved1 = {2, 3};
        System.out.println(solved.solution(n1, lost1, reserved1) == 2);
        System.out.println(solved.solution2(n1, lost1, reserved1) == 2);

        int n2 = 5;
        int[] lost2 = {4, 5};
        int[] reserved2 = {3, 4};
        System.out.println(solved.solution(n2, lost2, reserved2) == 4);
        System.out.println(solved.solution2(n2, lost2, reserved2) == 4);
    }

    static class Solution {
        public int solution(int n, int[] lost, int[] reserve) {
            // 배열 사용한 풀이
            int answer = n;
            int[] reservedStudents = new int[n + 2];
            int[] lostStudents = new int[n + 2];
            for (int number: reserve) {
                reservedStudents[number] = 1;
            }
            for (int number: lost) {
                lostStudents[number] = 1;
            }
            Arrays.sort(lost);
            for (int number: lost) {
                if (reservedStudents[number] > 0) {
                    reservedStudents[number]--;
                }
                else if (reservedStudents[number - 1] > 0) {
                    reservedStudents[number - 1]--;
                }

                else if (reservedStudents[number + 1] > 0) {
                    // 반례 lost: [1,2], reserve: [2, 3] 경우 대비를 위해 아래 조건문 필요
                    // 내 뒷 번호가 여벌 옷이 있지만, 도둑 맞았는지 확인 필요
                    if(lostStudents[number + 1] == 1) {
                        answer--;
                        continue;
                    }
                    reservedStudents[number + 1]--;
                }
                else {
                    answer--;
                }
            }

            return answer;
        }

        public int solution2(int n, int[] lost, int[] reserve) {
            // 집합 사용한 풀이
            // 옷을 도난 당한 모든 학생 집합
            Set<Integer> lostSet = Arrays.stream(lost).boxed().collect(Collectors.toSet());
            // 여벌 옷이 있는 모든 학생 집합
            Set<Integer> reservedSet = Arrays.stream(reserve).boxed().collect(Collectors.toSet());
            // 도난 당했지만, 여벌 옷이 있는 학생 집합
            Set<Integer> reserveWithLost = new HashSet<>(lostSet);
            reserveWithLost.retainAll(reservedSet);

            // 도난 당했는데, 여벌 옷이 없는 학생 집합
            lostSet.removeAll(reserveWithLost);
            // 여벌 옷이 있으면서 도난당하지 않은 학생 집합
            reservedSet.removeAll(reserveWithLost);

            List<Integer> reservedOnly = new ArrayList<>(reservedSet);
            reservedOnly.sort(Comparator.naturalOrder());

            for (int number: reservedOnly) {
                if (lostSet.contains(number - 1)) {
                    lostSet.remove(number - 1);
                    continue;
                }
                lostSet.remove(number + 1);
            }
            return n - lostSet.size();
        }
    }
}
