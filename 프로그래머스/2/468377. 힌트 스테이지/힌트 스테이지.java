import java.io.*;
import java.util.*;

class Solution {

    static int[][] cost;
    static int[][] hint;
    
    static int answer;
    
    static HashMap<String, Integer> memo;

    public static void dfs(int stage, int totalCost, int bundle) {
        if (stage == cost.length) {
            answer = Math.min(answer, totalCost);
            return;
        }

        if (totalCost >= answer)
            return;
        
        String key = stage + "," + bundle;
        if(memo.containsKey(key) && memo.get(key)<=totalCost)
            return;
        memo.put(key,totalCost);

        // 구매한 번들을 기준으로 현재stage의 힌트갯수 체크
        int maxUse = 0;
        for(int i = 0; i < hint.length; i++){
            if((bundle & (1<<i)) == 0)
                continue;
            
            for(int j=1; j < hint[i].length; j++){
                if(hint[i][j] == stage+1)
                    maxUse++;
            }
        }
        maxUse = Math.min(maxUse, cost[stage].length-1);
        
        for (int use = 0; use <= maxUse; use++) {
            int nextCost = totalCost + cost[stage][use];
            
            // 번들 구매 X
            dfs(stage + 1, nextCost, bundle);

            // 번들 구매 O
            if(stage < hint.length){
                dfs(stage + 1, nextCost + hint[stage][0], bundle | (1<<stage));
            }
        }
    }

    public int solution(int[][] cost, int[][] hint) {
        Solution.cost = cost;
        Solution.hint = hint;
        
        answer = Integer.MAX_VALUE;
        
        memo = new HashMap<>();

        dfs(0, 0, 0);

        return answer;
    }
}