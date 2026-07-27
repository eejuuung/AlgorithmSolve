import java.io.*;
import java.util.*;

class Solution {
    int[] parent;
    String[] value;
    
    public int find(int num){
        if(parent[num] == num)
            return num;
        else
            return parent[num] = find(parent[num]);
    }
    
    public String[] solution(String[] commands) {
        String[] answer;
        List<String> list = new ArrayList<>();
        
        parent = new int[2501];
        value = new String[2501];
        for(int i=0; i<2501; i++){
            parent[i] = i;
            value[i] = "";
        }
        
        for(String str : commands){
            String[] command = str.split(" ");
            
            switch(command[0]){
                case "UPDATE":{
                    if(command.length == 4){
                        int r = Integer.parseInt(command[1]);
                        int c = Integer.parseInt(command[2]);
                        int idx = (r-1)*50+c;
                        int num = find(idx);
                        value[num] = command[3];
                    } else{
                        for(int i=1; i<2501; i++){
                            if(parent[i] != i)
                                continue;
                            
                            if(value[i].equals(command[1])){
                                value[i] = command[2];
                            }
                        }
                    }
                }
                    break;
                    
                case "MERGE": {
                    int r1 = Integer.parseInt(command[1]);
                    int c1 = Integer.parseInt(command[2]);
                    int idx1 = (r1-1)*50 + c1;
                    int r2 = Integer.parseInt(command[3]);
                    int c2 = Integer.parseInt(command[4]);
                    int idx2 = (r2-1)*50 + c2;
                    
                    if(idx1 == idx2)
                        continue;
                    
                    int pNum1 = find(idx1);
                    int pNum2 = find(idx2);
                    
                    if(pNum1 == pNum2)
                        break;
                    
                    if(value[pNum1].equals("") && !value[pNum2].equals("")){
                        value[pNum1] = value[pNum2];
                    }
                    
                    parent[pNum2] = pNum1;
                    value[pNum2] = "";
                }
                    break;
                    
                case "UNMERGE": {
                    int r = Integer.parseInt(command[1]);
                    int c = Integer.parseInt(command[2]);
                    int idx = (r-1)*50 + c;
                    
                    int root = find(idx);
                    String val = value[root];
                    
                    List<Integer> group = new ArrayList<>();
                    
                    for(int i=1; i<2501; i++){
                        if(find(i) == root){
                            group.add(i);
                        }
                    }
                    
                    for(int num : group){
                        parent[num] = num;
                        value[num] = "";
                    }
                    
                    value[idx] = val;
                }
                    break;
                    
                case "PRINT": {
                    int r = Integer.parseInt(command[1]);
                    int c = Integer.parseInt(command[2]);
                    int idx = (r-1)*50 + c;
                    int root = find(idx);
                    
                    if(!value[root].equals(""))
                        list.add(value[root]);
                    else
                        list.add("EMPTY");
                }
                    break;
            }
        }
        
        answer = new String[list.size()];
        for(int i = 0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
}