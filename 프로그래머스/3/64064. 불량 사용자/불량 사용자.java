import java.io.*;
import java.util.*;

class Solution {
    HashSet<Integer> hash;
    String[] user_id;
    String[] banned_id;
    boolean visit[];
    int userLeng = 0;
    int bannLeng = 0;
    int answer = 0;
    
    public boolean CheckString(String user, String bann){
        if(user.length() != bann.length())
            return false;
        
        for(int i = 0; i<user.length(); i++){
            if(bann.charAt(i) == '*')
                continue;
            
            if(user.charAt(i) != bann.charAt(i))
                return false;
        }
        return true;
    }
    
    public void DFS(int num, int bit){
        if(num >= bannLeng){
            if(hash.add(bit))
                answer++;
            return;
        }
        
        for(int i=0; i<userLeng; i++){
            if(visit[i])
                continue;
            
            visit[i] = true;
            if(CheckString(user_id[i],banned_id[num])){
                DFS(num+1, bit | (1<<i));
            }
            visit[i] = false;
        }
        
        
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        hash = new HashSet<>();
        this.user_id = user_id;
        this.banned_id = banned_id;
        userLeng = user_id.length;
        bannLeng = banned_id.length;
        visit = new boolean[userLeng];
        
         DFS(0,0);
        
        return answer;
    }
}