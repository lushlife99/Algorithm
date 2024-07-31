import sys
import heapq

input = sys.stdin.readline
pq = []
n, m, k = map(int, input().strip().split())
A = [[] for _ in range(n + 1)]
D = [[] for _ in range(n + 1)]

for _ in range(m):
    a, b, c = map(int, input().strip().split())
    A[a].append((b, c))

# K번째 최단 경로를 저장할 리스트 초기화
heapq.heappush(D[1], 0)
heapq.heappush(pq, (0, 1))

while pq:
    dist, now = heapq.heappop(pq)

    for next, distance in A[now]:
        cost = dist + distance

        # K개의 경로를 저장하지 않은 경우 추가
        if len(D[next]) < k:
            heapq.heappush(D[next], -cost)
            heapq.heappush(pq, (cost, next))

        # 이미 K개의 경로를 저장한 경우, 가장 긴 경로보다 짧으면 교체
        elif -D[next][0] > cost:
            heapq.heappop(D[next])
            heapq.heappush(D[next], -cost)
            heapq.heappush(pq, (cost, next))

for i in range(1, n + 1):
    if len(D[i]) == k:
        print(-D[i][0])
    else:
        print(-1)
