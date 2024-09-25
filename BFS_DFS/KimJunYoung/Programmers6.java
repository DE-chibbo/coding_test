package BFS_DFS.KimJunYoung;

import java.util.*;

public class Programmers6 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        String[][] tickets = {{"ICN", "AAA"}, {"ICN", "DDD"}, {"DDD", "ICN"}};
        System.out.println(Arrays.toString(solved.solution(tickets)));
    }

    static class Solution {
        public String[] solution(String[][] tickets) {
            Map<String, PriorityQueue<String>> ticketMap = new HashMap<>();
            for (String[] ticket: tickets) {
                String from = ticket[0];
                String to = ticket[1];
                ticketMap.computeIfAbsent(from, k -> new PriorityQueue<>()).offer(to);
            }

            List<String> route = new ArrayList<>();
            ArrayDeque<String> deque = new ArrayDeque<>();
            deque.offerLast("ICN");

            while (!deque.isEmpty()) {
                String from = deque.peekLast();
                if (!ticketMap.containsKey(from) || ticketMap.get(from).isEmpty()) {
                    route.add(deque.pollLast());
                    continue;
                }
                deque.offerLast(ticketMap.get(from).poll());
            }
            Collections.reverse(route);
            return route.toArray(String[]::new);
        }
    }
}
