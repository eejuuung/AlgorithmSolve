import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class Pair{
		int y;
		int x;
		Pair(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	public static int R,C,T;
	public static int[][] map;
	public static Pair[] airCln;
	public static Queue<Pair> dust_que;
	//오, 위, 왼, 아
	public static int[] dy = {0,-1,0,1};
	public static int[] dx = {1,0,-1,0};
	//오, 아, 왼, 위
	public static int[] ddy = {0,1,0,-1};
	public static int[] ddx = {1,0,-1,0};
	
	// 미세먼지 확신 함수, 확산할 미세먼지가 없다면 종료
	public static boolean Spread_FindDust() {
		int[][] copymap = new int[R][C];
		copymap[airCln[0].y][airCln[0].x] = -1;
		copymap[airCln[1].y][airCln[1].x] = -1;
		
		while(!dust_que.isEmpty()) {
			Pair dust = dust_que.poll();
			//확산되는 양은 B = A(r,c)/5, 소수점은 버림.
			int Cal = map[dust.y][dust.x]/5;
			
			int cnt = 0;
			for(int i=0;i<4;i++) {
				int fy = dust.y + dy[i];
				int fx = dust.x + dx[i];
				
				if(fy<0 || fx<0 || fy>=R || fx>=C || map[fy][fx] == -1)
					continue;
				
				copymap[fy][fx] += Cal;
				
				cnt++;
			}
			//(r,c)에 남은 미세먼지  양은 A(r,c)-B
			copymap[dust.y][dust.x] = copymap[dust.y][dust.x] + map[dust.y][dust.x] - (Cal * cnt);
			copymap[dust.y][dust.x] = (copymap[dust.y][dust.x]<0? 0 : copymap[dust.y][dust.x]);
		}
		
		boolean isin  = false;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				map[i][j] = copymap[i][j];
				if(copymap[i][j]>0) {
					isin = true;
				}
			}
		}
		
		if(!isin)
			return false;
		
		return true;
	}
	
	//공기청정기 작동
	public static int Run_CleanAir() {
		int maxi = (R>C? R:C);
		
		// 1. 위쪽 공기청정기 바람은 반시계 방향
		int fy = airCln[0].y;
		int fx = airCln[0].x;
		int beforenum = 0;
		for(int i=0;i<4;i++) {
			
			for(int j=0;j<maxi;j++) {
				int ffy = fy + dy[i];
				int ffx = fx + dx[i];
				if(ffy<0 || ffx<0 || ffy>=R || ffx>=C || map[ffy][ffx] == -1)
					break;
				
				fy = ffy;
				fx = ffx;	
				int copyd = map[ffy][ffx];
				map[ffy][ffx] = beforenum;
				beforenum = copyd;
			}
		}
		
		// 2. 아래는 시계방향으로 이동
		fy = airCln[1].y;
		fx = airCln[1].x;
		beforenum = 0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<maxi;j++) {
				int ffy = fy + ddy[i];
				int ffx = fx + ddx[i];
				if(ffy<0 || ffx<0 || ffy>=R || ffx>=C || map[ffy][ffx] == -1 )
					break;
				
				fy = ffy;
				fx = ffx;	
				int copyd = map[ffy][ffx];
				map[ffy][ffx] = beforenum;
				beforenum = copyd;
			}
		}
		
		//3. 먼지 que에 담으면서 카운트해주기
		int dust =0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]>0) {
					dust_que.offer(new Pair(i, j));
					dust += map[i][j];
				}
			}
		}
		
		return dust;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(stz.nextToken());
		C = Integer.parseInt(stz.nextToken());
		T = Integer.parseInt(stz.nextToken());
		
		dust_que = new ArrayDeque<>();
		airCln = new Pair[2];
		map = new int[R][C];
		boolean check = false;
		
		for(int i=0;i<R;i++) {
			 stz = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				map[i][j] = Integer.parseInt(stz.nextToken());
				if(!check && map[i][j] == -1) {
					airCln[0] = new Pair(i, j);
					airCln[1] = new Pair(i+1, j);
					check = true;
				}
				else if(map[i][j]>0) {
					dust_que.offer(new Pair(i, j));
				}
			}
		}
		
		int answer = 0;
		for(int k=0;k<T;k++) {
			if(!Spread_FindDust()) {
				break;
			}
			answer = Run_CleanAir();
		}
		System.out.println(answer);
	}

}
