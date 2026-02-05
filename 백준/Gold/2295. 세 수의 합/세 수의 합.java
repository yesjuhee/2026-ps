import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    long[] arr;
    long max = -1; // 최대 값
    long[] two;

	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        init();
        Arrays.sort(arr);
        calculateTwoSum();
        Arrays.sort(two);

        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                long diff = Math.abs(arr[i] - arr[j]);
                if (Arrays.binarySearch(two, diff) >= 0) {
                    // 존재함
                    max = Math.max(arr[i], max);
                }
            }
        }

        System.out.println(max);
    }

    public void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
    }

    public void calculateTwoSum() {
        two = new long[n * n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                two[idx++] = arr[i] + arr[j];
            }
        }
    }
}
