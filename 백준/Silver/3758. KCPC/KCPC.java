import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static class Team implements Comparable<Team> {

        int teamId;
        int teamScore;
        int teamSendCount;
        int teamLastSend;

        public Team(int teamId) {
            this.teamId = teamId;
            this.teamScore = 0;
            this.teamSendCount = 0;
            this.teamLastSend = 0;
        }

        @Override
        public int compareTo(Team o) {

            if (this.teamScore != o.teamScore) {
                return Integer.compare(o.teamScore, this.teamScore);
            }

            if (this.teamSendCount != o.teamSendCount) {
                return Integer.compare(this.teamSendCount, o.teamSendCount);
            }

            return Integer.compare(this.teamLastSend, o.teamLastSend);
        }
    }

    public static class Problem {
        int[] teamScore;

        public Problem(int teamCount) {
            this.teamScore = new int[teamCount];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int teamCount = Integer.parseInt(stz.nextToken());
            int problemCount = Integer.parseInt(stz.nextToken());
            int teamId = Integer.parseInt(stz.nextToken()) - 1;
            int logCount = Integer.parseInt(stz.nextToken());

            Problem[] problemArr = new Problem[problemCount];
            Team[] teams = new Team[teamCount];

            for (int i = 0; i < problemCount; i++) {
                problemArr[i] = new Problem(teamCount);
            }

            for (int i = 0; i < teamCount; i++) {
                teams[i] = new Team(i);
            }

            for (int i = 0; i < logCount; i++) {
                stz = new StringTokenizer(br.readLine());
                int readTeamId = Integer.parseInt(stz.nextToken()) - 1;
                int problemNumber = Integer.parseInt(stz.nextToken()) - 1;
                int getScore = Integer.parseInt(stz.nextToken());

                teams[readTeamId].teamSendCount++;
                teams[readTeamId].teamLastSend = i + 1;

                if (problemArr[problemNumber].teamScore[readTeamId] < getScore)
                    problemArr[problemNumber].teamScore[readTeamId] = getScore;

            }

            for (int i = 0; i < problemCount; i++) {
                for (int j = 0; j < teamCount; j++) {
                    teams[j].teamScore += problemArr[i].teamScore[j];
                }
            }

            Arrays.sort(teams);

            for (int i = 0; i < teamCount; i++) {
                if (teams[i].teamId == teamId) {
                    sb.append(i + 1).append("\n");
                    break;
                }
            }
        }
        System.out.print(sb);

    }

}