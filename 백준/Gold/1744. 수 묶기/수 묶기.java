import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//+
		ArrayList<Long> plist = new ArrayList<>();
		//-
		ArrayList<Long> mlist = new ArrayList<>();
		
		boolean inZero = false;
		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			long readD = Long.parseLong(br.readLine());
			if(readD<0)
				mlist.add(readD);
			else if(readD>0)
				plist.add(readD);
			else
				inZero=true;
		}
		
		/*
		 * 	양수는 큰수끼리 서로 곱해주는것이 이득.
		 	0은 서로 곱해주지 않는것이 이득.
		 	음수는 서로 짝을지어서 곱해서 양수가 되게 만든 후 곱해주는것이 이득
		 	그렇게 되었을 때 만약 음수의 갯수가 홀수라면 가장 작은 홀수가 남고 
		 	그것을 0이 들어왓다면 곱해주어  값을 0으로 더해주지 않는것이 이득임.
		*/
		//먼저 정렬
		long answer = 0;
		Collections.sort(plist);
		Collections.sort(mlist);
		
		//p
		for(int i=plist.size()-2, j=plist.size()-1 ;i>=0;i-=2,j-=2) {
			if(plist.get(i) == 1 ||  plist.get(j) == 1) {
				answer += (plist.get(i) + plist.get(j));
			}
			else {
				answer += (plist.get(i) * plist.get(j));
			}
		}
		if(plist.size()%2!=0)
			answer += (plist.get(0));
		
		//m
		for(int i=0,j=1;j<mlist.size();i+=2,j+=2) {
			answer += (mlist.get(i) * mlist.get(j));
		}
		if(mlist.size()%2!=0) {
			if(!inZero) {
				answer += mlist.get(mlist.size()-1);
			}
		}
		System.out.println(answer);
		
	}

}
