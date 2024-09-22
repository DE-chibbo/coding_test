package bruteforce.KimJunYoung;

public class ProgrammersBruteforce7 {
    public static void main(String[] args) {
        Solution solved = new Solution();
        String word1 = "AAAAE";
        System.out.println(solved.solution(word1) == 6);
        solved = new Solution();
        String word2 = "I";
        System.out.println(solved.solution(word2) == 1563);
    }
    static class Solution {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int answer = 0;
        public int solution(String word) {
            char[] vowels = {'A', 'E', 'I', 'O', 'U'};
            getIndex(word, vowels);
            return answer;
        }

        public void getIndex(String word, char[] vowels){
            if (word.contentEquals(sb)) {
                answer = index;
                return;
            }
            if (sb.length() == 5) {
                return;
            }

            for (int i = 0; i < 5; i++) {
                sb.append(vowels[i]);
                index++;
                getIndex(word, vowels);
                sb = new StringBuilder(sb.substring(0, sb.length() - 1));
            }

        }
    }
}
