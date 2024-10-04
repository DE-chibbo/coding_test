package greedy.KimJunYoung;

import java.util.*;

public class Programmers5 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        int n = 4;
        System.out.println(solved.solution(n, costs) == 4);
    }
    static class Solution {
        public int solution(int n, int[][] costs) {
            /* 크루스칼 알고리즘 이용 */
            // 1. 비용의 오름차순으로 간선의 정보를 정렬
            Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));
            // 모든 노드의 참조 관계를 표시할 배열
            // parent[child] = 부모 노드
            int[] parent = new int[n];
            // 최초에는 자기 자신이 곧 부모가 되도록 초기화
            // 즉, 각각 하나의 노드를 갖는 n개의 집합이 존재하게 됨
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            int count = 0;
            int answer = 0;
            // 2. 찾은 최소 비용 간선의 갯수가 n - 1이 될 때까지 모든 간선 정보에 대해 반복문을 돌며 아래 과정을 수행
            // 2-1. 간선 정보에서 연결된 두 노드가 순환 관계인지 확인
            //   a. 순환 관계면 해당 간선은 넘어감
            //   b. 비순환 관계면 해당 간선을 선택하여 두 노드를 같은 집합으로 합치는 union을 수행
            for(int[] cost: costs) {
                if (count == n - 1) {
                    break;
                }
                int a = cost[0], b = cost[1], distance = cost[2];
                int rootA = find(parent, a), rootB = find(parent, b);
                // 두 노드가 순환 관계인 경우
                // 두 노드의 루트가 동일할 경우 순환관계임.
                if (rootA == rootB) {
                    continue;
                }
                // 비순환 관계일 경우, 비용을 더해주고, 두 노드의 집합에 대해 유니온 연산을 수행
                answer += distance;
                union(parent, a, b);
            }
            return answer;
        }

        private int find(int[] parent, int node) {
            /* 집합의 루트 노드 찾는 함수 */
            // 현재 노드의 부모가 자기 자신이면, 루트 노드임
            if (parent[node] == node) {
                return node;
            }
            // 현재 노드의 부모를 인자로 넣어 재귀호출,
            // 결국엔 루트 노드가 리턴될 것임
            return find(parent, parent[node]);
        }

        private void union(int[] parent, int a, int b) {
            // 두 집합을 합치기
            // 한 쪽 집합의 루트 노드의 부모 노드를 다른 쪽 집합의 루트 노드로 바꿔줌
            int rootA = find(parent, a);
            int rootB = find(parent, b);

            if (rootA < rootB) {
                parent[rootB] = rootA;
                return;
            }
            parent[rootA] = rootB;
        }
    }
}
