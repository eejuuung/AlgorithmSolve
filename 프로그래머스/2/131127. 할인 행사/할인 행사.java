import java.io.*;
import java.util.*;

class Solution {
    
    String[] want;
    int[] number;
    String[] discount;
    HashMap<String,Integer> hash;
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int leng = discount.length;
        
        this.want = want;
        this.number = number;
        this.discount = discount;
        hash = new HashMap<>();
        
        for(int i=0; i<10; i++){
            int num = hash.getOrDefault(discount[i],0);
            hash.put(discount[i],num+1);
        }
        
        answer = (isAllDiscount() ? answer+1 : answer);
        
        for(int left = 0, right = 10; right < leng; left++,right++){
            
            int rnum = hash.getOrDefault(discount[right],0);
            hash.put(discount[right],rnum+1);
            
            int lnum = hash.getOrDefault(discount[left],0);
            if(lnum!=0)
                hash.put(discount[left],lnum-1);
            
            answer = (isAllDiscount() ? answer+1 : answer);
        }
        
        return answer;
    }
    
    public boolean isAllDiscount(){
        for(int i=0; i< want.length; i++){
            int num = hash.getOrDefault(want[i],0);
            if(num < number[i])
                return false;
        }
        return true;
    }
    
    
    
    
}