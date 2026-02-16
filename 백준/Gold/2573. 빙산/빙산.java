import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    int[][] map;

    int[] dx = new int[]{1, 0, -1, 0};
    int[] dy = new int[]{0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
        /*
        시뮬레이션 진행 
        1. 녹이기 -> 이 중 for 문 -> 새로운 배열에 표시
        2. 카운트하기 -> BFS
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        init();
        int year = 0;
        int cnt;
        while((cnt = count()) == 1) {
            melt();
            year++;
        }
        if (cnt > 1) {
            System.out.println(year);
        }
        if (cnt == 0) {
            System.out.println(0);            
        }
    }

    void init() throws IOException {
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
    }

    void melt() {
        int[][] meltedMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) continue;
                int cnt = countSea(i, j, map);
                meltedMap[i][j] = map[i][j] - cnt < 0 ? 0 : map[i][j] - cnt;
            }
        }
        map = meltedMap;
    }

    int countSea(int x, int y, int[][] map) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (map[nx][ny] == 0) cnt++;
        }
        return cnt;
    }

    /* BFS 로 개수 카운트 */
    int count() {
        int cnt = 0;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || map[i][j] == 0) continue;
                cnt++;
                bfs(new Point(i, j), visited);
            }
        }

        return cnt;
    }

    void bfs(Point start, boolean[][] visited) {
        Deque<Point> q = new ArrayDeque<>();
        q.addLast(start);
        visited[start.x][start.y] = true;

        while(!q.isEmpty()) {
            Point cur = q.removeFirst();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny] || map[nx][ny] == 0) continue;
                Point next = new Point(nx, ny);
                q.addLast(next);
                visited[next.x][next.y] = true;
            }
        }
    }

    class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
