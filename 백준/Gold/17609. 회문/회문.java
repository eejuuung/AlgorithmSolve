import java.io.*;
import java.util.*;

public class Main {

    public static class Pair {
        int front;
        int back;
        boolean deleteStr;

        Pair(int front, int back, boolean deleteStr) {
            this.front = front;
            this.back = back;
            this.deleteStr = deleteStr;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for (int ntc = 0; ntc < tc; ntc++) {
            String str = br.readLine();
            int answer = 2;
            Queue<Pair> que = new ArrayDeque<>();
            que.offer(new Pair(0, str.length() - 1, false));

            while (!que.isEmpty()) {
                Pair nowP = que.poll();
                if (nowP.front == nowP.back) {
                    answer = (nowP.deleteStr ? 1 : 0);
                } else if (nowP.front + 1 == nowP.back) {
                    if (str.charAt(nowP.front) == str.charAt(nowP.back)) {
                        if (!nowP.deleteStr)
                            answer = 0;
                        else
                            answer = 1;
                    } else {
                        if (!nowP.deleteStr)
                            answer = 1;
                    }
                }

                if (nowP.front + 1 >= nowP.back)
                    continue;

                if (str.charAt(nowP.front) == str.charAt(nowP.back)) {
                    que.offer(new Pair(nowP.front + 1, nowP.back - 1, nowP.deleteStr));
                } else if (!nowP.deleteStr) {
                    que.offer(new Pair(nowP.front + 1, nowP.back, true));
                    que.offer(new Pair(nowP.front, nowP.back - 1, true));
                }
            }
            sb.append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
