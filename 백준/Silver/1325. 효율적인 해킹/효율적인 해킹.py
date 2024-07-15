import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().strip().split())
list = [[] for i in range(n+1)]
visited = [False] * (n+1)
dq = deque()
D = [0] * (n+1)
for i in range(m):
    a, b = map(int, input().strip().split())
    list[b].append(a)

def BFS(v):
    visited[v] = True
    dq.append(v)

    while dq:
        newNode = dq.popleft()
        for i in list[newNode]:
            if not visited[i]:
                visited[i] = True
                D[v] += 1
                dq.append(i)



for i in range(1, n+1):
    BFS(i)
    visited = [False] * (n+1)

maxCount = max(D)
for i in range(1, n+1):
    if D[i] == maxCount:
        print(i, end=' ')
