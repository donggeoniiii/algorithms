// 문자와 문자열

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String s = input.next();
        int i = input.nextInt();
        System.out.println(s.charAt(i-1));
    }
}