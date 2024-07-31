import sys
from queue import PriorityQueue
from collections import deque
import heapq

input = sys.stdin.readline
pq = []
n, m, k = map(int, input().strip().split())
A = [[] for _ in range(n + 1)]
D = [[] for _ in range(n + 1)]

for i in range(m):
    a, b, c = map(int, input().strip().split())
    A[a].append((b, c))

heapq.heappush(D[1], 0)
heapq.heappush(pq, (0, 1))
while pq:
    dist, now = heapq.heappop(pq)

    for next, distance in A[now]:
        cost = dist + distance
        if len(D[next]) < k:
            heapq.heappush(pq, (cost, next))
            heapq.heappush(D[next], -cost)

        elif distance + dist < -D[next][0]:
            heapq.heappush(pq, (cost, next))
            heapq.heappop(D[next])
            heapq.heappush(D[next], -cost)

for i in range(1, n + 1):
    if len(D[i]) == k:
        print(-D[i][0])
    else:
        print(-1)
