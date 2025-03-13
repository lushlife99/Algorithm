import sys

sys.setrecursionlimit(100000)
input = sys.stdin.readline

def dfs(node):

    for next in graph[node]:
        if visited[next] == 0:
            visited[next] = node
            dfs(next)

N = int(input())
graph = [[] for _ in range(N+1)]
visited = [0 for _ in range(N+1)]

for i in range(N-1):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

visited[1] = 1
dfs(1)

for i in range(2, N+1):
    print(visited[i])