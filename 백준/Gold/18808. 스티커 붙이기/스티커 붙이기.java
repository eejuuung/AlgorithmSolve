import java.io.*;
import java.util.*;

public class Main {

    static class StickerBook {
        int R;
        int C;
        boolean[][] sticker;

        StickerBook(int R, int C) {
            this.R = R;
            this.C = C;
            sticker = new boolean[R][C];
        }
    }

    static int N, M, K;
    static boolean[][] noteBook;
    static StickerBook[] stickerBooks;

    // k번 스티커 회전하기
    public static void rotationSticker(int k) {
        StickerBook copySticker = new StickerBook(stickerBooks[k].C, stickerBooks[k].R);
        for (int i = stickerBooks[k].R - 1, n = 0; i >= 0; i--, n++) {
            for (int j = 0, m = 0; j < stickerBooks[k].C; j++, m++) {
                copySticker.sticker[j][i] = stickerBooks[k].sticker[n][m];
            }
        }

        stickerBooks[k] = copySticker;
    }

    public static boolean checkBook(int y, int x, int k) {
        for (int i = 0; i < stickerBooks[k].R; i++) {
            for (int j = 0; j < stickerBooks[k].C; j++) {
                if (stickerBooks[k].sticker[i][j] && noteBook[y + i][x + j])
                    return false;
            }
        }

        for (int i = 0; i < stickerBooks[k].R; i++) {
            for (int j = 0; j < stickerBooks[k].C; j++) {
                noteBook[y + i][x + j] = stickerBooks[k].sticker[i][j] ? stickerBooks[k].sticker[i][j] : noteBook[y + i][x + j];
            }
        }

        return true;
    }

    public static boolean isStickerOkay(int k) {
        int height = N - stickerBooks[k].R;
        int width = M - stickerBooks[k].C;
        boolean isOkay = false;

        // 노트북에 붙일 수 있는지 체크하기
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                isOkay = checkBook(i, j, k);
                if (isOkay)
                    break;
            }
            if (isOkay)
                break;
        }
        return isOkay;
    }

    public static void putSticker(int k) {
        // k번 sticker 붙이기
        for (int i = 0; i < 4; i++) {
            if (isStickerOkay(k)) {
                break;
            }
            rotationSticker(k);
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());
        noteBook = new boolean[N][M];
        stickerBooks = new StickerBook[K];

        for (int i = 0; i < K; i++) {
            stz = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(stz.nextToken());
            int C = Integer.parseInt(stz.nextToken());

            stickerBooks[i] = new StickerBook(R, C);
            for (int y = 0; y < R; y++) {
                stz = new StringTokenizer(br.readLine());
                for (int x = 0; x < C; x++) {
                    int num = Integer.parseInt(stz.nextToken());
                    stickerBooks[i].sticker[y][x] = (num == 1);
                }
            }
            putSticker(i);
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = noteBook[i][j] ? answer + 1 : answer;
            }
        }
        System.out.println(answer);
    }
}