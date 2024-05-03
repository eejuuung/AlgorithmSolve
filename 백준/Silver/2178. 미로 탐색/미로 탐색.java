import java.io.*;
import java.util.*;

public class Main {
	
	public static class Pair{
		int y;
		int x;
		int n;
		
		public Pair(int y, int x, int n) {
			this.y = y;
			this.x = x;
			this.n = n;
		}
	}
	
	// 오, 아, 왼, 위
	public static int[] dy = {0,1,0,-1};
	public static int[] dx = {1,0,-1,0};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N,M;
		StringTokenizer stz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		int[][] map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				int num = (str.charAt(j) == '1' ? 0:-1);
				map[i][j] = num;
			}
		}
		Queue<Pair> que = new ArrayDeque<>();
		que.offer(new Pair(0, 0, 1));
		map[0][0] = 1;
		
		while(!que.isEmpty()) {
			Pair nowP = que.poll();
			
			for(int i=0;i<4;i++) {
				int fy = nowP.y + dy[i];
				int fx = nowP.x + dx[i];
				
				if(fy<0 || fx<0 || fy>=N || fx>=M || map[fy][fx]!=0)
					continue;
				
				if(fy == N-1 && fx == M-1) {
					System.out.println(nowP.n+1);
					return;
				}
				
				map[fy][fx] = nowP.n+1;				
				que.offer(new Pair(fy, fx, nowP.n+1));
			}
		}
	}

}