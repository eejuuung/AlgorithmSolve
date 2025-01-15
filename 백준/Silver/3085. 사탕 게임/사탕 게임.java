import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static int N;
    public static char[][] map;

    public static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }
    }

    public static int countCheck(boolean line, int k) {
        int maxCount = 0;
        int nowCount = 0;

        char nowChar = map[k][0];
        if (line) {
            for (int i = 0; i < N; i++) {
                if (map[k][i] == nowChar) {
                    nowCount++;
                } else {
                    nowChar = map[k][i];
                    if (nowCount > maxCount)
                        maxCount = nowCount;
                    nowCount = 1;
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                if (map[i][k] == nowChar) {
                    nowCount++;
                } else {
                    nowChar = map[i][k];
                    if (nowCount > maxCount)
                        maxCount = nowCount;
                    nowCount = 1;
                }
            }
        }

        if (nowCount > maxCount)
            maxCount = nowCount;

        return maxCount;
    }

    public static int lineCheck() {
        int maxEat = 0;

        for (int k = 0; k < N; k++) {
            for (int i = 0, j = i + 1; j < N; i++, j++) {
                if (map[k][i] == map[k][j])
                    continue;

                char store = map[k][i];
                map[k][i] = map[k][j];
                map[k][j] = store;

                maxEat = Math.max(maxEat, countCheck(true, k));
                maxEat = Math.max(maxEat, countCheck(false, i));
                maxEat = Math.max(maxEat, countCheck(false, j));

                map[k][j] = map[k][i];
                map[k][i] = store;
            }
        }

        return maxEat;
    }

    public static int rowCheck() {
        int maxEat = 0;

        for (int k = 0; k < N; k++) {
            for (int i = 0, j = i + 1; j < N; i++, j++) {
                if (map[i][k] == map[j][k])
                    continue;

                char store = map[i][k];
                map[i][k] = map[j][k];
                map[j][k] = store;

                maxEat = Math.max(maxEat, countCheck(false, k));
                maxEat = Math.max(maxEat, countCheck(true, i));
                maxEat = Math.max(maxEat, countCheck(true, j));

                map[j][k] = map[i][k];
                map[i][k] = store;
            }
        }

        return maxEat;
    }


    public static void main(String[] args) throws Exception {
        init();

        int maxEat = 0;
        for (int i = 0; i < N; i++) {
            maxEat = Math.max(maxEat, countCheck(true, i));
            maxEat = Math.max(maxEat, countCheck(false, i));
        }

        maxEat = Math.max(maxEat, lineCheck());
        maxEat = Math.max(maxEat, rowCheck());

        System.out.println(maxEat);
    }
}