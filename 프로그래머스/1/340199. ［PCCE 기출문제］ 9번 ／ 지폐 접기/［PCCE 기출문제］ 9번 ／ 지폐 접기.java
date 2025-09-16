class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        while (!isInPocket(wallet, bill)) {
            if (bill[0] >= bill[1]) bill[0] /= 2;
            else bill[1] /= 2;
            
            answer++;
        }
        
        return answer;
    }
    
    // 돌려서라도 넣을 수 있는지 확인하기
    boolean isInPocket(int[] wallet, int[] bill) {
        int wr = wallet[0];
        int wc = wallet[1];
        int br = bill[0];
        int bc = bill[1];
        
        if (wr >= br && wc >= bc) return true;
        
        if (wc >= br && wr >= bc) return true;
        
        return false;
    }
}