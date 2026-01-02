import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n, m;
	int[][] dist;
	Deque<Pair> q = new ArrayDeque<>();
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 토마토들이 며칠이 지나면 다 익게 되는지 최소 일수
		// m : 가로 칸, n: 세로 칸
		// 1인 지점부터 BFS -> 최대 값 출력
		// 0인 부분 있으면 -1 출력

		// dist 배열
		// -1 : 익지 않은 토마토 
		// 0: 익은 토마토
		// -2 : 비어있는 부분

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		dist = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				String c = st.nextToken();
				if (c.equals("0")) { // 방문할 지점
					dist[i][j] = -1;
				} else if (c.equals("1")) { // 시작점
					dist[i][j] = 0;
					q.addLast(new Pair(i, j));
				} else { // c == -1
					dist[i][j] = -2;
				}
			}
		}

		// BFS
		while(!q.isEmpty()) {
			Pair cur = q.removeFirst();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
					continue;
				}
				if (dist[nx][ny] != -1) {
					continue;
				}
				Pair next = new Pair(nx, ny);
				dist[next.x][next.y] = dist[cur.x][cur.y] + 1;
				q.addLast(next);
			}
		}

		// 답 찾기
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int cur = dist[i][j];
				if (cur == -1) {
					System.out.println(-1);
					return;
				}
				max = Math.max(max, cur);
			}
		}
		System.out.println(max);
	}

	class Pair {

		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}