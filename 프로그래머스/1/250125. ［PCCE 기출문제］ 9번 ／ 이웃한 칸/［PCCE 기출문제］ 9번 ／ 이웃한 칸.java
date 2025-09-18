class Solution {
    static int[] dh = {-1, 1, 0, 0};
    static int[] dw = {0, 0, -1, 1};
    
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        String targetColor = board[h][w];
        for (int dt = 0; dt < 4; dt++) {    
            int nh = h + dh[dt];
            int nw = w + dw[dt];
            
            if (nh >= board.length || nw >= board.length || nh < 0 || nw < 0) continue;
            
            if (board[nh][nw].equals(targetColor)) answer++;
        }
        
        return answer;
    }
}