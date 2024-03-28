import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class Witch{
		int n; // 배열번호
		int y;
		int x;
		
		Witch(int n, int y, int x){
			this.n = n;
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int rtc = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=rtc;tc++) {
			int N = Integer.parseInt(br.readLine());
			
			Witch[] gs25 = new Witch[N+2];
			
			for(int i = 0;i<N+2;i++) {
				StringTokenizer stz = new StringTokenizer(br.readLine());
				gs25[i]  = new Witch(i,Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()));
			}
			int[][] cmap = new int[N+2][N+2];
			
			// 각 위치별 거리값 담기
			for(int i=0;i<N+2;i++) {
				for(int j=i+1;j<N+2;j++) {
					cmap[i][j] = Math.abs(gs25[i].y - gs25[j].y) +  Math.abs(gs25[i].x - gs25[j].x);
					cmap[j][i] = cmap[i][j];
				}
			}
			
			
			//각 위치끼리의 좌표 넣기
			
			Queue<Witch> que = new ArrayDeque<>();
			que.offer(gs25[0]);
			boolean[] bmap = new boolean[N+2];
			bmap[0] = true;
			boolean isin = false;
			
			while(!que.isEmpty()) {
				Witch nowW = que.poll();
				
				//현재 위치가 종료 위치로 이동이 가능한 곳이지 체크갔던 위치라면 가지 않게끔 체크
				if(cmap[nowW.n][N+1]<=1000) {
					isin = true;
					sb.append("happy").append("\n");
					break;	
				}
				for(int i =1;i<=N;i++) {
					if(cmap[nowW.n][i]<=1000 && !bmap[i]) {
						bmap[i] = true;
						que.offer(new Witch(i, gs25[i].y, gs25[i].x));
					}
				}
			}
			
			if(!isin)
				sb.append("sad").append("\n");
		}
		System.out.print(sb);
	}

}
