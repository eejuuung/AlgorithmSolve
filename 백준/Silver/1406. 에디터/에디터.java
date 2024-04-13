import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> frontS = new Stack<>();
        Stack<Character> backS = new Stack<>();

        String str = br.readLine();
        for(int i=0;i<str.length();i++){
            frontS.push(str.charAt(i));
        }

        int N = Integer.parseInt(br.readLine());

        for(int k=0;k<N;k++){
            str = br.readLine();

            if(str.equals("L") && !frontS.isEmpty()){
                backS.push(frontS.pop());
            }else if(str.equals("D")  && !backS.isEmpty()){
               frontS.push(backS.pop());
            } else if(str.equals("B") && !frontS.isEmpty()){
               frontS.pop();
            } else if(str.contains("P")){
                frontS.push(str.charAt(2));
            }

        }
        StringBuilder sb = new StringBuilder();
        while (!frontS.isEmpty())
            sb.append(frontS.pop());

        sb.reverse();

        while (!backS.isEmpty())
            sb.append(backS.pop());

        System.out.print(sb);
    }
}