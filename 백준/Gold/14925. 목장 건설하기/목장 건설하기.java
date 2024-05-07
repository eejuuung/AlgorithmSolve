import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N,M;
	public static int nowL;
	public static int[][] map;
	public static int[][] wmap;
	
	public static boolean mapCheck(int sy, int sx, int ey, int ex) {
		
		for(int i=sy;i<=ey;i++) {
			if(wmap[i][ex] - wmap[i][sx] !=0)
				return false;
		}
		
		
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		map = new int[N+1][M+1];
		wmap = new int[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j] = Integer.parseInt(stz.nextToken());
			}
		}
		//map체크
		for(int i=1;i<=N;i++) {
			int cnt = 0;
			for(int j=1;j<=M;j++) {
				if(map[i][j]!=0)
					cnt++;
				wmap[i][j] = cnt;
			}
		}
		
		//nowL
		nowL = Math.min(N, M);
		
		while(nowL>0) {
			
			for(int i=1;i+nowL-1<=N;i++) {
				for(int j=1;j+nowL-1<=M;j++) {
					int n = i+nowL-1;
					int m = j+nowL-1;
					if(mapCheck(i, j-1, n,m )) {
						System.out.println(nowL);
						return;
					}
				}
			}
			nowL--;
		}
		
		System.out.println("0");
		
	}

}