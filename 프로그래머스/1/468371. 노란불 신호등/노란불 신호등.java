class Solution {
    
    /*
    구: 모든 신호등이 노란불이 되는 가장 빠른 시각 / 존재하지 않으면 -1
    최소 공배수
    아니면 그냥 돌려보기 
    
    -1 이 되는 경우는 언제??
    최대 공배수까지 돌려서 시뮬레이션 하기
    */
    
    int n;
    int[] sums;
    int[][] signals;
    
    public int solution(int[][] signals) {
        // 최대 시간 (최소공배수) 구하기
        this.signals = signals;
        n = signals.length;
        sums = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                sums[i] += signals[i][j];
            }
        }
        int maxTime = getLCM(sums);
        
        for (int now = 1; now <= maxTime; now++) {
            // 지금이 노란색인지 확인 -> 하나라도 아닌게 있으면 패스
            if (isAllYellow(now)) return now;
        }
        return -1;
    }
    
    public boolean isAllYellow(int now) {
        for (int i = 0; i < n; i++) {
            int[] signal = signals[i];
            if (!isYellow(signal, now, sums[i])) return false;
        }
        return true;
    }
    
    public boolean isYellow(int[] signal, int now, int sum) {
        now %= sum;
        int G = signal[0];
        int Y = signal[1];
        // System.out.printf("now, G, Y : %d, %d, %d\n", now, G, Y);
        return now > G && now <= G + Y;
    }
    
    // 최대공약수 구하기 
    public int getGCD(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    
    // 최소공배수 구하기 
    public int getLCM(int a, int b) {
        return (a / getGCD(a, b)) * b;
    }
    
    public int getLCM(int[] arr) {
        int result = 1;
        for (int n: arr) {
            result = getLCM(result, n);
        }
        return result;
    }
    
    
    
}