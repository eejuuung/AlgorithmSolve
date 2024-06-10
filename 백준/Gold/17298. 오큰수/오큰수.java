import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer stz = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        Stack<Integer> sta = new Stack<>();

        for(int i=0;i<N;i++){

            while (!sta.isEmpty() && arr[sta.peek()]<arr[i]){
                arr[sta.pop()] = arr[i];
            }
            sta.push(i);
        }

        while (!sta.isEmpty()){
            arr[sta.pop()] = -1;
        }

        for(int i=0;i<N;i++){
            sb.append(arr[i]).append(" ");
        }
        sb.append("\n");
        System.out.print(sb);


    }
}