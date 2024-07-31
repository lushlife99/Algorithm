import sys
from collections import deque
sys.setrecursionlimit(100000)
input = sys.stdin.readline

v, e = map(int, input().strip().split())
start = int(input())
A = [[] for _ in range(v+1)]
D = [999999999] * (v+1)
D[start] = 0
visited = [False] * (v+1)
dq = deque()

for i in range(e):
    a, b, c = map(int, input().strip().split())
    A[a].append((b, c))

dq.append(start)

while dq:
    now = dq.popleft()
    visited[now] = True

    for next, distance in A[now]:
        if D[next] > D[now] + distance:
            D[next] = D[now] + distance
    min_distance = 999999999
    min_node = v+1
    for i in range(1, v+1):
        if not visited[i]:
            if D[i] < min_distance:
                min_node = i
                min_distance = D[i]

    if min_node != v+1:
        dq.append(min_node)

for i in range(1, v+1):
    if not visited[i]:
        print("INF")

    else: print(D[i])