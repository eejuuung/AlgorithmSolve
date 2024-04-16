import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	public static boolean checkStack(Stack sta, char[] answer) {
		
		
		
		return true;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String bomb = br.readLine();
		int leng = bomb.length();
		char[] answer = new char[leng];
		
		for(int i=0;i<bomb.length();i++) {
			answer[i] = bomb.charAt(i);
		}
		
		Stack<Character> sta = new Stack<>();
		
		for(int k=0;k<str.length();k++) {
			sta.add(str.charAt(k));
			
			if(sta.size()>=leng && sta.peek() == answer[leng-1]) {
				
				boolean isokay = true;
				for(int i=sta.size()-1, j=leng-1;j>=0;i--,j--) {
					if(sta.get(i)!=answer[j]) {
						isokay = false;
						break;
					}
				}
				
				if(isokay) {
					for(int i=0;i<leng;i++) {
						sta.pop();
					}
				}
			}
			
		}
		
		if(sta.isEmpty()) {
			System.out.println("FRULA");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!sta.isEmpty()) {
			sb.append(sta.pop());
		}
		sb.reverse();
		sb.append("\n");
		
		System.out.print(sb);
		
		
	}

}