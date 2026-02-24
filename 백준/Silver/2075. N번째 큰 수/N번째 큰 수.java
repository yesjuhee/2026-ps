import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
        /*
        나이브 풀이
        전체 숫자 리스트로 저장 -> O(N^2)
        정렬 -> O(N^2logN^2)
        조회 -> O(1)
        가능하겠는데? 근데 아슬아슬함

        우선순위 큐 활용 
        전체 숫자 저장 
        -> 하나씩 넣으면 O(N^2logN^2)
        -> 전체 입력 받고 일괄 생성하면 -> O(N^2)
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        List<Integer> arr = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr.add(num * -1);
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(arr);

        for (int i = 0; i < n - 1; i++) {
            pq.poll();
        }

        System.out.println(pq.poll() * -1);
    }
}
