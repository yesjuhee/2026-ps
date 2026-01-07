import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int[] dx = {0, 0, 1, -1};
	int[] dy = {1, -1, 0, 0};
	int n, m;
	int[][][] dist; // -1:벽, 0:방문 안 한 점
	Deque<Pair> q = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 1,1 -> m,n 이동 최단 거리 찾기 / 불가능하면 -1
		// 벽 하나 부수기 가능

		// 벽이 없이 갈 수 있는 경로가 있는데, 벽을 부수는게 더 빠를 수 있나?
		// 있음
		/*
		6 4
		0000
		1110
		0000
		0111
		0000
		*/

		// Pair를 저장할 때, 벽을 부수고 온건지, 안부수고 온건지 체크
		// map도 두가지로 관리 -> 벽을 안부셨을 때의 최단 거리 / 하나만 부셨을 때의 최단 거리

		init();
		q.addLast(new Pair(0,0,0));
		dist[0][0][0] = 1;
		dist[1][0][0] = 1;

		while(!q.isEmpty()) {
			Pair cur = q.removeFirst();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;				
				if (dist[0][nx][ny] == -1 && cur.status == 1) continue; // 다음 칸이 벽이고, 이미 벽 뚫기를 쓴 경우
				
				if (dist[0][nx][ny] == -1 && cur.status == 0) { // 다음 칸이 벽이고, 아직 뚫을 기회가 있을 때
					Pair next = new Pair(nx, ny, 1);
					q.addLast(next);
					dist[next.status][next.x][next.y] = dist[cur.status][cur.x][cur.y] + 1;
					continue;
				}

				if (dist[cur.status][nx][ny] == 0) { // 다음 칸이 평지일 때
					Pair next = new Pair(nx, ny, cur.status);
					q.addLast(next);
					dist[next.status][next.x][next.y] = dist[cur.status][cur.x][cur.y] + 1;
				} 
			}
		}

		// 결과 확인
		// StringBuilder sb1 = new StringBuilder();
		// StringBuilder sb2 = new StringBuilder();
		// for (int i = 0; i < n; i++) {
		// 	for (int j = 0; j < m; j++) {
		// 		sb1.append(dist[0][i][j]);
		// 		sb2.append(dist[1][i][j]);
		// 	}
		// 	sb1.append("\n");
		// 	sb2.append("\n");
		// }
		// System.out.println(sb1);
		// System.out.println(sb2);

		int result1 = dist[0][n - 1][m - 1];
		int result2 = dist[1][n - 1][m - 1];
		if (result1 == 0 && result2 == 0) {
			System.out.println(-1);
		} else if (result1 == 0) {
			System.out.println(result2);
		} else if (result2 == 0) {
			System.out.println(result1);
		} else {
			System.out.println(Math.min(result1, result2));
		}
	}

	public void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dist = new int[2][n][m];
		for (int i = 0; i < n; i++) {
			String row = br.readLine();
			for (int j = 0; j < m; j++) {
				char c = row.charAt(j);
				if (c == '1') {
					dist[0][i][j] = -1;
					dist[1][i][j] = -1;
				}
			}
		}
	}

	class Pair {

		int x, y;
		int status;

		Pair(int x, int y, int s) {
			this.x = x;
			this.y = y;
			this.status = s;
		}
	}
}