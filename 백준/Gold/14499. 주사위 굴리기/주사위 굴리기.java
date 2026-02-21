import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m, x, y, k;
    int[][] map;
    int[] dice = new int[7];

    int dx[] = new int[]{0, 0, 0, -1, 1}; // 동, 서, 북, 남
    int dy[] = new int[]{0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
        /*
        주사위와 주사위의 이동을 어떻게 표현할 것인가?
        6개의 면을 가지고 있음
        24개의 상태를 가질 수 있음
        현재 상태에서 4개의 상태로 움직일 수 있음

        6개의 숫자 리스트 -> 현재 주사위의 상태 표시
        1, 2, 3, 4, 5, 6
        U, N, E, W, S, D
        회전 -> 두개의 면을 남겨 두고, 4개의 면이 하나씩 이동하는 것
        - 동쪽이동 -> N,S 고정 & U->E->D->W->U
            1 -> 2,5 고정 & 1->3->6->4->1
        - 서쪽 이동 -> N,S 고정 & U->W->D->E->U
            2 -> 2,5 고정 & 1->4->6->3->1
        - 북쪽 이동 -> E,W 고정 & U->N->D->S->U
            3 -> 3,4 고정 & 1->2->6->5->1
        - 남쪽 이동 -> E,W 고정 & U->S->D->N->U
            4 -> 3,4 고정 & 1->5->6->2->1
        주사위를 arr로 표시하고, 이동은 수동 구현 하자

        이동 명령어 -> 회전&이동 -> 값 확인 -> 주사위&맵 업데이트
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            int mv = Integer.parseInt(st.nextToken());
            if (isIllegal(mv)) continue;
            rollDice(mv); // 주사위 회전
            updateNumber(); // 숫자 복사 
            // 상단에 쓰여 있는 값
            sb.append(dice[1]).append("\n");
        }

        System.out.println(sb);
    }

    void print() {
        System.out.printf("(x,y): (%d,%d)\n", x, y);
        StringBuilder sb = new StringBuilder();
        sb.append("x").append(dice[2]).append("x").append("\n");
        sb.append(dice[4]).append(dice[1]).append(dice[3]).append("\n");
        sb.append("x").append(dice[5]).append("x").append("\n");
        sb.append("x").append(dice[6]).append("x").append("\n");
        System.out.println(sb);
    }

    void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    boolean isIllegal(int move) {
        int nx = x + dx[move];
        int ny = y + dy[move];
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) return true;
        x = nx;
        y = ny;
        return false;
    }

    void rollDice(int move) {
        if (move == 1) { // 1 -> 2,5 고정 & 1->3->6->4->1
            int tmp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = tmp;
        } else if (move == 2) { // 2 -> 2,5 고정 & 1->4->6->3->1
            int tmp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = tmp;
        } else if (move == 3) { // 3 -> 3,4 고정 & 1->2->6->5->1
            int tmp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = tmp;            
        } else { // 4 -> 3,4 고정 & 1->5->6->2->1
            int tmp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = tmp;   
        }
    }

    void updateNumber() {
        if (map[x][y] == 0) {
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }
    }
}
