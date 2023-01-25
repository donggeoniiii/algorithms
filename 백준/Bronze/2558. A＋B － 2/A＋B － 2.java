import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        int A = input.nextInt();
        input.nextLine(); // 개행문자 제거(enter로 인한 오입력 방지)
        int B = input.nextInt();
        
        System.out.println(A+B);
    }

}
