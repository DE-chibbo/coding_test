package string.KimJunYoung;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_Q1781_ {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.beautySum("aabcbaa"));
    }
    static class Solution {
        public int beautySum(String s) {
            int sum = 0;
            // O(N^2)
            for (int i = 0; i < s.length(); i++) {
                Map<Character, Integer> map = new HashMap<>();
                for (int j = i; j < s.length(); j++) {
                    char ch = s.charAt(j);
                    if (map.containsKey(ch)) {
                        map.put(ch, map.get(ch) + 1);
                    }
                    else {
                        map.put(ch, 1);
                    }
                    int maxFrequency = 0;
                    int minFrequency = 500;
                    for (char key :
                            map.keySet()) {
                        int count = map.get(key);
                        maxFrequency = Math.max(maxFrequency, count);
                        minFrequency = Math.min(minFrequency, count);
                    }
                    sum += (maxFrequency - minFrequency);
                }
            }
            return sum;
        }
    }

    static class Solution2 {
        public int beautySum(String s) {
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                int[] counts = new int[26];
                for (int j = i; j < s.length(); j++) {
                    counts[s.charAt(j) - 'a']++;
                    int maxFrequency = 0;
                    int minFrequency = 500;
                    for (int count :
                            counts) {
                        if (count> 0 ) {
                            maxFrequency = Math.max(maxFrequency, count);
                            minFrequency = Math.min(minFrequency, count);
                        }
                    }
                    sum += (maxFrequency - minFrequency);
                }
            }
            return sum;
        }
    }
}
