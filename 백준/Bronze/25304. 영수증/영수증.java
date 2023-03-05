// 영수증

import java.util.Scanner;

class Main {      
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // 총 금액
        int total = input.nextInt();
        
        // 합산할 금액
        int sum = 0;
        
        // 산 물품의 수
        int items = input.nextInt();
        
        for (int i = 1; i <= items; i++){
            int item = input.nextInt();
            int n = input.nextInt();
            
            sum += item * n;
            
        }
        
        if (sum == total) System.out.println("Yes");
        else System.out.println("No");
        
    }
    
}