import java.io.*;

public class Main {
    public static  char[][] cmap;

    public static int allCheck(char cdata){
        int cnt =0;
        for(int i=0;i<3;i++){
            if(cmap[i][0]==cdata && cmap[i][1] == cdata && cmap[i][2] == cdata){
                cnt++;
            }
            if(cmap[0][i]==cdata && cmap[1][i] == cdata && cmap[2][i] == cdata){
                cnt++;
            }
        }
        if(cmap[0][0]==cdata && cmap[1][1] == cdata && cmap[2][2] == cdata)
            cnt++;
        if(cmap[0][2]==cdata && cmap[1][1]==cdata && cmap[2][0]==cdata)
            cnt++;

        return cnt;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        if(str.equals("end")){
            return;
        }
        while (!str.equals("end")){
            cmap = new char[3][3];
            int ocnt = 0;
            int xcnt = 0;

            for(int i=0,k=0;i<3;i++){
                for(int j=0;j<3;j++){
                    cmap[i][j] = str.charAt(k++);
                    if(cmap[i][j] == 'O')
                        ocnt++;
                    else if(cmap[i][j] == 'X')
                        xcnt++;
                }
            }

            int oanswer = allCheck('O');
            int xanswer = allCheck('X');


            if(xanswer!=0 && oanswer == 0 && xcnt == ocnt+1)
                sb.append("valid").append("\n");
            else if(oanswer !=0 && xanswer == 0 && xcnt == ocnt)
                sb.append("valid").append("\n");
            else if(xanswer == 0 && oanswer == 0 && xcnt == 5 && ocnt == 4)
                sb.append("valid").append("\n");
            else
                sb.append("invalid").append("\n");

            str = br.readLine();
        }
        System.out.print(sb);
    }
}