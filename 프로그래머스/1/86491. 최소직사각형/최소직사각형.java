import java.util.*;

class Solution {
    public int solution(int[][] sizes) { 
        int rowMax = -1;
        int colMax = -1;
        
        for (int i = 0; i < sizes.length; i++) {
            int row = sizes[i][0];
            int col = sizes[i][1];
        
            // 가로, 세로중 긴 쪽이 가로가 되게 길이 맞춰서 비교 
            if (row >= col) {
                rowMax = Math.max(rowMax, row);
                colMax = Math.max(colMax, col);
            } else {
                rowMax = Math.max(rowMax, col);
                colMax = Math.max(colMax, row);
            }
        }
        
        return rowMax * colMax;
    }
}