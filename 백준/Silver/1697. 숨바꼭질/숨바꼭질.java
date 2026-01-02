import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n, k;
	Deque<Pair> q = new ArrayDeque<>();
	boolean[] visited;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// n = 5, k = 17
		// 5 10 9 18 17
		// 이동할 수 있는 방법은 3가지
		// x-1, x+1, x*2
		// 비슷하게 큐를 만들어서 가장 적은 이동 시간 구하기?
		// q: 5
		// q: 4, 6, 10  : 1
		// q: 3, 5, 8, ..., 9, 11, 20 : 2
		// q: ... 8, 10, 18, ... : 3
		// q: ... 9, 11, 20, 17 -> 답
		// 가지가 3개씩 뻗어 나가는 트리의 형태! -> BFS

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int ceil = Math.max(n, k) * 2;
		visited = new boolean[ceil];

		if (n == k) {
			System.out.println(0);
			return;
		}

		Pair start = new Pair(n, 0);
		q.addLast(start);
		visited[start.v] = true;

		// BFS
		while(!q.isEmpty()) {
			Pair cur = q.removeFirst();
			List<Integer> nexts = List.of(cur.v - 1, cur.v + 1, cur.v * 2);
			for (int next: nexts) {
				if (next < 0 || next >= ceil || visited[next] == true) {
					continue;
				}
				if (next == k) {
					System.out.println(cur.d + 1);
					return;
				}
				q.addLast(new Pair(next, cur.d + 1));
				visited[next] = true;
			}
		}

	}

	class Pair {

		int v; // value
		int d; // distance

		public Pair(int v, int d) {
			this.v = v;
			this.d = d;
		}
	}
}