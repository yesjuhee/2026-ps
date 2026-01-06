import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int[] dx = {0, 0, 1, -1};
	int[] dy = {1, -1, 0, 0};
	char WALL = '#';
	char FIRE = '*';
	char EMPTY = '.';
	char SANG = '@';

	int t, n, m;
	char[][] map;
	Pair sang;		
	Deque<Pair> q;
	int[][] dist1; // 불
	int[][] dist2; // 상근
	boolean[][] visited1;
	boolean[][] visited2;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 불 동시에 BFS -> dist1 채우기
		// 상근 BFS -> 최소 탈출 루트 찾기 -> 불과 이동 시간 비교

		t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(t-- > 0) {
			init();

			// 불 BFS -> dist1 채우기
			bfs1();

			// 상근 BFS
			sb.append(bfs2()).append("\n");
		}

		System.out.println(sb);
	}

	public void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		dist1 = new int[n][m];
		dist2 = new int[n][m];
		visited1 = new boolean[n][m];
		visited2 = new boolean[n][m];
		q = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
			String row = br.readLine();
			for (int j = 0; j < m; j++) {
				char c = row.charAt(j);
				if (c == FIRE) {
					Pair fire = new Pair(i, j);
					q.addLast(fire);
					visited1[fire.x][fire.y] = true;
				} else if (c == SANG) {
					sang = new Pair(i, j);
				}
				map[i][j] = c;
			}
		}
	}

	public void bfs1() {
		while(!q.isEmpty()) {
			Pair cur = q.removeFirst();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (visited1[nx][ny] || map[nx][ny] == WALL) continue;
				Pair next = new Pair(nx, ny);
				q.addLast(next);
				visited1[next.x][next.y] = true;
				dist1[next.x][next.y] = dist1[cur.x][cur.y] + 1;
			}
		}
	}

	public String bfs2() {
		q.addLast(sang);
		visited2[sang.x][sang.y] = true;
		while(!q.isEmpty()) {
			Pair cur = q.removeFirst();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
					// cur 다음에 탈출 가능
					// 불 도착 여부 확인
					if (visited1[cur.x][cur.y] && dist1[cur.x][cur.y] <= dist2[cur.x][cur.y]) { // 불로 막혔다면
						continue; // 여기로는 못 나감!!
					}
					// 불 도착 안했으면 성공
					return Integer.toString(dist2[cur.x][cur.y] + 1);
				}
				if (visited2[nx][ny] || map[nx][ny] != EMPTY) continue;
				Pair next = new Pair(nx, ny);
				q.addLast(next);
				dist2[next.x][next.y] = dist2[cur.x][cur.y] + 1;
				visited2[next.x][next.y] = true;
			}
		}
		// 탈출 못 한 케이스
		return "IMPOSSIBLE";
	}


	class Pair {

		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}