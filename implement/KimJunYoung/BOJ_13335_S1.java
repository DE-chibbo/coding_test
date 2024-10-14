package implement.KimJunYoung;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335_S1 {
    /*
    백준 문제 13335번: 트럭
     */
    private static class Truck {
        int weight;
        int distance;

        public Truck(int weight, int distance) {
            this.weight = weight;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int w = Integer.parseInt(stringTokenizer.nextToken());
        int L = Integer.parseInt(stringTokenizer.nextToken());
        Queue<Truck> trucks = new LinkedList<>();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            trucks.offer(new Truck(Integer.parseInt(stringTokenizer.nextToken()), 0));
        }
        int time = 0;
        int availableWeight = L;
        Deque<Truck> onRoads = new ArrayDeque<>();
        while (!trucks.isEmpty() || !onRoads.isEmpty()) {
            if (!trucks.isEmpty() && trucks.peek().weight <= availableWeight) {
                availableWeight -= trucks.peek().weight;
                onRoads.offer(trucks.poll());
            }
            for (Truck truck :
                    onRoads) {
                truck.distance++;
            }
            time++;
            if (!onRoads.isEmpty() && onRoads.peekFirst().distance == w) {
                availableWeight += onRoads.pollFirst().weight;
            }
        }
        System.out.println(time + 1);
    }
}

