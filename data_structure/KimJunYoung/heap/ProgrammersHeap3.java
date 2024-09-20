package data_structure.KimJunYoung.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ProgrammersHeap3 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        String[] operations1 = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        System.out.println(Arrays.toString(solved.solution(operations1)));
        String[] operations2 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(Arrays.toString(solved.solution(operations2)));
        String[] operations3 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333", "D -1", "D 1"};
        System.out.println(Arrays.toString(solved.solution(operations3)));
    }
    private static final char INSERT = 'I';
    private static final char DELETE = 'D';
    private static final int MAX_TYPE = 1;
    private static final int MIN_TYPE = -1;
    static class Solution {
        public int[] solution(String[] operations) {
            DoublePriorityQueue dpq = new DoublePriorityQueue();
            for (String operation: operations) {
                StringTokenizer st = new StringTokenizer(operation);
                char command = st.nextToken().charAt(0);
                if (command == INSERT) {
                    dpq.insert(Integer.parseInt(st.nextToken()));
                    continue;
                }
                if (command == DELETE) {
                    int type = Integer.parseInt(st.nextToken());
                    if (type == MAX_TYPE) {
                        dpq.deleteMax();
                    } else if (type == MIN_TYPE) {
                        dpq.deleteMin();
                    }
                }
            }
            return new int[]{dpq.getMax(), dpq.getMin()};
        }
        private static class DoublePriorityQueue {
            PriorityQueue<Integer> ascQueue = new PriorityQueue<>(Comparator.naturalOrder());
            PriorityQueue<Integer> descQueue = new PriorityQueue<>(Comparator.reverseOrder());

            public void insert(int number) {
                moveDescToAsc();
                ascQueue.offer(number);
            }

            public void deleteMax() {
                moveAscToDesc();
                if (!descQueue.isEmpty()) {
                    descQueue.poll();
                }
            }

            public void deleteMin() {
                moveDescToAsc();
                if (!ascQueue.isEmpty()) {
                    ascQueue.poll();
                }
            }

            public int getMax() {
                moveAscToDesc();
                if (descQueue.isEmpty()){
                    return 0;
                }
                return descQueue.peek();
            }

            public int getMin() {
                moveDescToAsc();
                if (ascQueue.isEmpty()) {
                    return 0;
                }
                return ascQueue.peek();
            }

            private void moveAscToDesc() {
                while (!ascQueue.isEmpty()) {
                    descQueue.offer(ascQueue.poll());
                }
            }

            private void moveDescToAsc() {
                while (!descQueue.isEmpty()) {
                    ascQueue.offer(descQueue.poll());
                }
            }

        }
    }
}
