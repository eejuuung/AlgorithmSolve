class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        // 오, 아, 왼, 위
        int[] dy = {0,1,0,-1};
        int[] dx = {1,0,-1,0};
        
        for(int i=0; i<4; i++){
            int y = dy[i] + h;
            int x = dx[i] + w;
            
            if(y<0 || x<0|| y >= board.length || x>= board[0].length || 
               !board[y][x].equals(board[h][w]))
                continue;
            
            answer++;
        }
        
        return answer;
    }
}