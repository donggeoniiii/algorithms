class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        long dist = (long)d;
        for (int x = 0; x <= dist; x += k) {
            long y = (long) Math.sqrt(Math.pow(dist, 2) - Math.pow(x, 2));
            answer += (long)y / k + 1;
        }
        
        
        return answer;
    }
}