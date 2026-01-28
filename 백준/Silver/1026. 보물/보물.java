import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        s 값을 작게 만들기
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        int n;
        int[] a;
        int[] b;

        n = Integer.parseInt(br.readLine());
        a = new int[n];
        b = new int[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st1.nextToken());
            b[i] = Integer.parseInt(st2.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int s = 0;
        for (int i = 0; i < n; i++) {
            s += a[i] * b[n-1-i];
        }
        System.out.println(s);
	}
}
