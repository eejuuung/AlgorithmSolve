import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
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
	
	public static int R,C;
	public static char[][] map;
	public static boolean[][] bmap;
	public static boolean[][] wbmap;
	// API Point클래스 확인
	public static Pair goal;	// 도착점
	public static Queue<Pair> waterQue;	// 물의위치
	public static Queue<Pair> gasiQue;	// 물의위치
	
	// 우, 아, 왼, 위
	public static int[] dy = {0,1,0,-1};
	public static int[] dx = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());
		bmap = new boolean[R][C];
		wbmap = new boolean[R][C];
		map = new char[R][C];
		waterQue = new ArrayDeque<>();
		gasiQue = new ArrayDeque<>();
		
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S') {
					map[i][j] = '.';
					gasiQue.offer(new Pair(i, j));
					bmap[i][j] = true;
				} else if(map[i][j] == 'E') {
					goal = new Pair(i, j);
				} else if(map[i][j] == '*') {
					waterQue.offer(new Pair(i, j));
					wbmap[i][j] = true;
				}
			}
		}
		
		String answer = "KAKTUS";
		
		// 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없으므로, 다음에 물이찰 예정인 칸을 미리 구해놓는다.
		// 그리고 해당칸을 고슴도치는 이동하지 못하게한다.
		int time = 0;
		ArrayList<Pair> list = new ArrayList<>();
		while(!gasiQue.isEmpty()) {
			int waterSize = waterQue.size();
			
			// 물먼저 이동, 다음에 찰 물은  list에 미리 담아놓기
			list = new ArrayList<>();
			for(int k=0;k<waterSize;k++) {
				Pair nowWater = waterQue.poll();
				
				for(int i=0;i<4;i++) {
					int fy = nowWater.y + dy[i];
					int fx = nowWater.x + dx[i];
					
					if(fy<0 || fx<0 || fy>=R || fx>=C || map[fy][fx]!='.' || wbmap[fy][fx])
						continue;
					
					wbmap[fy][fx] = true;
					waterQue.offer(new Pair(fy, fx));
					list.add(new Pair(fy, fx));
				}
			}
			
			// 고슴도치 이동
			int gasiSize = gasiQue.size();
			for(int k=0;k<gasiSize;k++) {
				Pair nowGasi = gasiQue.poll();
				if(map[nowGasi.y][nowGasi.x] == '*')
					continue;
				
				for(int i=0;i<4;i++) {
					int fy = nowGasi.y + dy[i];
					int fx = nowGasi.x + dx[i];
					
					if(fy<0 || fx<0 || fy>=R || fx>=C || map[fy][fx]=='X' || map[fy][fx] == '*' || bmap[fy][fx] || wbmap[fy][fx])
						continue;
					
					bmap[fy][fx] = true;
					if(map[fy][fx] == 'D') { // 도착 해당시간에 나가면됨
						 System.out.println(time+1);
						 return;
					}
					
					gasiQue.offer(new Pair(fy, fx));
				}
				
				
			}
			
			
			// list 에 담긴거 빼서 map에 적용시키기
			while(!list.isEmpty()) {
				if(map[list.get(0).y][list.get(0).x] == '.' )
					map[list.get(0).y][list.get(0).x] = '*';
				list.remove(0);
			}
			
			// 시간증가
			time++;
		}
		 System.out.println(answer);
	}

}
