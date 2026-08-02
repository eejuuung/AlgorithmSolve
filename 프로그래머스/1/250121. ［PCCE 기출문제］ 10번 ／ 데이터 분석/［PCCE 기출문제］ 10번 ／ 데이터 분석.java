import java.io.*;
import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<int[]> list = new ArrayList<>();
        
        int num = returnNum(ext);
        for(int[] dat : data){
            if(val_ext > dat[num])
                list.add(dat);
        }
        
        int snum = returnNum(sort_by);
        list.sort((a,b) -> Integer.compare(a[snum],b[snum]));
        
        int[][] answer = new int[list.size()][4];
        for(int i = 0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public int returnNum(String str){
        switch(str) {
            case "code": return 0;
            case "date": return 1;
            case "maximum": return 2;
            case "remain": return 3;
        }
        return 0;
    }
}