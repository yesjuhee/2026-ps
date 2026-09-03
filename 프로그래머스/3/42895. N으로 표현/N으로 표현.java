import java.util.*;

/*
dp[k] = {N을 k개 사용해서 만들 수 있는 값들의 집합}

dp[1] = {N}
dp[k] = {dp[1] +-* / dp[k - 1], dp[k-1] -/ dp[1], N(1+10+...+10^(k-1))}
*/

class Solution {
    public int solution(int N, int number) {
        if (number == N) return 1; 
        
        Set<Long>[] dp = new HashSet[9];
        dp[1] = new HashSet<>();
        dp[1].add((long) N);
        
        for (int k = 2; k <= 8; k++) {            
            Set<Long> set = new HashSet<>();
            
//             for (int i = 1; i < k; i++) { // 이어붙이는 경우
//                 int con = concat(N, i);
//                 Set<Long> set2 = dp[k - i];
//                 for (long a: set2) {
//                     set.add(con + a);
//                     set.add(con - a);
//                     set.add(con * a);
//                     if (a!=0) set.add(con / a);
//                     set.add(a - con);
//                     if (con!=0) set.add(a / con);
//                 }
//             }
//             set.contains(concat(N, k));
            
//             if (set.contains((long) number)) return k;

//             // 없었으면 dp 에 저장
//             dp[k] = set;            
            
            // 모든 경우의 수 구하기, 찾으면 k return
            for (int i = 1; i <= k/2; i++) {
                Set<Long> set1 = dp[i];
                Set<Long> set2 = dp[k-i];
                for (long a: set1) {
                    for (long b: set2) {
                        set.add(a + b);
                        set.add(a - b);
                        set.add(a * b);
                        if (b!=0L) set.add(a / b);
                        set.add(b - a);
                        if (a!=0L) set.add(b / a);
                    }
                }
                 
            }
            Long tmp = 0L;
            for (int i = 0; i <= k - 1; i++) {
                tmp += (long) Math.pow(10, i);
            }
            set.add(tmp * (long) N);           

            if (set.contains((long) number)) return k;
            
            // 없었으면 dp 에 저장
            dp[k] = set;
            
            // System.out.printf("%d 개 사용: %s\n", k, set);
        }

        return -1;
    }
    
    // int concat(int n, int length) {
    //     int result = 0;
    //     for (int i = 0; i < length; i++) {
    //         result += Math.pow(10, i);
    //     }
    //     return result;
    // }
}