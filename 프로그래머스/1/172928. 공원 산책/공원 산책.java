class Solution {
    public int[] solution(String[] park, String[] routes) {
        int n = park.length;
        int m = park[0].length();
        int[] answer = new int[2];
        int[][] map = new int[n][m];
        int y = 0;
        int x = 0;
        // 위, 아, 왼, 오
        int[] dy = {-1,1,0,0};
        int[] dx = {0,0,-1,1};
        
        for(int i = 0 ; i<n; i++){
            for(int j=0; j<m; j++){
                if(park[i].charAt(j) == 'S'){
                    y = i;
                    x = j;
                } else if(park[i].charAt(j) == 'X'){
                    map[i][j] = -1;
                }
            }
        }
        
        for(String str : routes){
            String[] sArr = str.split(" ");
            int num = Integer.parseInt(sArr[1]);
            int val = 0;
            switch(sArr[0]){
                case "N":
                    val = 0;
                    break;
                case "S":
                    val = 1;
                    break;
                case "W":
                    val = 2;
                    break;
                case "E":
                    val = 3;
                    break;
            }
            
             if(y+(dy[val]*num) < 0 || y+(dy[val]*num) >=n ||
                x+(dx[val]*num) < 0 || x+(dx[val]*num) >= m)
                 continue;
            
            int endy = y+(dy[val]*num);
            int endx = x+(dx[val]*num);
            boolean isOkay = true;
            for(int i=y, j=x, k = 0; k<num; k++){
                
                i += dy[val];
                j += dx[val];
                
                if(map[i][j] == -1){
                    isOkay = false;
                    break;
                }
            }
            if(isOkay){
                y = endy;
                x = endx;
            }
        }
        
        
        answer[0] = y;
        answer[1] = x;
        
        return answer;
    }
}