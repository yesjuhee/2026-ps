import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[] arr;
    int m;
    StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Arrays.sort(arr);
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            int under = underBound(target);
            int upper = upperBound(target);
            sb.append(upper - under).append(" ");
        }

        System.out.println(sb);
    }

    int underBound(int target) {
        /*
        target이 배열에 있는 경우 -> 최초 등장 위치
        target이 배열이 없는 경우 -> target 보다 큰 수가 최초로 등장한 위치
        arr[mid] > target -> mid 이하 
        arr[mid] == target -> mid 이하
        arr[mid] < target -> mid 초과
        */
        int start = 0;
        int end = n;

        while(start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= target) { // 최종 위치는 mid 이하
                end = mid;
            } else { // 최종 위치는 mid 초과
                start = mid + 1;
            }
        }

        return start;
    }

    int upperBound(int target) {
        /*
        target이 배열에 있는 경우 -> target 보다 큰 수가 최초로 등장한 위치
        target이 배열이 없는 경우 -> target 보다 큰 수가 최초로 등장한 위치
        arr[mid] > target -> mid 이하 
        arr[mid] == target -> mid 초과
        arr[mid] < target -> mid 초과
        */
        int start = 0;
        int end = n;

        while(start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) { // 최종 위치는 mid 이하
                end = mid;
            } else { // 최종 위치는 mid 초과
                start = mid + 1;
            }
        }

        return start;        
    }
}
