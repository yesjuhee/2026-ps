# 다익스트라 알고리즘 : 개선된 구현 방법
import heapq
import sys
input = sys.stdin.readline
INF = int(1e9) # 1 * 10^9 (10억)

# 입력 1
n, m, x = map(int, input().split())

graph = [[] for i in range(n + 1)] # 간접 리스트, 각 행은 각 노드의 연결을 의미함

# 입력 2 - 간선 정보
for _ in range(m):
    a, b, c = map(int, input().split())  # a노드에서 b노드까지의 간선 비용이 c
    graph[a].append((b, c))              # 간접 리스트에 튜플 자료형을 이용해 저장

# 다익스트라 함수
def dijkstra(start, end, n):
    distance = [INF] * (n + 1)         # 최단 거리 테이블

    q = []  # 우선순위 큐, 최소 힙 사용, (계산된 거리, 도착 노드 인덱스) 튜플 저장
    # 시작 노드에 대해서 초기화
    heapq.heappush(q, (0, start))
    distance[start] = 0
    # q가 모두 pop 될 때까지 반복
    while q:
        # 우선순위 큐에서 최단 거리가 가장 짧은 노드 선택
        dist, now = heapq.heappop(q)
        # 이미 처리된 노드인지 확인
        if distance[now] < dist:
            continue
        # 선택된 now 노드와 인접한 노드들 확인
        for i in graph[now]:
            # i[0] : now와 연결된 노드 번호
            # i[1] : now와 해당 노드 사이의 거리
            cost = dist + i[1]    # now를 거쳐서 가는 경우의 비용
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))
    
    return distance[end]
            
# 오고 가는 시간 계산
result = [0] * (n + 1)
for i in range(n + 1):
    cost = dijkstra(i, x, n) + dijkstra(x, i, n)
    if cost < INF:
        result[i] = cost

# 출력
print(max(result))