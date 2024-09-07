package data_structure.KimJunYoung;

import java.util.*;
public class ProgrammersStackQueue6 {
    public static void main(String[] args) {
        ProgrammersStackQueue6 solved = new ProgrammersStackQueue6();
        int[] prices1 = {1, 2, 1, 2, 1, 2};
        System.out.println(Arrays.toString(solved.solution(prices1))); // [5, 1, 3, 1, 1, 0]
    }
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<int[]> deque = new ArrayDeque<>();
        for (int time = 0; time < prices.length; time++) {
            // the numbers in deque will be sorted as ascending order
            if (deque.isEmpty() || prices[time] >= deque.peekLast()[0]) {
                deque.offerLast(new int[]{prices[time], time});
                continue;
            }
            // if largest price in deque meet lower price
            // pop largest price in deque and calculate remained time while lower price < largest price in deque
            while(!deque.isEmpty() && deque.peekLast()[0] > prices[time]) {
                int releasedTime = deque.pollLast()[1];
                int interval = time - releasedTime;
                answer[releasedTime] = interval;
            }
            // after popping largest number in deque, should offerLast lower price in deque
            // and it might be largest price in deque
            deque.offerLast(new int[]{prices[time], time});
        }
        // for prices that didnt meet price lower than itself
        while (!deque.isEmpty()) {
            int releasedTime = deque.pollLast()[1];
            int interval = prices.length - 1 - releasedTime;
            answer[releasedTime] = interval;
        }
        return answer;
    }
}
