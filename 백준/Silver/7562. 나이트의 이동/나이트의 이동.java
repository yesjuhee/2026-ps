import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int t, n;
	boolean[][] visited;
	int[][] dist;
	Pair start;
	Pair target;
	Deque<Pair> q;
	int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
	int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 나이트 이동 방식으로 BFS
		// 목표 지점 나올 때 까지 BFS + dist 배열

		t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < t; i++) {
			init();
			bfs(); // start ~ target 까지 거리 구하기
			sb.append(dist[target.x][target.y]).append("\n");
		}

		System.out.println(sb);
	}

	public void init() throws IOException {
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n][n];
		dist = new int[n][n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		target = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		q = new ArrayDeque<>();
	}

	public void bfs() {
		q.addLast(start);
		visited[start.x][start.y] = true;

		while(!q.isEmpty()) {
			Pair cur = q.removeFirst();
			if (target.x == cur.x && target.y == cur.y) {
				return;
			}

			for (int i = 0; i < 8; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if (visited[nx][ny]) continue;
				Pair next = new Pair(nx, ny);
				q.addLast(next);
				visited[next.x][next.y] = true;
				dist[next.x][next.y] = dist[cur.x][cur.y] + 1;
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