import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int n = genres.length;
        
        Map<String, Integer> genrePlay = new HashMap<>();
        Map<String, PriorityQueue<int[]>> genreMusics = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            String genre = genres[i];
            int play = plays[i];
            genrePlay.put(genre, genrePlay.getOrDefault(genre, 0) + play);
            
            PriorityQueue<int[]> musics = genreMusics.getOrDefault(
                genre, new PriorityQueue<>(Comparator.comparingInt((int[] o) -> -o[1])
                                                .thenComparingInt(o -> o[0])));
            
            musics.offer(new int[]{i, play});
            
            genreMusics.put(genre, musics);
        }
        
        List<Integer> answer = new ArrayList<>();
        
        // 출력을 위해 총 재생수 기준으로 정렬
        List<String> keys = new ArrayList<>(genreMusics.keySet());
        
        keys.sort((o1, o2) -> genrePlay.get(o2).compareTo(genrePlay.get(o1)));
        
        for (String genre : keys) {
            PriorityQueue<int[]> musics = genreMusics.get(genre);
            answer.add(musics.poll()[0]);
            
            if (!musics.isEmpty()) answer.add(musics.poll()[0]);
        }
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}