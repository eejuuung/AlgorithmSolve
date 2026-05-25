import java.io.*;
import java.util.*;

class Solution {
    
    static String[][] park;
    
    public int solution(int[] mats, String[][] park) {
        this.park = park;
        int answer = -1;
        int size = mats.length;
        int pY = park.length;
        int pX = park[0].length;
        
        Arrays.sort(mats);
        
        for(int i=0; i<pY; i++){
            for(int j=0; j<pX; j++){
                for(int k=size-1; k>=0; k--){
                     if(park[i][j].equals("-1") && i+mats[k]<=pY && j+mats[k]<=pX){
                         if(checkMat(i,j,mats[k])){
                             answer = Math.max(answer,mats[k]);
                         }
                    }
                }
            }
        }
        
        
        
        
        
        return answer;
    }
    
    static boolean checkMat(int y, int x, int k){
        for(int i=y; i<y+k; i++){
            for(int j=x; j<x+k; j++){
                if(!park[i][j].equals("-1")){
                    return false;
                }
            }
        }
        return true;
    }
}