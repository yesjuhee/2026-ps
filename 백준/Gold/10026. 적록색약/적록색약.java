import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n;
	boolean[][] visited;
	char[][] map;
	Deque<Pair> q = new ArrayDeque<>();
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 색약X 색약O
		// 색약O인 경우 R == G
		// map, visited -> 한 명 하고 초기화
		// 하나의 색깔에 대해서 BFS 돌리기

		// 입력
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n][n];
		map = new char[n][n];
		for(int i = 0; i < n; i++) {
			String row = br.readLine();
			for(int j = 0; j < n; j++) {
				char c = row.charAt(j);
				map[i][j] = c;
			}
		}

		StringBuilder sb =  new StringBuilder();
		int cnt = 0;

		// 기본 BFS
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j]) continue;
				bfs(new Pair(i, j));
				cnt++;
			}
		}
		sb.append(cnt).append(" ");

		// 색약 BFS
		cnt = 0;
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'R') {
					map[i][j] = 'G';
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j]) continue;
				bfs(new Pair(i, j));
				cnt++;
			}
		}
		sb.append(cnt).append("\n");

		// 출력
		System.out.println(sb);
	}

	public void bfs(Pair start) {
		q.addLast(start);
		visited[start.x][start.y] = true;
		char color = map[start.x][start.y];

		while(!q.isEmpty()) {
			Pair cur = q.removeFirst();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if (visited[nx][ny] || map[nx][ny] != color) continue;
				Pair next = new Pair(nx, ny);
				q.addLast(next);
				visited[nx][ny] = true;
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