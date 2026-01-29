# 좌표 정렬하기

import sys
input = sys.stdin.readline

# 입력
n = int(input())
array = []
for _ in range(n):
    array.append(list(map(int, input().split())))

# 정렬
array.sort()

# 출력
for i in range(n):
    print(f'{array[i][0]} {array[i][1]}')