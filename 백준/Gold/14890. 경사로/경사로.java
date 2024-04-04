import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int L = Integer.parseInt(stz.nextToken());
        int[][] map = new int[N][N];
        for(int i=0;i<N;i++){
            stz = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }
        int answer = 0;

        //가로 체크
        for(int i=0;i<N;i++){
            boolean isokay = true;
            boolean[] bmap = new boolean[N];
            for(int j=1,k=0;j<N;j++){
                //경사로 세워야하는경우
                if(map[i][k]!=map[i][j]){
                    //높이체크
                    // 올라가는 경우 k의 뒤의 위치들에 경사로를 올려야함.
                    if(map[i][k]+1 == map[i][j] && k-L+1>=0 ){
                        // 해당 길이의 값들이 모두 동일한지 체크
                        int n = k;
                        int gizun = map[i][n];
                        for(int m=0;m<L;m++){
                            if(map[i][n-m]!=gizun || bmap[n-m]){
                                isokay = false;
                                break;
                            }
                            else{
                                bmap[n-m] = true;
                            }
                        }
                        k=j;
                    }
                    // 내려가는경우, 그리고 경사로를 세울수 잇는 길이인경우
                    else if(map[i][k]-1 == map[i][j] && j+L-1<N ){
                        // 해당 길이의 값들이 모두 동일한지 체크
                        int n = j;
                        int gizun = map[i][n];
                        for(int m=0;m<L;m++){
                            if(map[i][n+m]!=gizun || bmap[n+m]){
                                isokay = false;
                                break;
                            }
                            else{
                                bmap[n+m] = true;
                            }
                        }
                        k=j+L-1;
                        j=k;
                    }
                    else { // 높이 맞지않는경우 1이상인경우
                        isokay = false;
                        break;
                    }
                }
                else{
                    k++;
                }
                if(!isokay)
                    break;
            }
            if(isokay)
                answer++;
        }

        //세로 체크
        for(int i=0;i<N;i++){
            boolean isokay = true;
            boolean[] bmap = new boolean[N];
            for(int j=1,k=0;j<N;j++){
                //경사로 세워야하는경우
                if(map[k][i]!=map[j][i]){
                    //높이체크
                    // 올라가는 경우 k의 뒤의 위치들에 경사로를 올려야함.
                    if(map[k][i]+1 == map[j][i] && k-L+1>=0 ){
                        // 해당 길이의 값들이 모두 동일한지 체크
                        int n = k;
                        int gizun = map[n][i];
                        for(int m=0;m<L;m++){
                            if(map[n-m][i]!=gizun || bmap[n-m]){
                                isokay = false;
                                break;
                            }
                            else{
                                bmap[n-m] = true;
                            }
                        }
                        k=j;
                    }
                    // 내려가는경우, 그리고 경사로를 세울수 잇는 길이인경우
                    else if(map[k][i]-1 == map[j][i] && j+L-1<N ){
                        // 해당 길이의 값들이 모두 동일한지 체크
                        int n = j;
                        int gizun = map[n][i];
                        for(int m=0;m<L;m++){
                            if(map[n+m][i]!=gizun || bmap[n+m]){
                                isokay = false;
                                break;
                            }
                            else{
                                bmap[n+m] = true;
                            }
                        }
                        k=j+L-1;
                        j=k;
                    }
                    else { // 높이 맞지않는경우 1이상인경우
                        isokay = false;
                        break;
                    }
                }
                else{
                    k++;
                }
                if(!isokay)
                    break;
            }
            if(isokay)
                answer++;
        }
        System.out.println(answer);
    }
}