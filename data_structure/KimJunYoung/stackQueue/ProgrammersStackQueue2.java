package data_structure.KimJunYoung.stackQueue;

import java.util.*;

public class ProgrammersStackQueue2 {
    public static void main(String[] args) {
        ProgrammersStackQueue2 solved  = new ProgrammersStackQueue2();
        int[] test1Progresses = {95, 0};
        int[] test2Speeds = {4, 50};
        System.out.println(Arrays.toString(solved.solution(test1Progresses, test2Speeds))); // [2]
    }
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < speeds.length; i++) {
            int progress = progresses[i];
            int speed = speeds[i];
            int daysToDeploy = (int) Math.ceil((100 - progress) / (double) speed);
            queue.offer(daysToDeploy);
        }
        List<Integer> answer = new ArrayList<>();
        while(!queue.isEmpty()) {
            int daysToDeploy = queue.poll();
            int count = 1;
            while(!queue.isEmpty()) {
                if (queue.peek() > daysToDeploy) {
                    break;
                }
                queue.poll();
                count++;
            }
            answer.add(count);
        }
        return answer.stream().mapToInt(x -> x).toArray();
    }
}
