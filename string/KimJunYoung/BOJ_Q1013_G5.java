package string.KimJunYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Q1013_G5 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String wave = bufferedReader.readLine();
            if (isVega(wave)) {
                result.append("YES").append(System.lineSeparator());
                continue;
            }
            result.append("NO").append(System.lineSeparator());
        }
        System.out.println(result);
    }

    private static boolean isVega(String wave) {
        String pattern = "(100+1+|01)+";
        return wave.matches(pattern);
    }
}
