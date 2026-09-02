import java.util.*;

/*
종이 조각으로 만들 수 있는 소수가 몇 개인지?

모든 경우의 수 구하기 (백트래킹, 인덱스 선택) -> 숫자로 변환하기 -> 소수인지 판단 + HashSet으로 관리

모든 경우의 수 구하기 -> 다 다른걸로 보고 순열 
한자리수 ~ 일곱자리수
*/

class Solution {
    
    Set<Integer> primes = new HashSet<>(); // 가능한 소수 모두 저장
    boolean[] isUsed;
    int n;  // numbers 개수
    
    public int solution(String numbers) {
        n = numbers.length();
        isUsed = new boolean[n];
        
        for (int targetLength = 1; targetLength <= n; targetLength++) {
            func(targetLength, new StringBuilder(), numbers);
        }
        
        return primes.size();
    }
    
    void func(int tl, StringBuilder sb, String numbers) {
        if (sb.length() == tl) {
            int target = Integer.parseInt(sb.toString());
            if (isPrime(target)) {
                primes.add(target);
            }
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (isUsed[i]) continue;
            isUsed[i] = true;
            sb.append(numbers.substring(i, i + 1));
            func(tl, sb, numbers);
            sb.delete(sb.length()-1, sb.length());
            isUsed[i] = false;
        }
    }
    
    boolean isPrime(int num) {
        if (num <= 1) return false;
        
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        
        return true;
    } 
}