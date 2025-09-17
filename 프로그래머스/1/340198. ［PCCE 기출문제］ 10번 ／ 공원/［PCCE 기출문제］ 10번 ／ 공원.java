class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        
        for (int i = 0; i < mats.length; i++) {
            int l = mats[i];
            
            // 빈자리 찾아서 최댓값 비교
            if (hasSpace(l, park) && l > answer) answer = l;
        }
        
        return answer;
    }
    
    boolean hasSpace(int l, String[][] park) {
        for (int r = 0; r < park.length; r++) {
            for (int c = 0; c < park[0].length; c++) {
                if (!park[r][c].equals("-1")) continue;
                
                if (clear(r, c, l, park)) return true;
            }
        }
        
        return false;
    }
    
    boolean clear(int r, int c, int l, String[][] park) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                int nr = r + i;
                int nc = c + j;
                
                // out of index
                if (nr >= park.length || nc >= park[0].length) return false;
                
                if (!park[nr][nc].equals("-1")) return false;
            }
        }
        
        return true;
    }
}