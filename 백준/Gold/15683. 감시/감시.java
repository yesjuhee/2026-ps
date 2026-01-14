import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    Map map;
    List<CCTV> cctvs = new ArrayList<>();
    // int[] isRowWatched; // i 번째 행 감시 카메라 몇개?
    // int[] isColumnWatched; // i 번째 열 감시 카메라 몇개?
    int[][] watchCount; // 해당 지점을 보고 있는 카메라 개수

	public static void main(String[] args) throws IOException {
        /*
        0: 빈칸
        6: 벽
        1 ~ 5: CCTV 번호
        CCTV의 방향을 적절히 정해서, 사각 지대 최소 크기를 구하는 프로그램
        전체 크기에서 벽, CCTV, 감시 영역 제외 나머지 크기
        감시 영역을 최대로 -> 겹치는 범위를 최소로
        */
		new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        init();

        // 백트래킹 -> 모든 경우의 수 탐색
        func(0);

        // 출력
        System.out.println(map.min);
	}

    public void func(int k) { // k 번째 CCTV 회전, 1 ~ k-1 는 이미 회전 함
        if (k == cctvs.size()) {
            // 트리의 마지막 -> 여기서 map의 minimum 계산
            map.calculateMin();
            return;
        }

        CCTV cur = cctvs.get(k);
        while(!cur.finishRotation()) {
            cur.watch(1); // 지금 상태에서 감시
            func(k + 1); // 다음 CCTV 확인
            cur.watch(-1); // 지금 상태 감시 해제
            cur.next(); // 다음 상태로 업데이트
        }
    }

    class Map {
        
        char[][] map;
        int min; // 사각지대 최소 크기

        Map(int n, int m) {
            this.map = new char[n][m];
            this.min = 64;
        }

        public void calculateMin() {
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != '0') continue; // CCTV, 벽
                    if (watchCount[i][j] > 0) continue; // 감시 중
                    count++; // 사각지대
                }
            }
            if (min > count) {
                min = count;
            }
            // System.out.println(min);        
            // print();
        }

        public boolean hasWall(int x, int y) {
            return map[x][y] == '6';
        }

        public void print() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    sb.append(watchCount[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }

    abstract class CCTV {

        int x, y;
        int rotationStatus;
        int finishRotationCount;
        boolean isFinished;

        public CCTV (int x, int y) {
            this.x = x;
            this.y = y;
            this.rotationStatus = 0;
        }

        boolean finishRotation() {
            if (isFinished) {
                isFinished = false;
                return true;
            }
            return false;
        }

        abstract void watch(int a);
        
        void next() {
            rotationStatus++;
            if (rotationStatus == finishRotationCount) {
                isFinished = true;
                rotationStatus = 0;
            }
        }

        void east(int a) {
            int i = x;
            for (int j = y + 1; j < m; j++) {
                if(map.hasWall(i, j)) break;
                watchCount[i][j] += a;
            }            
        }

        void west(int a) {
            int i = x;
            for (int j = y - 1; j >= 0; j--) {
                if(map.hasWall(i, j)) break;                    
                watchCount[i][j] += a;
            }            
        }

        void south(int a) {
            int j = y;
            for (int i = x + 1; i < n; i++) {
                if(map.hasWall(i, j)) break;                    
                watchCount[i][j] += a;
            }            
        }

        void north(int a) {
            // 자기 보다 북쪽
            int j = y;
            for (int i = x - 1; i >= 0; i--) {
                if(map.hasWall(i, j)) break;                    
                watchCount[i][j] += a;
            }            
        }
    }

    class CCTV1 extends CCTV {

        public CCTV1 (int x, int y) {
            super(x, y);
            finishRotationCount = 4;
        }

        public void watch(int a) {
            if (rotationStatus == 0) { // 자기 보다 동쪽
                east(a);
            } else if (rotationStatus == 1) { // 자기 보다 서쪽
                west(a);
            } else if (rotationStatus == 2) { // 자기 보다 남쪽
                south(a);
            } else {
                north(a);
            }
        }
    }
    class CCTV2 extends CCTV {

        public CCTV2 (int x, int y) {
            super(x, y);
            finishRotationCount = 2;
        }

        public void watch(int a) {
            if (rotationStatus == 0) { // 같은 행
                east(a);
                west(a);
            } else { // 같은 열
                north(a);
                south(a);
            }
        }        
    }
    class CCTV3 extends CCTV {

        public CCTV3 (int x, int y) {
            super(x, y);
            finishRotationCount = 4;
        }     
        
        public void watch(int a) {
            if (rotationStatus == 0) { // 북동
                north(a);
                east(a);
            } else if (rotationStatus == 1) { // 동남
                east(a);
                south(a);
            } else if (rotationStatus == 2) { // 남서
                south(a);
                west(a);
            } else { // 서북
                west(a);
                north(a);
            }
        }         
    }
    class CCTV4 extends CCTV {
        
        public CCTV4 (int x, int y) {
            super(x, y);
            finishRotationCount = 4;
        }
        
        public void watch(int a) {
            if (rotationStatus == 0) { // 북 + 같은 행
                north(a);
                west(a);
                east(a);
            } else if (rotationStatus == 1) { // 남 + 같은 행
                west(a);
                east(a);
                south(a);
            } else if (rotationStatus == 2) { // 동 + 같은 열
                north(a);
                south(a);                
                west(a);
            } else { // 서 + 같은 열
                north(a);
                south(a);                
                east(a);                
            }
        }            
    }
    class CCTV5 extends CCTV {
        
        public CCTV5 (int x, int y) {
            super(x, y);
            finishRotationCount = 1;
        }        

        public void watch(int a) {
            north(a);
            south(a);                
            east(a);
            west(a);
        }         
    }
    
    public void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        watchCount = new int[n][m];
        map = new Map(n, m);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                char v = st.nextToken().charAt(0);
                map.map[i][j] = v;
                if (v != '0' && v != '6') {
                    if (v == '1') {
                        cctvs.add(new CCTV1(i, j));
                    } else if (v == '2') {
                        cctvs.add(new CCTV2(i, j));
                    } else if (v == '3') {
                        cctvs.add(new CCTV3(i, j));
                    } else if (v == '4') {
                        cctvs.add(new CCTV4(i, j));
                    } else {
                        cctvs.add(new CCTV5(i, j));
                    }
                }
            }
        }
    }
}
