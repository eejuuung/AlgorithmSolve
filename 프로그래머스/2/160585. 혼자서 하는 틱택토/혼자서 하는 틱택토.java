class Solution {
    int[][] map;
    
    public boolean winner(int num){
        // 가로 X 세로
        for(int i=0; i<3; i++){
            if(map[i][0] == num && map[i][1] == num && map[i][2] == num){
                return true;
            }
            
            if(map[0][i] == num && map[1][i] == num && map[2][i] == num){
                return true;
            }
                
        }
        
        // 대각선
        if(map[0][0] == num && map[1][1] == num && map[2][2] == num){
                return true;
        }
        if(map[0][2] == num && map[1][1] == num && map[2][0] == num){
                return true;
        }
        
        return false;
    }
    
    public int solution(String[] board) {
        map = new int[3][3];
        int oCount = 0;
        int xCount = 0;
        
        for(int j=0; j<3; j++){
            String str = board[j];
            for(int i=0; i<3; i++){
                if(str.charAt(i) == 'O'){
                    map[j][i] = 1;
                    oCount++;
                } else if(str.charAt(i) == 'X'){
                    map[j][i] = 2;
                    xCount++;
                }
            }
        }
        
        boolean owin = winner(1);
        boolean xwin = winner(2);
        
        if(owin && xwin)
            return 0;
        else if(owin){
            if(oCount != xCount+1)
                return 0;
        } else if(xwin){
            if(oCount != xCount)
                return 0;
        } else if(oCount<xCount || oCount>xCount+1){
            return 0;
        }
        
        return 1;
    }
}