import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int INF = Integer.MAX_VALUE;

        // dp[a] = A의 흔적이 a일때 B의 최소흔적
        int[] dp = new int[n];
        Arrays.fill(dp,INF);
        dp[0] = 0;

        for(int i=0; i<info.length; i++){
            int[] next = new int[n];
            Arrays.fill(next,INF);

            int a = info[i][0];
            int b = info[i][1];
            for(int j=0; j<n; j++){
                if(dp[j] == INF)
                    continue;

                int totalA = j + a;
                if(totalA < n){
                    next[totalA] = Math.min(next[totalA],dp[j]);
                }
                
                int totalB = dp[j] + b;
                if(totalB < m){
                    next[j] = Math.min(next[j],totalB);
                }
            }
            dp = next;
        }
        
        for(int i=0; i<n; i++){
            if(dp[i] != INF){
                return i;
            }
        }

        return -1;
    }
}