import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> hash = new HashMap<>();
        int leng = friends.length;
        int[][] arr = new int[leng][leng];
        int[] giftLevel = new int[leng];
        int[] giftCount = new int[leng];

        // 친구들 번호 붙이기
        int count = 0;
        for(String friend : friends){
            hash.put(friend,count++);
        }
        
        // 주고받은 선물 배열에 정리
        for(String gift : gifts){
            String[] s = gift.split(" ");
            int giver = hash.get(s[0]);
            int receiver = hash.get(s[1]);
            
            arr[giver][receiver]++;
        }
        
        // 선물지수 계산
        for(int k=0; k<leng; k++){
            int giveGift = 0;
            int receiveGift = 0;
            
            for(int i=0; i<leng; i++){
                giveGift += arr[k][i];
                receiveGift += arr[i][k];
            }
            giftLevel[k] = giveGift - receiveGift;
        }
        
        // 받을선물 계산
        int answer = 0;
        for(int i=0; i<leng; i++){
            for(int j=i+1; j<leng; j++){
                int num = 0;
                // 더 많은 선물을 준 사람이 선물 하나 더 받기 i>j
                if(arr[i][j] > arr[j][i]){
                    num = i;
                } else if(arr[i][j] < arr[j][i]){
                    num = j;
                } else {
                    // 선물지수가 더 큰사람이 선물지수가 더 작은사람에게 선물 하나 받기
                    if(giftLevel[i]>giftLevel[j]){
                        num = i;
                    } else if(giftLevel[j] > giftLevel[i]){
                        num = j;
                    } else{
                        continue;
                    }
                }
                answer = Math.max(answer,++giftCount[num]);
            }
        }
        return answer;
    }
}