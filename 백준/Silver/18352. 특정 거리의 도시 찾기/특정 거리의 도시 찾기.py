import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline
from collections import deque

n, m, k, x = map(int, input().strip().split()) # 도시의 개수, 도로의 개수, 거리 정보, 출발 도시의 번호
list = [[] for i in range(n+1)]
D = [0 for i in range(n+1)]
visited = [False] * (n+1)
dq = deque()
answerList = []
for i in range(m):
    a, b = map(int, input().strip().split())
    list[a].append(b)

def BFS(v):
    visited[v] = True
    dq.append(v)
    while dq:
        newNode = dq.popleft()
        for i in list[newNode]:
            if not visited[i]:
                dq.append(i)
                D[i] = D[newNode] + 1
                visited[i] = True

BFS(x)

for i in range (n+1):
    if D[i] == k:
        answerList.append(i)

if answerList:
    for i in answerList:
        print(i)

else: print(-1)
