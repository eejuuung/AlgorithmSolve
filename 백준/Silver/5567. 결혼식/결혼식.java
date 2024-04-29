import java.io.*;
import java.util.*;

public class Main {

	public static class Person{
		List<Integer> list;
		
		public Person() {
			list = new ArrayList<>();
		}
	}
	
	public static int N;
	public static int M;
	public static Person[] donggi;
	public static boolean[] bcheck;
	public static Queue<Integer> que;
	
	public static void allPutQueue(int nowP) {
		
		if(donggi[nowP] == null)
			return;
		
		for(int i=0;i<donggi[nowP].list.size();i++) {
			int friend = donggi[nowP].list.get(i);
			
			if(bcheck[friend])
				continue;
			bcheck[friend] = true;
			que.add(friend);
		}
	}

	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		donggi = new Person[N+1];
		bcheck = new boolean[N+1];
		que = new ArrayDeque<>();
		bcheck[1] = true;
		
		for(int i=0;i<M;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			int rn = Integer.parseInt(stz.nextToken());
			int rm =  Integer.parseInt(stz.nextToken());
			
			if(donggi[rn]==null) {
				donggi[rn] = new Person();
			}
			
			if(donggi[rm]==null) {
				donggi[rm] = new Person();
			}
			
			if(rn == 1) {
				if(!bcheck[rm]) {
					bcheck[rm] = true;
					que.offer(rm);
				}
			}else if(rm == 1) {
				if(!bcheck[rn]) {
					bcheck[rn] = true;
					que.offer(rn);
				}
			} else {
				donggi[rn].list.add(rm);
				donggi[rm].list.add(rn);
			}
		}
		
		// 친구의 친구의 친구는 추가하지않음.
		// 즉 현재 que에 들어간 애들만 연결
		int answer = 0;
		int leng = que.size();
		for(int i=0;i<leng;i++) {
			int nowP = que.poll();
			allPutQueue(nowP);
			answer++;
		}
		answer += que.size();
		
		System.out.println(answer);
	}
}