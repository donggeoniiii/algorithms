// 나머지

import java.util.Scanner;

// 첫째 줄에 (A+B)%C, 둘째 줄에 ((A%C) + (B%C))%C, 셋째 줄에 (A×B)%C, 넷째 줄에 ((A%C) × (B%C))%C를 출력한다.

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        // 입력받기
        int A = input.nextInt();
        int B = input.nextInt();
        int C = input.nextInt();
        
        // (A+B)%C
        System.out.println((A+B)%C);
        
        // ((A%C) + (B%C))%C
        System.out.println(((A%C) + (B%C))%C);
        
        // (A*B)%C
        System.out.println((A*B)%C);
        
        // ((A%C) * (B%C))%C
        System.out.println(((A%C) * (B%C))%C);
        
        
    }
}
