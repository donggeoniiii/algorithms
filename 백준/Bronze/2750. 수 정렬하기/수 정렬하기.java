// 수 정렬하기

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        // 수의 개수
        int n = input.nextInt();
        
        // 저장할 ArrayList
        ArrayList<Integer> list = new ArrayList<>();
        
        // 저장
        for (int i = 0; i < n; i++) {
            list.add(input.nextInt());
        }
        
        // 정렬
        Collections.sort(list);
        
        // 출력
        for (int i : list)
            sb.append(i).append("\n");

        System.out.println(sb);
    }
}