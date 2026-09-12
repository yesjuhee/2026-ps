import java.util.*;

/*
k개의 숫자 제거

abcdefg

k개 제거 

앞에서부터 제일 큰걸 남겨야 함
"나를 지우면 뒤에게 더 큰가?" 검사해서 k번 지우기

i < i+1 일 때 i 삭제
4177252841
4 77252841
  77252841
  77 52841  
  77 5 841  
  77   841  
  7    841  
       841  

이런식으로 하면 n^2나옴 .. 맞나?

하나씩 쌓아가면서 검사하기 with stack
4177252841

1924
k=3

1
94

*/

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        
        int removeCount = 0;
        for (int i = 0; i < number.length(); i++) {
            char next = number.charAt(i);
            
            while (sb.length() != 0) {
                if (removeCount == k) break;
                int a = sb.charAt(sb.length() - 1) - '0';
                int b = next - '0';
                if (a >= b) break;
                sb.deleteCharAt(sb.length() - 1);
                removeCount++;
                
            }
            sb.append(next);
        }
        
        while(removeCount != k) {
            sb.deleteCharAt(sb.length() - 1);
            removeCount++;
        }
        
        return sb.toString();
    }
}