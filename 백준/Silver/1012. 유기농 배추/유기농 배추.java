import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int t, n, m, k;
	boolean[][] visited;
	Deque<Pair> q = new ArrayDeque<>();
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 덩어리 개수 구하기

		// 입력
		t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			init();

			sb.append(count()).append("\n");
		}

		// 출력
		System.out.println(sb);
	}

	public void init() throws IOException {
		visited = new boolean[n][m];
		// 전체 true로 초기화 -> 지렁이 있는 곳만 false
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], true);
		}
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			visited[y][x] = false;
		}
	}

	public int count() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j]) continue;
				cnt++;
				bfs(new Pair(i, j));
			}
		}
		return cnt;
	}

	public void bfs(Pair start) {
		q.addLast(start);
		visited[start.x][start.y] = true;

		while(!q.isEmpty()) {
			Pair cur = q.removeFirst();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (visited[nx][ny]) continue;
				Pair next = new Pair(nx, ny);
				q.addLast(next);
				visited[next.x][next.y] = true;
			}
		}
	}

	class Pair {

		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}