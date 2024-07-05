import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Pair{
        int y;
        int x;
        Pair(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static int N;
    public static int[][] map;
    public static boolean[][] bMap;
    public static ArrayList<Integer> list;
    // 오, 아, 왼, 위
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};

    public static void findHome(int y, int x){
        int count = 1;
        bMap[y][x] = true;
        Queue<Pair> que = new ArrayDeque<>();
        que.offer(new Pair(y,x));

        while (!que.isEmpty()){
            Pair nowP = que.poll();
            for(int i=0;i<4;i++){
                int fy = nowP.y + dy[i];
                int fx = nowP.x + dx[i];

                if(fy<0 || fx<0 || fy>=N || fx>=N || bMap[fy][fx] || map[fy][fx]==0){
                    continue;
                }
                bMap[fy][fx] = true;
                count++;
                que.offer(new Pair(fy,fx));
            }
        }
        list.add(count);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        bMap = new boolean[N][N];
        list = new ArrayList<>();

        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        //1. BFS를 이용하여 그룹 찾기
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==1 && !bMap[i][j]){
                    findHome(i,j);
                }
            }
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for (Integer item : list) {
            sb.append(item).append("\n");
        }
        System.out.print(sb);
    }
}