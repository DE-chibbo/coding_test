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
        boolean isDone = false;
//        int answer = 0;
        public int solution(String word) {
            char[] vowels = {'A', 'E', 'I', 'O', 'U'};
            int answer = getIndex(word, vowels, 0);
            return answer;
        }

        public int getIndex(String word, char[] vowels, int index){
            if (word.contentEquals(sb)) {
                isDone = true;
                return index;
            }
            if (sb.length() == 5) {
                return index;
            }

            for (int i = 0; i < 5; i++) {
                sb.append(vowels[i]);
                index++;
                index = getIndex(word, vowels, index);
                if (isDone) {
                    return index;
                }
                sb = new StringBuilder(sb.substring(0, sb.length() - 1));
            }
            return index;
        }
    }
}
