import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    Pair[] times;
    long cnt = 0;
    
	public static void main(String[] args) throws IOException {
        /*
        회의실을 사용할 수 있는 회의의 최대 개수
        현재 시간이 t라고 할 때 시작 시간이 t 이상인 모든 회의 중에서
        가장 먼저 끝나는 회의를 선택하는 것이 최적해이다.

        제일 빨리 끝나는걸 선택해야 함 -> 끝나는 순으로 정렬
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());
        times = new Pair[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Pair time = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            times[i] = time;
        }

        // 정렬
        // 1. 끝나는 시간 오름차순
        // 2. 시작 시간 오름차순 
        Arrays.sort(times, (t1, t2) -> {
            if (t1.en != t2.en) return Long.compare(t1.en, t2.en);
            return Long.compare(t1.st, t2.st);
        });
        
        // 하나씩 꺼내서 판단
        long now = 0; // 지금까지 진행 시간
        for (int i = 0; i < n; i++) {
            Pair time = times[i];
            if (now > time.st) continue; // 시작시간이 지금보다 과거면 안됨
            cnt++;
            now = time.en; // 회의실 사용
        }

        System.out.println(cnt);
	}

    public class Pair {
        long st, en;
        public Pair(long st, long en) {
            this.st = st;
            this.en = en;
        }
    }
}
