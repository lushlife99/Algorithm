import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

cnt = 0
n = int(input())
graph = [[] for _ in range(n+1)]
visited = [False] * (n+1)
distance = [0] * (n+1)
for i in range(n-1):
    s, e, w = map(int, input().split())
    graph[s].append((e, w))
    graph[e].append((s, w))


def DFS(v):
    visited[v] = True

    for node, w in graph[v]:
        if not visited[node]:
            visited[node] = True
            distance[node] = distance[v] + w
            DFS(node)

DFS(1)
maxCost = 0
maxNode = -1
for i in range(1, n+1):
    if distance[i] > maxCost:
        maxNode = i
        maxCost = distance[i]

visited = [False] * (n+1)
distance = [0] * (n+1)
DFS(maxNode)

print(max(distance))