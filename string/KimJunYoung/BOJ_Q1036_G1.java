package string.KimJunYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_Q1036_G1 {
    static BigInteger Z = new BigInteger("Z", 36);
    static BigInteger THIRTY_SIX = new BigInteger("10", 36);

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        List<String> numbers = new ArrayList<>();
        Map<String, BigInteger> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String number = bufferedReader.readLine();
            numbers.add(number);
            mapDigitWithProfit(number, map);
        }

        int K = Integer.parseInt(bufferedReader.readLine());
        Set<String> digitSet = getTopKDigits(map, K);
        List<String> convertedNumbers = getConvertedNumbers(numbers, digitSet);

        BigInteger result = new BigInteger("0", 36);
        for (String number: convertedNumbers) {
            BigInteger num36 = new BigInteger(number, 36);
            result = result.add(num36);
        }
        System.out.println(result.toString(36).toUpperCase());
    }

    private static void mapDigitWithProfit(String number, Map<String, BigInteger> map) {
        for (int j = 0; j < number.length(); j++) {
            String digitString = String.valueOf(number.charAt(j));
            BigInteger digit = new BigInteger(digitString, 36);
            int pow = number.length() - 1 - j;
            BigInteger offset = Z.subtract(digit);
            BigInteger profit = THIRTY_SIX.pow(pow).multiply(offset);
            map.merge(digitString, profit, BigInteger::add);
        }
    }

    private static Set<String> getTopKDigits(Map<String, BigInteger> map, int K) {
        List<String> digits = new ArrayList<>(map.keySet());
        digits.sort(((o1, o2) -> map.get(o2).compareTo(map.get(o1))));
        if (K > digits.size()) {
            K = digits.size();
        }
        return new HashSet<>(digits.subList(0, K));
    }

    private static List<String> getConvertedNumbers(List<String> numbers, Set<String> digitSet) {
        List<String> convertedNumbers = new ArrayList<>();
        for (String number : numbers) {
            char[] numberDigits = new char[number.length()];
            for (int i = 0; i < number.length(); i++) {
                String digit = String.valueOf(number.charAt(i));
                if (digitSet.contains(digit)) {
                    numberDigits[i] = 'Z';
                    continue;
                }
                numberDigits[i] = number.charAt(i);
            }
            convertedNumbers.add(new String(numberDigits));
        }
        return convertedNumbers;
    }
}
