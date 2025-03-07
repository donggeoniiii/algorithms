class Solution {
    public int[] solution(int brown, int yellow) {
        
        int total = brown + yellow;
        
        return solve(total, brown, 1);
    }
    
    static int[] solve(int total, int brown, int div) {
        if (total % div != 0) {
            return solve(total, brown, div+1);
        }
        
        int row = total / div;
        int col = div;
        
        if (row < col) return new int[] {-1, -1};
        
        if ((2 * (row + col) - 4) == brown) {
            return new int[]{row, col};
        } else {
            return solve(total, brown, div+1);   
        }
        
    }
}