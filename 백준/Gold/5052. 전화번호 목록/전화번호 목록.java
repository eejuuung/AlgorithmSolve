import java.io.*;

public class Main {
    static class TrieNode {
        TrieNode[] children = new TrieNode[10]; // 0~9
        boolean isEnd; // 이 노드에서 번호가 끝나는가?
    }

    static class Trie {
        TrieNode root = new TrieNode();

        public boolean insert(String number) {
            TrieNode curr = root;
            for (int i = 0; i < number.length(); i++) {
                int idx = number.charAt(i) - '0';

                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }

                curr = curr.children[idx];

                // 현재 도중에 다른 번호가 끝난 경우 → 현재 번호가 그 번호의 접두사
                if (curr.isEnd) {
                    return false;
                }
            }

            // 현재 번호 삽입 완료 후에도 자식 노드가 있으면 → 다른 번호의 접두사임
            for (int i = 0; i < 10; i++) {
                if (curr.children[i] != null) {
                    return false;
                }
            }

            curr.isEnd = true; // 현재 번호 끝 표시
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = br.readLine();
            }

            Trie trie = new Trie();
            boolean isConsistent = true;

            for (String number : numbers) {
                if (!trie.insert(number)) {
                    isConsistent = false;
                    break;
                }
            }

            sb.append(isConsistent ? "YES\n" : "NO\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
