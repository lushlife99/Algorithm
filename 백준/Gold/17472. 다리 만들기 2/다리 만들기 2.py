import sys
import heapq

sys.setrecursionlimit(100000)
input = sys.stdin.readline
INF = sys.maxsize

n, m = map(int, input().split())

graph = [[] for _ in range(n)]
island = [[0 for _ in range(m)] for _ in range(n)]
visited = [[False for _ in range(m)] for _ in range(n)]
edges = []
def dfs(x, y, count):
    visited[x][y] = True
    island[x][y] = count
    if 0 <= x - 1 < n and graph[x-1][y] == 1 and not visited[x-1][y]:
        dfs(x-1, y, count)

    if 0 <= x + 1 < n and graph[x+1][y] == 1 and not visited[x+1][y]:
        dfs(x+1, y, count)

    if 0 <= y - 1 < m and graph[x][y-1] == 1 and not visited[x][y-1]:
        dfs(x, y-1, count)

    if 0 <= y + 1 < m and graph[x][y+1] == 1 and not visited[x][y+1]:
        dfs(x, y+1, count)

def count_edges():
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 1:
                cost = 0
                for k in range(j+1, m):
                    if graph[i][k] == 0:
                        cost += 1
                    else:
                        if island[i][j] != island[i][k]:
                            if cost > 1:
                                min_island = min(island[i][j], island[i][k])
                                max_island = max(island[i][j], island[i][k])

                                if not edges.__contains__((cost, min_island, max_island)):
                                    heapq.heappush(edges, (cost,min_island, max_island))
                                break
                            else :
                                break
                        else:
                            cost = 0

    for i in range(m):
        for j in range(n):
            if graph[j][i] == 1:
                cost = 0
                for k in range(j+1, n):
                    if graph[k][i] == 0:
                        cost += 1
                    else:
                        if island[j][i] != island[k][i]:
                            if cost > 1:
                                min_island = min(island[j][i], island[k][i])
                                max_island = max(island[j][i], island[k][i])

                                if not edges.__contains__((cost, min_island, max_island)):
                                    heapq.heappush(edges, (cost,min_island, max_island))
                                break
                            else :
                                break
                        else:
                            cost = 0

for i in range(n):
    inputs = list(map(int, input().split()))
    for j in range(m):
        graph[i].append(inputs[j])

count = 0
for i in range(n):
    for j in range(m):
        if not visited[i][j] and graph[i][j] == 1:
            dfs(i, j, count+1)
            count += 1

count_edges()

parents = [i for i in range(count + 1)]
useEdges = 0

def find(a):
    if a == parents[a]:
        return a

    parents[a] = find(parents[a])
    return parents[a]

def union(a, b):
    a = find(a)
    b = find(b)
    parents[a] = min(a,b)
    parents[b] = min(a,b)

result = 0

while edges and useEdges < count - 1:
    w, s, e = heapq.heappop(edges)
    if find(s) != find(e):
        result += w
        useEdges += 1
        union(s,e)

if useEdges < count - 1:
    print(-1)

else:
    print(result)

