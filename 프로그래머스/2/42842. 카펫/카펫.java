class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        
        int total = brown + yellow;
        
        for (int col = 1; col <= total / col; col++) {
            if (total % col != 0) continue;
            
            int row = total / col;
            
            if (2 * (row + col) - 4 == brown) {
                answer = new int[]{row, col};
            }
        }
        
        return answer;
    }
}