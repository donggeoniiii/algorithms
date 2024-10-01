class Solution {
    static int n;
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        n = diffs.length;
        
        int start = 1;
        int end = 100000;

        while (start <= end) {
            int mid = (start + end) / 2;

            // 해당 숙련도로 통과했으면 더 낮은 숙련도로 가능한지 테스트
            if (solved(diffs, times, mid, limit)) {
                answer = mid;
                end = mid - 1;
            }

            // 해당 숙련도로 통과 못했으면 더 높은 숙련도로 가능한지 테스트
            else start = mid + 1;
        }   
    

    return answer;
    }
    
    static boolean solved(int[] diffs, int[] times, int level, long limit) {
        
        // diff[0] = 1, 무조건 한번에 클리어
        long totalTime = times[0];
        
        for (int i = 1; i < n; i++) {
            int prevTime = times[i-1];
            int curTime = times[i];
            int curDiff = diffs[i];
            
            if (level >= curDiff) {
                totalTime += curTime;
            }
            else {
                totalTime += (curDiff - level) * (prevTime + curTime) + curTime;
            }
            
            if (totalTime > limit) return false;
        }
        
        return true;
    }    
}