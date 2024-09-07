package data_structure.KimJunYoung;

import java.util.*;

public class ProgrammersStackQueue3 {
    public static void main(String[] args) {
        ProgrammersStackQueue3 solved = new ProgrammersStackQueue3();
        String test1 = "(())(";
        System.out.println(solved.solution(test1)); // false
        String test2 = "(()))";
        System.out.println(solved.solution(test2)); // false
        String test3 = ")()(";
        System.out.println(solved.solution(test3)); // false
        String test4 = "(((((((((())))))))))";
        System.out.println(solved.solution(test4)); // true
        String test5 = "(((())(()(())))(()(())())(((()))))";
        System.out.println(solved.solution(test5)); // true
    }
    public boolean solution(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                deque.offerLast(ch);
            }
            else if (ch == ')') {
                if (deque.isEmpty() || deque.peek() == ')') {
                    return false;
                }
                else if (deque.peek() == '(') {
                    deque.pollLast();
                }
            }
        }
        if (!deque.isEmpty()) {
            return false;
        }
        return true;
    }
}
