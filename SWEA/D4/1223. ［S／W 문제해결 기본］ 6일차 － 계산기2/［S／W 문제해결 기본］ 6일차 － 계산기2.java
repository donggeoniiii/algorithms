// 계산기2
 
import java.util.Scanner;
import java.util.Stack;
 
/*
* 중위표기식(A+B)을 후위표기식(AB+)로 바꾸어 계산
* stack 활용
* 숫자는 바로 print, 여는 괄호와 연산자는 우선순위가 낮은 값이 top에 올 때까지 pop & push
* 우선순위: 들어있는 괄호 < 더하기 빼기 < 곱하기 나누기 < 안들어간 괄호
* 같은 값끼리는 우선순위가 더 낮은 기호가 나올 때까지/stack이 빌 때까지 pop
*/
 
public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack<Character> signStack;
        Stack<Integer> numStack; 
        StringBuilder sb;
 
        // 10개의 testcase
        for (int tc = 1; tc <= 10; tc++) {
            // StringBuilder로 postfix 만들기
            sb = new StringBuilder();
 
            // 문자열 계산식의 길이(연산자 & 숫자 포함)
            int L = Integer.parseInt(input.nextLine());
 
            // 계산식 변환을 위한 2개의 stack
            signStack = new Stack<>();
            numStack = new Stack<>();
 
            // 문자열을 입력받고 거기에 있는 문자 하나하나당 비교
            for (char c : input.nextLine().toCharArray()) {
 
               // 만약 문자가 더하기 연산자면
               if (c == '+') {

            	   // 비었을 때는 스택에 push
                   if (signStack.isEmpty()) signStack.push(c);
                   
                   // 안 비었으면 하나 pop해서 postfix에 넣은 후에 push
                   else {
                       sb.append(signStack.pop());
                       signStack.push(c);
                   }

               }
 
                // 만약 문자가 곱하기 연산자면 스택에 push
                else if (c == '*') {
 
                    // 비었을 때는 스택에 push
                    if (signStack.isEmpty()) signStack.push(c);
 
                    // 안 비었으면 
                    else {
												// top이 더하기인 경우 그냥 push
                        if (signStack.peek() == '+') signStack.push(c);
												// 곱하기인 경우 하나 pop해서 postfix에 넣은 후에 push
                        else {
                            sb.append(signStack.pop());
                            signStack.push(c);
                        }
                    }
 
                }
 
                // 만약 문자가 숫자면 그대로 postfix에 추가
                else sb.append(c);
 
            }
 
            // 아직 나오지 않은 연산자 입력
            while(!signStack.isEmpty()) {
                sb.append(signStack.pop());
            }
 
            // postfix한 식 확인
            // System.out.println(sb.toString());
 
            // 후위표기식 계산
            for (String s : sb.toString().split("")) {
 
                // 만약 문자가 연산자면 숫자 두개 뽑아서 계산하고 다시 push
                if (s.equals("+")) numStack.push(numStack.pop() + numStack.pop());
                else if (s.equals("*")) {
                    numStack.push(numStack.pop() * numStack.pop());
                }
 
                // 만약 문자가 숫자면 push
                else numStack.push(Integer.parseInt(s));
            }
 
            // 최종적으로 남은 수로 정답 출력
            System.out.printf("#%d %d\n", tc, numStack.peek());
 
        }
 
    }
}