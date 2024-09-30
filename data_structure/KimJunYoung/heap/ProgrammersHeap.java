package data_structure.KimJunYoung.heap;
import java.util.*;

class ProgrammersHeap {
    private static final char INSERT = 'I';
    private static final char DELETE = 'D';
    private static final int MAX_TYPE = 1;
    private static final int MIN_TYPE = -1;

    public int[] solution(String[] operations) {
        DoublePriorityQueue dpq = new DoublePriorityQueue();
        for(String operation: operations) {
            StringTokenizer st = new StringTokenizer(operation);
            char command = st.nextToken().charAt(0);
            if(command == INSERT) {
                int number = Integer.parseInt(st.nextToken());
                dpq.insert(number);
                continue;
            }
            if(command == DELETE) {
                int type = Integer.parseInt(st.nextToken());
                if(type == MAX_TYPE) {
                    dpq.deleteMaxValue();
                    continue;
                }
                if(type == MIN_TYPE) {
                    dpq.deleteMinValue();
                }
            }
        }
        int max = dpq.getMaxValue();
        int min = dpq.getMinValue();
        return new int[] {max, min};
    }

    private static class DoublePriorityQueue {
        private final PriorityQueue<Integer> ascPQ = new PriorityQueue<>();
        private final PriorityQueue<Integer> descPQ = new PriorityQueue<>(Collections.reverseOrder());

        public void insert(int number) {
            ascPQ.offer(number);
        }

        public void deleteMaxValue() {
            if (!descPQ.isEmpty() && ascPQ.isEmpty()) {
                descPQ.poll();
                return;
            }
            while (!ascPQ.isEmpty()) {
                descPQ.offer(ascPQ.poll());
            }
            descPQ.poll();
        }

        public void deleteMinValue() {
            if (!ascPQ.isEmpty() && descPQ.isEmpty()) {
                ascPQ.poll();
                return;
            }

            while (!descPQ.isEmpty()) {
                ascPQ.offer(descPQ.poll());
            }

            ascPQ.poll();
        }

        public int getMaxValue() {
            if (!descPQ.isEmpty() && ascPQ.isEmpty()) {
                return descPQ.peek();
            }

            while (!ascPQ.isEmpty()) {
                descPQ.offer(ascPQ.poll());
            }
            if (!descPQ.isEmpty()) {
                return descPQ.peek();
            }
            return 0;
        }

        public int getMinValue() {
            if (!ascPQ.isEmpty() && descPQ.isEmpty()) {
                return ascPQ.peek();
            }

            while (!descPQ.isEmpty()) {
                ascPQ.offer(descPQ.poll());
            }
            if (!ascPQ.isEmpty()) {
                return ascPQ.peek();
            }
            return 0;
        }
    }
}