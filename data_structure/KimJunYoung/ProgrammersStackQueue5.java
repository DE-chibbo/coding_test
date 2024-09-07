package data_structure.KimJunYoung;

import java.util.*;

public class ProgrammersStackQueue5 {
    public static void main(String[] args) {
        ProgrammersStackQueue5 solved = new ProgrammersStackQueue5();
        int bridge_length1 = 1;
        int weight1 = 100;
        int[] trucks1 = {1, 1, 1, 1, 1};
        System.out.println(solved.solution(bridge_length1, weight1, trucks1)); // 6
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> trucks = new ArrayDeque<>();
        Deque<int[]> bridge = new ArrayDeque<>();
        for (int truckWeight : truck_weights) {
            trucks.offer(truckWeight);
        }

        int weightSum = 0;
        int curTime = 1;
        // 다리를 건너야할 트럭이 있는동안만 루프를 돌림
        while(!trucks.isEmpty()) {
            int curTruckWeight = trucks.peek();
            // check truck remaining at the first of the bridge
            if (!bridge.isEmpty()) {
                // 제일 앞 트럭이 나갈 시간이 되면 다리에서 제거하고, 하중을 줄임
                if (bridge.peekFirst()[1] == curTime) {
                    weightSum -= bridge.pollFirst()[0];
                }
            }
            // 다리 하중이 견딘다면 다리에 트럭을 추가, 트럭 대기 큐에서는 제거
            if (weightSum + curTruckWeight <= weight) {
                bridge.offerLast(new int[]{curTruckWeight, curTime + bridge_length});
                weightSum += curTruckWeight;
                trucks.poll();
            }
            // 현재 시간을 더 해줌
            curTime++;
        }
        // 다리 위에 있는 제일 마지막 트럭의 나갈 시간이 답이 될 것임.
        if (!bridge.isEmpty()) {
            return bridge.pollLast()[1];
        }
        return curTime + bridge_length - 1;
    }
}
