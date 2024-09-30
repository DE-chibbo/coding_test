package greedy.KimJunYoung;

public class Programmers2 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        String name1 = "JAAAAAN";
        System.out.println(solved.solution(name1) == 23);

        String name2 = "JNAAAAAA";
        System.out.println(solved.solution(name2) == 23);

        String name3 = "JNAAAAAAN";
        System.out.println(solved.solution(name3) == 38);
    }
    static class Solution {
        public int solution(String name) {
            int answer = 0, beforeNotA = 0, cnt = Integer.MAX_VALUE;

            for (int i = 0; i < name.length(); i++) {
                char ch = name.charAt(i);
                if (name.charAt(i) != 'A') {
                    answer += Math.min('Z' - ch + 1, ch - 'A');

                    if (i == 0) continue;
                    // tmp = 이전에 'A'가 아니었던 문자까지 이동한 횟수
                    // + 'A'가 아닌 현재 가리키는 문자 인덱스로 끝에서부터 역방향으로 이동한 횟수
                    int tmp = beforeNotA + name.length() - i;

                    /*
                    cnt = 좌우 최소 이동 횟수를 의미하며,
                    cnt를 구하는 과정은 'A'가 아닌 문자 사이에 무조건 'A'가 있다는 전제하에 구함
                    따라서 "JNAAAA" == 23 같은 경우는
                    마지막 'A'가 아닌 문자까지 이동한 횟수와 cnt 정방향, 역방향으로 이동해가며 구한 최소 이동 횟수 중
                    최솟 값으로 헤야함

                    cnt를 구하는 로직은 다음과 같음
                    안쪽의 min()은 현재 'A'가 아닌 문자에 도달할 수 있는 좌우 최소 이동 횟수를 의미함.
                    즉, 루프를 돌며, 'A'가 아닌 임의의 문자가 제일 마지막으로 탐색됐다고 가정했을 떄,
                    가장 적은 횟수로 도달할 수 있는, 첫번째가 아닌 문자가 마지막으로 변형해야할 문자를 의미하며,
                    반복문 내에서 구한 cnt는 결국 마지막으로 변형한 문자까지 좌우로 최소 이동한 횟수를 의미함.
                    */
                    cnt = Math.min(cnt, Math.min(tmp + beforeNotA, tmp + name.length() - i));

                    // beforeNotA = 이전에 'A가' 아니었던 문자의 인덱스
                    beforeNotA = i;
                }
            }
            // 'A'가 마지막 루프에서의 마지막 문자 이후에 나오기 시작한 경우
            // 예시: "JNAAAA" == 23 이어야함
            cnt = Math.min(beforeNotA, cnt);
            return answer+cnt;
        }
    }
}
