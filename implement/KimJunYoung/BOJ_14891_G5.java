package implement.KimJunYoung;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891_G5 {
    /*
    백준 문제 14891번: 톱니바퀴
     */
    private static String[] wheels;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        wheels = new String[5];
        for (int i = 1; i <= 4; i++) {
            wheels[i] = bufferedReader.readLine();
        }
        int K = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int wheelNumber = Integer.parseInt(stringTokenizer.nextToken());
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            rotateWheels(wheelNumber, direction);
        }
        int score = 0;
        for (int i = 1; i <= 4; i++) {
            if (wheels[i].charAt(0) == '1') {
                score += (int) Math.pow(2, i - 1);
            }
        }
        System.out.println(score);
    }

    private static void rotateWheels(int wheelNumber, int direction) {
        switch (wheelNumber) {
            case 1:
                wheel1Rotate(direction);
                break;
            case 2:
                wheel2Rotate(direction);
                break;
            case 3:
                wheel3Rotate(direction);
                break;
            case 4:
                wheel4Rotate(direction);
                break;
        }


    }

    // index of string 2: right rotating teeth
    // index of string 6: left rotating teeth
    private static void wheel1Rotate(int direction) {
        if (
                wheels[1].charAt(2) != wheels[2].charAt(6) &&
                        wheels[2].charAt(2) != wheels[3].charAt(6) &&
                        wheels[3].charAt(2) != wheels[4].charAt(6)
        ) {
            rotate(4, direction * -1);
        }
        if (
                wheels[1].charAt(2) != wheels[2].charAt(6) &&
                        wheels[2].charAt(2) != wheels[3].charAt(6)
        ) {
            rotate(3, direction);
        }
        if (wheels[1].charAt(2) != wheels[2].charAt(6)) {
            rotate(2, direction * -1);
        }
        rotate(1, direction);
    }

    private static void wheel2Rotate(int direction) {
        if (
                wheels[2].charAt(2) != wheels[3].charAt(6) &&
                        wheels[3].charAt(2) != wheels[4].charAt(6)
        ) {
            rotate(4, direction);
        }
        if (wheels[2].charAt(2) != wheels[3].charAt(6)) {
            rotate(3, direction * -1);
        }
        if (wheels[2].charAt(6) != wheels[1].charAt(2)) {
            rotate(1, direction * -1);
        }
        rotate(2, direction);
    }

    private static void wheel3Rotate(int direction) {
        if (
                wheels[3].charAt(6) != wheels[2].charAt(2) &&
                        wheels[2].charAt(6) != wheels[1].charAt(2)
        ) {
            rotate(1, direction);
        }
        if (wheels[3].charAt(2) != wheels[4].charAt(6)) {
            rotate(4, direction * -1);
        }
        if (wheels[3].charAt(6) != wheels[2].charAt(2)) {
            rotate(2, direction * -1);
        }
        rotate(3, direction);
    }

    private static void wheel4Rotate(int direction) {
        if (
                wheels[4].charAt(6) != wheels[3].charAt(2) &&
                        wheels[3].charAt(6) != wheels[2].charAt(2) &&
                        wheels[2].charAt(6) != wheels[1].charAt(2)
        ) {
            rotate(1, direction * -1);
        }
        if (
                wheels[4].charAt(6) != wheels[3].charAt(2) &&
                        wheels[3].charAt(6) != wheels[2].charAt(2)
        ) {
            rotate(2, direction);
        }
        if (wheels[4].charAt(6) != wheels[3].charAt(2)) {
            rotate(3, direction * -1);
        }
        rotate(4, direction);
    }

    private static void rotate(int wheelNumber, int direction) {
        StringBuilder wheelBuilder = new StringBuilder();
        if (direction == 1) { // clockwise
            String wheel = wheels[wheelNumber];
            wheelBuilder.append(wheel.charAt(wheel.length() - 1));
            for (int i = 0; i < wheel.length() - 1; i++) {
                wheelBuilder.append(wheel.charAt(i));
            }
            wheels[wheelNumber] = wheelBuilder.toString();
            return;
        }
        if (direction == -1) {// counterclockwise
            String wheel = wheels[wheelNumber];
            wheelBuilder.append(wheel.charAt(1));
            for (int i = 2; i < wheel.length(); i++) {
                wheelBuilder.append(wheel.charAt(i));
            }
            wheelBuilder.append(wheel.charAt(0));
            wheels[wheelNumber] = wheelBuilder.toString();
        }
    }
}

