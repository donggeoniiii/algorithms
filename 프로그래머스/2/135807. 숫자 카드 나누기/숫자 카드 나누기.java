class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int n = arrayA.length;
        
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        
        for (int i = 1; i < n; i++) {
            gcdA = findGcd(gcdA, arrayA[i]);
            gcdB = findGcd(gcdB, arrayB[i]);
        }
        
        if (isDivided(gcdA, arrayB)) {
            gcdA = 0;
        }
        
        if (isDivided(gcdB, arrayA)) {
            gcdB = 0;
        }
        
        return (gcdA == 0 && gcdB == 0) ? 0 : Math.max(gcdA, gcdB);
    }

    // 최대공약수 찾기
    int findGcd(int a, int b) {
        return (b == 0) ? a : findGcd(b, a % b);
    }
    
    // 상대편 카드 중 나뉘는게 있는지
    boolean isDivided(int num, int[] arr) {
        for (int x : arr) {
            if (x % num == 0) return true;
        }
        
        return false;
    }
}