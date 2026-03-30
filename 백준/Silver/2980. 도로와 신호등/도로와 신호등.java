import java.io.*;
import java.util.*;

public class Main {

    public static class Light implements Comparable<Light> {
        int location;
        int redTime;
        int greenTime;

        Light(int location, int redTime, int greenTime) {
            this.location = location;
            this.redTime = redTime;
            this.greenTime = greenTime;
        }

        @Override
        public int compareTo(Light o) {
            return location - o.location;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int L = Integer.parseInt(stz.nextToken());
        ArrayList<Light> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            int location = Integer.parseInt(stz.nextToken());
            int redTime = Integer.parseInt(stz.nextToken());
            int greenTime = Integer.parseInt(stz.nextToken());
            list.add(new Light(location, redTime, greenTime));
        }
        list.add(new Light(L, 0, 0));
        N++;
        Collections.sort(list);

        int time = 0;
        int beforeLocation = 0;
        for (int i = 0; i < N; i++) {
            Light nowLight = list.get(i);
            time += (nowLight.location - beforeLocation);
            if (i + 1 == N)
                break;

            int lightTime = nowLight.redTime + nowLight.greenTime;
            if (time % lightTime <= nowLight.redTime) {
                time += (nowLight.redTime - (time % lightTime));
            }
            beforeLocation = nowLight.location;
        }
        System.out.println(time);
    }
}