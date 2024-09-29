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
            // 경로가 두 개일 경우 사전순으로 갔을 때, 더 이상 방문할 공항이 있음에도 못 가는 경우가 있음
            // 따라서 모든 공항 방문 경로를 역순으로 route 리스트에 삽입할 것임
            // 즉, 더이상 다른 공항으로 이동할 수 없는 공항을 우선으로 route 리스트에 삽입
            // 이를 위해 더 방문할 공항이 있는 공항들을 저장할 deque를 선언
            ArrayDeque<String> deque = new ArrayDeque<>();
            List<String> route = new ArrayList<>();
            deque.offerLast("ICN");

            while (!deque.isEmpty()) {
                String from = deque.peekLast();
                // 더이상 방문할 공항이 없을 경우
                if (!ticketMap.containsKey(from) || ticketMap.get(from).isEmpty()) {
                    route.add(deque.pollLast());
                    continue;
                }
                // 더 방문할 공항이 있을 경우
                deque.offerLast(ticketMap.get(from).poll());
            }
            Collections.reverse(route);
            return route.toArray(String[]::new);
        }
    }
}
