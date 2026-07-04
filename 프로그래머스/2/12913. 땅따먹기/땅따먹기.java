import java.io.*;
import java.util.*;

class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int[][] dp = new int[land.length][4];
        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];

        for(int i=1; i<land.length; i++){
            for(int j=0; j<4; j++){
                int maxD = 0;
                for(int k=0; k<4; k++){
                    if(j==k)
                        continue;
                    maxD = Math.max(land[i][j] + dp[i-1][k], maxD);
                }
                dp[i][j] = maxD;
            }
        }
        
        for(int i=0; i<4; i++){
            answer = Math.max(answer, dp[land.length-1][i]);
        }

        return answer;
    }
}