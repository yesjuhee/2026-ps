import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
        /*
        1. A 입력
        2. B 입력
        3. b순회 -> A에서 b 제거  => O(N^2)
        4. A 출력

        O(nlogn) 으로 정렬하고 binary Search 로 탐색
        아니면 hash set 으로 풀면
        제거 O(1) -> O(N)
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nA = Integer.parseInt(st.nextToken());
        int nB = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        Set<Integer> setA = new HashSet<>();
        for (int i = 0; i < nA; i++) {
            setA.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nB; i++) {
            int n = Integer.parseInt(st.nextToken());
            setA.remove(n);
        }

        if (setA.size() == 0) {
            System.out.println(0);
            return;
        } 
        StringBuilder sb = new StringBuilder();
        List<Integer> nums = new ArrayList<>(setA);
        Collections.sort(nums);
        sb.append(nums.size()).append("\n");
        for (int i = 0; i < nums.size(); i++) {
            sb.append(nums.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}
