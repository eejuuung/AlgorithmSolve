import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Main {

    public static ArrayList<Integer> list;
    public static int N;
    public static int[] arr;
    public static boolean[] visited;

    public static void dfs(int start, int end){
        if(!visited[arr[start]]){
            visited[arr[start]] = true;
            dfs(arr[start],end);
            visited[arr[start]] = false;
        }
        if(arr[start] == end)
            list.add(end);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        visited = new boolean[N+1];

        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        list = new ArrayList<>();
        for(int i=1;i<=N;i++){
            visited[i] = true;
            dfs(i,i);
            visited[i] = false;
        }

        Collections.sort(list);

        sb.append(list.size()).append("\n");
        for (Integer val : list) {
            sb.append(val).append("\n");
        }

        System.out.print(sb);
    }
}