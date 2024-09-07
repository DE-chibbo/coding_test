package data_structure.KimJunYoung;

import java.util.*;
public class ProgrammersStackQueue1 {
    public static void main(String[] args) {
        ProgrammersStackQueue1 solved = new ProgrammersStackQueue1();
        int[] testCase1 = {9, 9, 9};
        System.out.println(Arrays.toString(solved.solution(testCase1))); // [9]
        int[] testCase2 = {1};
        System.out.println(Arrays.toString(solved.solution(testCase2))); // [1]
        int[] testCase3 = {1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 1};
        System.out.println(Arrays.toString(solved.solution(testCase3))); // [1, 2, 1, 2, 1, 2, 1, 2, 1]
    }
    public int[] solution(int []arr) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(arr[0]);
        for (int num : arr) {
            int before = numbers.get(numbers.size() - 1);
            if (num != before) {
                numbers.add(num);
            }
        }

        return numbers.stream().mapToInt(x -> x).toArray();
    }
}
