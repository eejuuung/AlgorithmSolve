import java.io.*;
import java.util.*;

public class Main {

    public static int s;
    public static char[][] map;

    public static void drawMap(int number, int x) {
        int y = 0;

        switch (number) {
            case 1:
                y += 1;
                x += s + 1;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                }
                y++;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                }
                break;
            case 2:
                for (int i = 0; i < s; i++, y++) {
                    map[y + s + 2][x] = '|';
                }
                y = 0;
                x++;
                for (int i = 0; i < s; i++, x++) {
                    map[y][x] = '-';
                    map[y + s + 1][x] = '-';
                    map[y + (2 * s) + 2][x] = '-';
                }
                y++;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                }
                break;
            case 3:
                x++;
                for (int i = 0; i < s; i++, x++) {
                    map[y][x] = '-';
                    map[y + s + 1][x] = '-';
                    map[y + (2 * s) + 2][x] = '-';
                }
                y = 1;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                    map[y + s + 1][x] = '|';
                }

                break;
            case 4:
                y = 1;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                    map[y][x + s + 1] = '|';
                    map[y + s + 1][x + s + 1] = '|';
                }
                x++;
                y = s + 1;
                for (int i = 0; i < s; i++, x++) {
                    map[y][x] = '-';
                }

                break;
            case 5:
                y = 1;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                    map[y + s + 1][x + s + 1] = '|';
                }
                y = 0;
                x++;
                for (int i = 0; i < s; i++, x++) {
                    map[y][x] = '-';
                    map[y + s + 1][x] = '-';
                    map[y + (2 * s) + 2][x] = '-';
                }
                break;
            case 6:
                y = 1;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                    map[y + s + 1][x] = '|';
                    map[y + s + 1][x + s + 1] = '|';
                }
                y = 0;
                x++;
                for (int i = 0; i < s; i++, x++) {
                    map[y][x] = '-';
                    map[y + s + 1][x] = '-';
                    map[y + (2 * s) + 2][x] = '-';
                }
                break;
            case 7:
                x++;
                for (int i = 0; i < s; i++, x++) {
                    map[y][x] = '-';
                }
                y++;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                    map[y + s + 1][x] = '|';
                }


                break;
            case 8:
                y = 1;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                    map[y][x + s + 1] = '|';
                    map[y + s + 1][x] = '|';
                    map[y + s + 1][x + s + 1] = '|';
                }
                y = 0;
                x++;
                for (int i = 0; i < s; i++, x++) {
                    map[y][x] = '-';
                    map[y + s + 1][x] = '-';
                    map[y + (2 * s) + 2][x] = '-';
                }
                break;
            case 9:
                y = 1;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                    map[y][x + s + 1] = '|';
                    map[y + s + 1][x + s + 1] = '|';
                }
                y = 0;
                x++;
                for (int i = 0; i < s; i++, x++) {
                    map[y][x] = '-';
                    map[y + s + 1][x] = '-';
                    map[y + (2 * s) + 2][x] = '-';
                }
                break;
            case 0:
                y = 1;
                for (int i = 0; i < s; i++, y++) {
                    map[y][x] = '|';
                    map[y][x + s + 1] = '|';
                    map[y + s + 1][x] = '|';
                    map[y + s + 1][x + s + 1] = '|';
                }
                y = 0;
                x++;
                for (int i = 0; i < s; i++, x++) {
                    map[y][x] = '-';
                    map[y + (2 * s) + 2][x] = '-';
                }
                break;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        s = Integer.parseInt(stz.nextToken());
        String str = stz.nextToken();
        int width = str.length() * (s + 3);
        int height = (2 * s) + 3;
        map = new char[height][width];

        for (int i = 0, x = s + 3; i < str.length(); i++) {
            drawMap(str.charAt(i) - '0', x * i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(map[i][j] == 0 ? ' ' : map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}