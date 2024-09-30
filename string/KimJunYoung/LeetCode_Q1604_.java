package string.KimJunYoung;

import java.util.*;


public class LeetCode_Q1604_ {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // tests
        String[] keyName1 = {"daniel","daniel","daniel","luis","luis","luis","luis"};
        String[] keyTime1 = {"10:00","10:40","11:00","09:00","11:00","13:00","15:00"};
        System.out.println(solution.alertNames(keyName1, keyTime1));

        String[] keyName2 = {"alice","alice","alice","bob","bob","bob","bob"};
        String[] keyTime2 = {"12:01","12:00","18:00","21:00","21:20","21:30","23:00"};
        System.out.println(solution.alertNames(keyName2, keyTime2));

        String[] keyName4 = {"john","john","john"};
        String[] keyTime4 = {"23:58","23:59","00:01"};
        System.out.println(solution.alertNames(keyName4, keyTime4));

        String[] keyName5 = {"a","a","a","a","a","a","b","b","b","b","b"};
        String[] keyTime5 = {"23:27","03:14","12:57","13:35","13:18","21:58","22:39","10:49","19:37","14:14","10:41"};
        System.out.println(solution.alertNames(keyName5, keyTime5));

        String[] keyName3 = {"d", "f", "f", "f", "c", "c", "c", "c", "c"};
        String[] keyTime3 = {"12:00", "09:12", "09:38", "10:12", "08:00", "08:30", "09:01", "09:30", "10:01"};
        System.out.println(solution.alertNames(keyName3, keyTime3));

    }
    static class Solution {
        public List<String> alertNames(String[] keyName, String[] keyTime){
            // keyTime values are not sorted
            Map<String, List<Integer>>  nameTimestamps = new HashMap<>();
            for (int i = 0; i < keyName.length; i++) {
                String name = keyName[i];
                String time = keyTime[i];
                // convert time to minutes
                int minute = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
                nameTimestamps.computeIfAbsent(name, k -> new ArrayList<>()).add(minute);
            }
            List<String> alertNames = new ArrayList<>();
            // might be O(NlogN)
            for (var pair : nameTimestamps.entrySet()) {
                List<Integer> minutes = pair.getValue();
                if (minutes.size() < 3) {
                    continue;
                }
                // sort time values
                minutes.sort(Comparator.naturalOrder());
                for (int i = 2; i < minutes.size(); i++) {
                    int endTime = minutes.get(i);
                    int startTime = minutes.get(i - 2);
                    if (endTime - startTime <= 60) {
                        alertNames.add(pair.getKey());
                        break;
                    }
                }
            }
            alertNames.sort(Comparator.naturalOrder());
            return alertNames;
        }
    }
}
