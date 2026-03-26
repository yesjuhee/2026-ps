import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n, m;
    int[][] map;
    int max = 0;

    int newWallCount = 0; // 새로 세워지는 벽 카운트

    int[] dx = new int[]{1, -1, 0, 0};
    int[] dy = new int[]{0, 0, 1, -1};
    
	public static void main(String[] args) throws IOException {
        /*
        안전 영역 크기의 최대값
        3개의 벽 추가 설치 -> 바이러스 막기
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 빈 자리 중 3개 고르기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) continue;                
                newWallCount++;
                int tmp = map[i][j];
                map[i][j] = 1;
                func(i, j);
                map[i][j] = tmp;
                newWallCount--;
            }
        }

        System.out.println(max);
    }

    void func(int x, int y) { // x, y 점을 마지막으로 선택함 
        if (newWallCount == 3) { // 3번째 선택인 경우
            // bfs 돌리기    
            int size = bfs();
            max = Math.max(max, size);
            return;
        }

        // 다음 점 선택
        for (int i = x; i <= x; i++) {
            for (int j = y; j < m; j++) {
                if (map[i][j] != 0) continue;
                newWallCount++;
                int tmp = map[i][j];
                map[i][j] = 1;
                func(i, j);
                map[i][j] = tmp;
                newWallCount--;               
            }
        }
        for (int i = x + 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) continue;
                newWallCount++;
                int tmp = map[i][j];
                map[i][j] = 1;
                func(i, j);
                map[i][j] = tmp;
                newWallCount--;
            }
        }
    }

    int bfs() {
        int[][] map = new int[n][m];
        Deque<Point> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = this.map[i][j];
                if (map[i][j] == 2) {
                    q.addLast(new Point(i, j));
                }
            }
        }

        while(!q.isEmpty()) {
            Point now = q.removeFirst();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (map[nx][ny] != 0) continue;
                Point next = new Point(nx, ny);
                q.addLast(next);
                map[next.x][next.y] = 2;
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }

    void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
