import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int count = 0;
	int maxSize = 0;
	int n, m;
	int[][] map;
	boolean[][] visited;
	int[] dx = new int[]{-1, 1, 0, 0};
	int[] dy = new int[]{0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 구: 그림의 개수, 가장 넓은 것의 넓이
		// n, m
		// 모든 칸을 다 방문 할 때 까지 BFS

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// bfs
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] || map[i][j] == 0) continue;
				// 아직 방문 안 한 지점들에 대해서 BFS & 사이즈 계산
				count++;
				int size = bfs(i, j); 
				if (size > maxSize) {
					maxSize = size;
				}
			}
		}

		// 출력
		System.out.println(count);
		System.out.println(maxSize);
	}

	public int bfs(int a, int b) {
		// BFS 후 사이즈 반환		
		Pair start = new Pair(a, b);
		start.visit();
		Deque<Pair> q = new ArrayDeque<>();
		q.addLast(start);
		int size = 1;

		while(!q.isEmpty()) {
			Pair cur = q.removeFirst();
			int x = cur.x;
			int y = cur.y;
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (visited[nx][ny] == true || map[nx][ny] == 0) continue;
				
				Pair next = new Pair(nx, ny);
				next.visit();
				q.addLast(next);
				size++;
			}
		}

		return size;
	}

	class Pair {

		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void visit() {
			visited[x][y] = true;
		}
	}
}