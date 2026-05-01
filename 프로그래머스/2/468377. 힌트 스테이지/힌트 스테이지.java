import java.util.*;

class Solution {

    static int[][] cost;
    static int[][] hint;

    static int answer;

    public static void dfs(int stage, int totalCost, int[] hintNum) {
        if (totalCost >= answer)
            return;

        if (stage == cost.length) {
            answer = Math.min(answer, totalCost);
            return;
        }
        
        int use = Math.min(hintNum[stage], cost[stage].length - 1);
        int nextCost = totalCost + cost[stage][use];

        // 번들 구매 X
        dfs(stage + 1, nextCost, hintNum);

        // 번들 구매 O
        if (stage < hint.length) {
            for (int k = 1; k < hint[stage].length; k++) {
                hintNum[hint[stage][k] - 1]++;
            }

            dfs(stage + 1,
                nextCost + hint[stage][0],
                hintNum);

            for (int k = 1; k < hint[stage].length; k++) {
                hintNum[hint[stage][k] - 1]--;
            }
        }
    }

    public int solution(int[][] cost, int[][] hint) {

        Solution.cost = cost;
        Solution.hint = hint;

        answer = Integer.MAX_VALUE;

        dfs(0, 0, new int[cost.length]);

        return answer;
    }
}