class Solution {
    boolean[] opened, selected;
    int[] select;
    int answer, n;
    
    public int solution(int[] cards) {
        n = cards.length;
        
        selected = new boolean[n+1];
        select = new int[2];
        findBestScore(0, cards);
        
        return answer;
    }
    
    void findBestScore(int count, int[] cards) {
        if (count >= 2) {
            opened = new boolean[n];
            int trial1 = getCards(0, select[0] - 1, cards);
            int trial2 = getCards(0, select[1] - 1, cards);            
            
            answer = Math.max(answer, trial1 * trial2);
            return;
        }
        
        // 상자 번호 1 ~ size 중 택 2
        for (int i = 1; i <= n; i++) {
            if (selected[i]) continue;
            
            selected[i] = true;
            select[count] = i;
            findBestScore(count + 1, cards);
            selected[i] = false;
        }
    }
    
    int getCards(int score, int idx, int[] cards) {
        if (opened[idx]) {
            return score;
        }
        
        opened[idx] = true;
        return getCards(score + 1, cards[idx] - 1, cards);
    }
}