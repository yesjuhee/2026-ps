import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    long[] nums;
    int maxCount = 0;
    long maxNum;
    
	public static void main(String[] args) throws IOException {
        /*
        int -> 4 byte -> 2^32
        long -> 8 byte -> 2^64 -> -2^63 ~ 2^63 - 1
        long 범위로 커버는 됨
        입력되는 개수가 작음
        1. 전체 입력
        2. 정렬
        3. 순회하면서 카운트
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());
        nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        // 정렬
        Arrays.sort(nums);

        maxCount = 0;
        maxNum = nums[0];
        long prev = nums[0];
        int count = 1;
        for (int i = 1; i < n; i++) {
            long now = nums[i];
            if (now == prev) {
                count++;
                if (count > maxCount) { // 최대값 갱신
                    maxNum = now;
                    maxCount = count;
                }
            } else {
                // 다음 숫자
                count = 1;
                prev = now;
            }
        }

        System.out.println(maxNum);
	}
}
