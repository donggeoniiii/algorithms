class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // 3진법으로 전환
        String s = Integer.toString(n, 3);
        
        // 뒤집지 말고 그대로 앞자리부터 계산해주면 됨
        for (int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - '0';
            
            answer += cur * ((int) Math.pow(3, i));
        }
        
        return answer;
    }
}