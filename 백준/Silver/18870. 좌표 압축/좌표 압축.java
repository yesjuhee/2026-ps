import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[] arr1; // 중복제거용
    int[] arr2; // 결과출력용
    List<Integer> sortedArr; // 중복 제거

	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        // 1. 입력
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr1 = new int[n];
        arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
            arr2[i] = arr1[i];
        }

        // 2. 정렬
        Arrays.sort(arr1);
        
        // 3. 중복 제거
        sortedArr = new ArrayList<>();
        int last = arr1[0];
        sortedArr.add(last);
        for (int i = 1; i < n; i++) {
            int num = arr1[i];
            if (num == last) continue;
            sortedArr.add(num);
            last = num;
        }
        
        // 4. 이분탐색 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(binarySearch(arr2[i])).append(" ");
        }
        System.out.println(sb);
    }

    int binarySearch(int target) {
        int start = 0;
        int end = sortedArr.size() - 1;

        while(start <= end) {
            int mid = (start + end) / 2;
            if (sortedArr.get(mid) > target) {
                end = mid - 1;
            } else if (sortedArr.get(mid) < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        
        return -1;
    }
}
