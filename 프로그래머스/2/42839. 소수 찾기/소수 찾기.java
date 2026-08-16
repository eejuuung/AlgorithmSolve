import java.io.*;
import java.util.*;

class Solution {
    public boolean[] sosu;
    public boolean[] check;
    public boolean[] used;
    public int answer;
    public String number;
    
    public int solution(String numbers) {
        answer = 0;
        number = numbers;
        
        sosu = eratosthenes(10000000);
        check = new boolean[10000000];
        used = new boolean[numbers.length()];
        
        for(int i=1; i<=numbers.length(); i++){
            dfs("",i);
        }
        
        return answer;
    }
    
    public void dfs(String num, int limit){
        if(num.length() == limit){
            int val = Integer.parseInt(num);
            if(!check[val]){
                check[val] = true;
                
                if(sosu[val]){
                    answer++;
                }
            }
            return;
        }
        
        for(int i = 0; i<number.length(); i++){
            if(used[i])
                continue;
            
            used[i] = true;
            dfs(num + number.charAt(i), limit);
            used[i] = false;
        }
    }
    
    public boolean[] eratosthenes(int limit){
        boolean[] sosu = new boolean[limit+1];
        Arrays.fill(sosu,true);
        sosu[0] = sosu[1] = false;
        
        for(int i = 2; i<= Math.sqrt(limit); i++){
            if(sosu[i]){
                for(int j = i*i; j<= limit; j+=i){
                    sosu[j] = false;
                }
            }
        }
        return sosu;
    }
}