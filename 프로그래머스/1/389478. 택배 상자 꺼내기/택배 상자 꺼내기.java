class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        int h = n / w + ((n % w == 0) ? 0 : 1);
        int[][] arr = new int[h][w];
        
        
        // 상자가 몇 개 쌓여있나 == (배열에서) 밑에 몇 개 있나
        int box = 1;
        boolean flag = false;
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                arr[r][c] = box++;
            }
            
            if (flag) {
                swap(arr[r]);
                flag = false;
            } else {
                flag = true;
            }
        }
        
        int tr = (num - 1) / w;
        int tc = (num - 1) % w;
        if (tr % 2 == 1) {
            tc = (w - 1) - tc;
        }
    
        for (int r = tr; r < h; r++) {
            if (arr[r][tc] <= n) answer++;        
        }
        
        return answer;
    }
    
    void swap(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n-1-i];
            arr[n-1-i] = temp;
        }
    }
}