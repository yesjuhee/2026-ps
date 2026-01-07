import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	long a, b, c;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		c = Long.parseLong(st.nextToken());

		System.out.println(func(b));
	}

	// a의 b승 구하기
	public long func(long bb) {
		if (bb == 1) {
			return a % c;
		}
		long val = func(bb / 2);
		if (bb % 2 == 0) {
			return (val * val) % c;
		} else {
			return (((val * val) % c) * a) % c;
		}
	}
}