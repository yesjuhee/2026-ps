import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n,m,k;
    int r, c;
    boolean[][] sticker; // 스티커
    boolean[][] note; // 노트북
    boolean[][] rotation; // 회전용
    
	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        note = new boolean[n][m];

        for (int tmp = 0; tmp < k; tmp++) {
            inputSticker();

            // 4 방향으로
            boolean next = false;
            for (int rot = 0; rot < 4; rot++) { 
                if (next) break;
                // 왼쪽 위부터 하나씩
                for (int i = 0; i < n - r + 1; i++) {
                    if (next) break;
                    for (int j = 0; j < m - c + 1; j++) {
                        if(isPutable(i, j)) {
                            put(i, j);
                            next = true;
                            break;
                        }
                    }                    
                }
                rotation(); // 90도 회전
            }
        }

        // 스티커가 붙은 칸의 수
        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         if (note[i][j]) sb.append("1").append(" ");
        //         else sb.append("0").append(" ");
        //     }
        //     sb.append("\n");
        // }
        // System.out.println(sb);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (note[i][j]) cnt++;
            }
        }
        System.out.println(cnt);
	}

    /*
    스티커가 들어가는지 확인하고 붙이기
    */
    public boolean isPutable(int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sticker[i][j] && note[i+x][j+y]) return false;
            }
        }
        return true;
    }

    public void put(int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sticker[i][j]) note[x + i][y + j] = sticker[i][j];
            }
        }
    }

    public void rotation() {
        rotation = new boolean[c][r];
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                int nx = y;
                int ny = r - 1 - x;
                rotation[nx][ny] = sticker[x][y];
            }
        }
        sticker = rotation;
        int tmp = c;
        c = r;
        r = tmp;

        
        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < r; i++) {
        //     for (int j = 0; j < c; j++) {
        //         if (sticker[i][j]) sb.append("1").append(" ");
        //         else sb.append("0").append(" ");
        //     }
        //     sb.append("\n");
        // }
        // System.out.println("rotation:");
        // System.out.println(sb);

    }
    
    public void inputSticker() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sticker = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                String v = st.nextToken();
                if (v.equals("1")) {
                    sticker[i][j] = true;
                } else {
                    sticker[i][j] = false;
                }
            }
        }
    }
}
