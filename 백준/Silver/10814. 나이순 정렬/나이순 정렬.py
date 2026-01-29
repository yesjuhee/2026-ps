# 나이순 정렬

# sort 함수의 key 파라미터 + 람다함수 이용해 정렬

import sys
input = sys.stdin.readline

n = int(input())
member_list = []
for _ in range(n):
    member = list(input().split())
    member[0] = int(member[0])
    member_list.append(member)

member_list.sort(key=lambda x: x[0])

for member in member_list:
    print(f'{member[0]} {member[1]}')
