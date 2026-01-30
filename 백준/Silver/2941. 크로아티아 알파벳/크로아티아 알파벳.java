import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        lj e s= nj a k : 6
        d dz= z=
        replace 써서 바꾼 다음에 교체
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        String str = br.readLine();
        str = str.replace("c=", "a");
        str = str.replace("c-", "a");
        str = str.replace("dz=", "a");
        str = str.replace("d-", "a");
        str = str.replace("lj", "a");
        str = str.replace("nj", "a");
        str = str.replace("s=", "a");
        str = str.replace("z=", "a");

        System.out.println(str.length());
	}
}
