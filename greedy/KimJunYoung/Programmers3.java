package greedy.KimJunYoung;

import java.util.*;

public class Programmers3 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        String number1 = "4177252841";
        int k1 = 4;
        System.out.println(solved.solution(number1, k1));

        String number2 = "987654321";
        int k2 = 3;
        System.out.println(solved.solution(number2, k2));
    }


    static class Solution {
        public String solution(String number, int k) {
            List<Integer> collected = new ArrayList<>();
            for (int i = 0; i < number.length(); i++) {
                int current = Character.getNumericValue(number.charAt(i));
                // 여태까지 수집한 수의 뒷자리부터 현재 자리 수보다 작은 수는 제거함(k가 0이 아닐때까지)
                while (!collected.isEmpty() && collected.get(collected.size() - 1) < current && k > 0) {
                    collected.remove(collected.size() - 1);
                    k--;
                }
                // 숫자 문자열을 다 순회하기 전에 k개만큼 숫자를 지운 경우
                // 현재 자리부터 끝까지 모든 수는 그대로 붙게됨
                if (k == 0) {
                    String sliced = number.substring(i);
                    for (int j = 0; j < sliced.length(); j++) {
                        int digit = Character.getNumericValue(sliced.charAt(j));
                        collected.add(digit);
                    }
                    break;
                }
                collected.add(current);
            }


            // 문자열을 다 순회했는데도 k개만큼 중간에 있는 수를 지우지 않은 경우
            // "987654321"의 경우가 됨
            // 뒤에서부터 수를 하나씩 지움
            while (k > 0) {
                collected.remove(collected.size() - 1);
                k--;
            }

            StringBuilder sb = new StringBuilder();
            for (int digit: collected) {
                sb.append(digit);
            }

            return sb.toString();
        }

    }
}
