import java.io.*;
import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int dEnd = n-1;
        int pEnd = n-1;
        
        while(dEnd>=0 || pEnd >=0){
            
            while (dEnd >= 0 && deliveries[dEnd] == 0) {
                dEnd--;
            }
            
            while (pEnd >= 0 && pickups[pEnd] == 0) {
                pEnd--;
            }
            
            if (dEnd < 0 && pEnd < 0)
                break;
            
            answer += (Math.max(dEnd, pEnd) + 1) * 2L;
            
            // 가져갈 택배체크
            int total = 0;
            for(int d = dEnd; d>=0; d--){
                total += deliveries[d];
                if(total>cap){
                    deliveries[d] =total - cap;
                    break;
                }
                deliveries[d] = 0;
            }
            
            // 수거할 택배 체크
            total = 0;
            for(int p=pEnd; p>=0; p--){
                total+=pickups[p];
                
                if(total>cap){
                    pickups[p] = total-cap;
                    break;
                }
                pickups[p] = 0;
            }
        }
        
        return answer;
    }
}