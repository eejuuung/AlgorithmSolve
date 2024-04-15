import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] sarr = new String[N];
        int[] alpa = new int[26];
        for(int k=0;k<N;k++){
            sarr[k] = br.readLine();
            int j=1;
            for(int i=sarr[k].length()-1,n=1;i>=0;i--,n*=10){
                alpa[sarr[k].charAt(i)-'A'] += (j*n);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            int maxi = 0;
            int maxn = 0;
            for(int j=0;j<26;j++){
                if(alpa[j]==0)
                    continue;
                if(alpa[j]>maxi){
                    maxi = alpa[j];
                    maxn = j;
                }
            }
            if(maxi == 0)
                break;

            alpa[maxn]=0;
            list.add(maxn);
        }

        int answer = 0;

        for(int i=0, j=9;i<list.size();i++,j--){
            alpa[list.get(i)] = j;
        }
        for(int i=0;i<N;i++){
            for(int j=sarr[i].length()-1,k=1;j>=0;j--,k*=10){
                answer += (alpa[sarr[i].charAt(j)-'A']*k);
            }
        }
        System.out.println(answer);

    }
}