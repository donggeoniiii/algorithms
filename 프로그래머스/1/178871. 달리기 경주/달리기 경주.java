import java.util.*;

class Solution {
    Map<String, Integer> playerRank;
    
    void init(String[] players) {
        playerRank = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            playerRank.put(players[i], i);
        }
    }
    
    public String[] solution(String[] players, String[] callings) {
        init(players);
        
        for (String name : callings) {
            overtake(name, players);
        }
        
        return players;
    }
    
    void overtake(String name, String[] players) {
        int cur = playerRank.get(name);
        
        playerRank.put(name, cur-1);
        
        String temp = players[cur];
        players[cur] = players[cur-1];
        players[cur-1] = temp;
        
        playerRank.put(players[cur], cur);
    }
}