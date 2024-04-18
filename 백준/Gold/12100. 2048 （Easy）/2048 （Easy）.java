import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int maxNum;

    public static void dfs(int depth,int[][] map){

        if(depth>=5){

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j]>maxNum)
                        maxNum = map[i][j];
                }
            }
            return;
        }


        int[][] copyMap;

        //오
        copyMap = right(map);
        dfs(depth+1, copyMap);

        //아
        copyMap = down(map);
        dfs(depth+1, copyMap);

        //왼
        copyMap = left(map);
        dfs(depth+1, copyMap);

        //위
        copyMap = up(map);
        dfs(depth+1, copyMap);
    }

    public static int[][] copyArr(int[][] map){
        int[][] copyMap = new int[N][N];
        for(int i=0;i<N;i++){
            System.arraycopy(map[i], 0, copyMap[i], 0, N);
        }
        return copyMap;
    }

    public static int[][] right(int[][] map){
        int[][] copyMap = copyArr(map);
        boolean ispp = false;
        Stack<Integer> sta = new Stack<>();
        for(int i=0;i<N;i++){
            for(int j=N-1;j>=0;j--){
                if(copyMap[i][j]!=0){
                    int num = copyMap[i][j];
                    if(sta.isEmpty()){
                        sta.push(num);
                        ispp = false;
                    } else if(sta.peek() == num && !ispp){
                        num = sta.pop() *2;
                        sta.push(num);
                        ispp = true;
                    } else{
                        sta.push(copyMap[i][j]);
                        ispp = false;
                    }
                    copyMap[i][j] = 0;
                }
            }

            for(int j=N-sta.size();j<N;j++){
                copyMap[i][j] = sta.pop();
            }
        }

        return copyMap;
    }

    public static int[][] left(int[][] map){
        int[][] copyMap = copyArr(map);
        Stack<Integer> sta = new Stack<>();
        boolean ispp = false;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(copyMap[i][j]!=0){
                    int num = copyMap[i][j];
                    if(sta.isEmpty()){
                        sta.push(num);
                        ispp = false;
                    } else if(sta.peek() == num && !ispp){
                        num = sta.pop() *2;
                        sta.push(num);
                        ispp = true;
                    } else{
                        sta.push(copyMap[i][j]);
                        ispp = false;
                    }
                    copyMap[i][j] = 0;
                }

            }
            for(int j=sta.size()-1;j>=0;j--){
                copyMap[i][j] = sta.pop();
            }
        }
        return copyMap;
    }

    public static int[][] down(int[][] map){
        int[][] copyMap = copyArr(map);
        Stack<Integer> sta = new Stack<>();
        boolean ispp = false;
        for(int j=0;j<N;j++){
            for(int i=N-1;i>=0;i--){
                if(copyMap[i][j]!=0){
                    int num = copyMap[i][j];
                    if(sta.isEmpty()){
                        sta.push(num);
                        ispp = false;
                    } else if(sta.peek() == num && !ispp){
                        num = sta.pop() *2;
                        sta.push(num);
                        ispp = true;
                    } else{
                        sta.push(copyMap[i][j]);
                        ispp = false;
                    }
                    copyMap[i][j] = 0;
                }
            }
            for(int i=N-sta.size();i<N;i++){
                copyMap[i][j] = sta.pop();
            }
        }
        return copyMap;
    }

    public static int[][] up(int[][] map){
        int[][] copyMap = copyArr(map);
        boolean ispp = false;
        Stack<Integer> sta = new Stack<>();
        for(int j=0;j<N;j++){
            for(int i=0;i<N;i++){
                if(copyMap[i][j]!=0){
                    int num = copyMap[i][j];
                    if(sta.isEmpty()){
                        sta.push(num);
                        ispp = false;
                    } else if(sta.peek() == num && !ispp){
                        num = sta.pop() *2;
                        sta.push(num);
                        ispp = true;
                    } else{
                        sta.push(copyMap[i][j]);
                        ispp = false;
                    }
                    copyMap[i][j] = 0;
                }
            }
            for(int i=sta.size()-1;i>=0;i--){
                copyMap[i][j] = sta.pop();
            }
        }

        return copyMap;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for(int i=0;i<N;i++){
            StringTokenizer stz = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }
        maxNum = 0;
        dfs(0,map);
        System.out.println(maxNum);
    }
}