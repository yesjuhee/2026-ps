import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m, k;
    boolean[][] notebook; // 스티커가 붙여진 경우 true
    Sticker[] stickers; // 스티커
    
	public static void main(String[] args) throws IOException {
        /*
        왼쪽 위부터 모양 그대로 스티커 붙임
        위치가 없으면 시계 방향으로 90도 씩 회전 -> 안되면 버림
        구: 노트북에서 스티커가 붙은 칸의 수 출력

        O(N*M*K*4 * R*C) -> O(40 40 100 4 100) -> O(64,000,000)
        모든 경우의 수 다 해보면 될 듯

        회전은 어떻게?
        */
        new Main().solution();
	}

    public void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        notebook = new boolean[n][m];
        stickers = new Sticker[k];
        for (int tmp = 0; tmp < k; tmp++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            boolean[][] sticker = new boolean[r][c];
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    if (st.nextToken().equals("1")) {
                        sticker[i][j] = true;
                    } else {
                        sticker[i][j] = false;
                    }
                }
            }
            stickers[tmp] = new Sticker(r, c, sticker);
        }
    }

	public void solution() throws IOException {
        init();
        for(int tmp  = 0; tmp < k; tmp++) {
            Sticker st = stickers[tmp];
            st.findPosition();
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (notebook[i][j]) cnt++;
            }
        }

        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         if (notebook[i][j]) sb.append("1 ");
        //         else sb.append("0 ");
        //     }
        //     sb.append("\n");
        // }
        // System.out.println(sb);
        
        System.out.println(cnt);
	}

    public boolean isIn(int x, int y, Rotation st) {
        // x, y 부터 시작해서 st가 다 들어갈 수 있음?
        for (int i = 0; i < st.n; i++) {
            for (int j = 0; j < st.m; j++) {
                if (st.rot[i][j] && notebook[i + x][j + y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void putSticker(int x, int y, Rotation st) {
        for (int i = 0; i < st.n; i++) {
            for (int j = 0; j < st.m; j++) {
                if (st.rot[i][j]) {
                    notebook[i + x][j + y] = true;
                }
            }
        }
    }

    public class Sticker {
        
        int r, c;
        Rotation[] rots = new Rotation[4];

        public Sticker(int r, int c, boolean[][] sticker) {
            this.r = r;
            this.c = c;
            rots[0] = new Rotation(r, c, sticker);
            for (int i = 1; i <= 3; i++) {
                rots[i] = rots[i-1].rotation(); // 90도 회전
            }
        }

        public void findPosition() {
            for (int rot = 0; rot < 4; rot++) {
                Rotation cur = rots[rot];
                // 한 칸씩 맞춰보기
                int r = cur.n;
                int c = cur.m;
                for (int i = 0; i <= n - r; i++) {
                    for (int j = 0; j <= m - c; j++) {
                         // (i, j) -> 왼쪽 위 최상단
                        if (isIn(i, j, cur)) {
                            putSticker(i, j, cur);
                            return;
                        }
                    }
                }                
            }
        }

        public void print() {
            for (int i = 0; i < 4; i++) {
                System.out.printf("rotation: %d\n", i * 90);
                rots[i].print();
            }
        }
    }

    public class Rotation {

        int n, m;
        boolean[][] rot;

        public Rotation(int n, int m, boolean[][] rot) {
            this.n = n;
            this.m = m;
            this.rot = rot;
        }

        public Rotation rotation() {
            boolean[][] next = new boolean[m][n];
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    int nx = y;
                    int ny = n - 1 - x;
                    next[nx][ny] = rot[x][y];
                }
            }
            return new Rotation(m, n, next);
        }

        public void print() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i< n; i++) {
                for (int j = 0; j < m; j++) {
                    sb.append(rot[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }
}
