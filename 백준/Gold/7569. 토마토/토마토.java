import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int m, n, h;
	int[][][] map;
	boolean[][][] visited;
	int[][][] dist;
	Deque<Point> q = new ArrayDeque<>();
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, 1, -1};
	int[] dz = {-1, 1};

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 창고에 보관된 토마토들이 다 익게 되는 최소 일수
		// 1 - 익은 / 0 - 안익은 / -1 빈칸
		// 3차원 BFS
		// BFS 덩어리 count -> count의 최대값 || 방문 못한 점 있으면 -1

		// 입력
		init();

		bfs();

		// 출력
		// dist 배열 -> -2 or -1(안익음) or 익은날짜
		int max = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if (dist[i][j][k] == -2) continue;
					if (dist[i][j][k] == -1) {
						System.out.println(-1);
						return;
					}
					// 익은 날짜
					max = Math.max(max, dist[i][j][k]);
				}
			}
		}
		System.out.println(max);
	}

	public void bfs() {
		while(!q.isEmpty()) {
			Point cur = q.removeFirst();
			// 같은 층 4군데
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				int nz = cur.z;
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (visited[nz][nx][ny] || map[nz][nx][ny] != 0) continue;
				// 안익은 토마토
				Point next = new Point(nz, nx, ny);
				dist[next.z][next.x][next.y] = dist[cur.z][cur.x][cur.y] + 1;
				visited[next.z][next.x][next.y] = true;
				q.addLast(next);
			}
			// 위 아래
			for (int i = 0; i < 2; i++) {
				int nx = cur.x;
				int ny = cur.y;
				int nz = cur.z + dz[i];
				if (nz < 0 || nz >= h) continue;
				if (visited[nz][nx][ny] || map[nz][nx][ny] != 0) continue;
				// 안익은 토마토
				Point next = new Point(nz, nx, ny);
				dist[next.z][next.x][next.y] = dist[cur.z][cur.x][cur.y] + 1;
				visited[next.z][next.x][next.y] = true;
				q.addLast(next);
			}
		}
	}

	public void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][n][m];
		visited = new boolean[h][n][m];
		dist = new int[h][n][m];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < m; k++) {
					int v = Integer.parseInt(st.nextToken());;
					map[i][j][k] = v;
					if (v == 0) {
						dist[i][j][k] = -1; // 안익은 토마토
					} else if (v == -1) {
						dist[i][j][k] = -2; // 벽
					} else { // v == 1
						// dist[i][j][k] = 0
						q.addLast(new Point(i, j, k)); // BFS 시작점
					}
				}
			}
		}
	}


	class Point {

		int x, y, z;

		Point(int z, int x, int y) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}