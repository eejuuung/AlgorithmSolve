import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int top = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0; i<moves.length; i++) {
            int location = moves[i]-1;
            
            for(int j=0; j<board.length; j++) {
                if(board[j][location] != 0) {
                    if(top == board[j][location]) {
                        answer += 2;
                        stack.pop();
                        if(stack.isEmpty())
                            top = 0;
                        else
                            top = stack.peek();
                    } else {
                        stack.push(board[j][location]);
                        top = board[j][location];
                    }
                    
                    board[j][location] = 0;
                    break;
                }
            }
            
            
        }
        
        
        
        
        return answer;
    }
}