import java.util.*;

class Solution {
    static int n;
    
    static boolean[] isPrime;
    
    static Set<Integer> answers;
    
    public int solution(String numbers) {
        int answer = 0;
        
        String[] nums = numbers.split("");
        n = nums.length;
        
        // 조합으로 만들 수 있는 가장 큰 수 -> 9,999,999
        int maxNum = 9_999_999;
        
        // 나올 수 있는 숫자 범위 내 소수 탐색
        findPrimeNumbers(maxNum);
        
        // 만들 수 있는 모든 경우의 수 중 소수인 경우 탐색
        answers = new HashSet<>();
        findCombinations("", numbers);
        
        return answers.size();
    }
    
    // 소수 판독기
    static void findPrimeNumbers(int max) {

        isPrime = new boolean[max+1];
        Arrays.fill(isPrime, true);
        
        isPrime[0] = false;
        isPrime[1] = false;
        
        for (int i = 2; i * i <= max; i++) {
            
            // 해당 수가 이미 합성수이면 넘어감
            if (!isPrime[i]) continue;
            
            // 소수의 배수는 소수가 될 수 없음
            for (int j = i*i; j <= max; j += i) {
                isPrime[j] = false;
            }
        }
    }
    
    static void findCombinations(String cur, String rest) {
        if (!cur.isEmpty()) {
            int curNum = Integer.parseInt(cur);
            
            if (isPrime[curNum]) {
                answers.add(curNum);
            }
        }
        
        for (int i = 0; i < rest.length(); i++) {
            findCombinations(cur + rest.charAt(i), rest.substring(0, i) + rest.substring(i+1));
        }
    }
}