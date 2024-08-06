import sys
import heapq

sys.setrecursionlimit(100000)
input = sys.stdin.readline
INF = sys.maxsize

n = int(input())
graph = []
totalCost = 0
useCost = 0
parents = [i for i in range(n + 1)]

def find(a):
    if a == parents[a]:
        return a

    parents[a] = find(parents[a])
    return parents[a]

def union(a, b):
    a = find(a)
    b = find(b)
    parents[a] = min(a, b)
    parents[b] = min(a, b)

for i in range(n):
    lanCosts = str(input().strip())
    for j in range(len(lanCosts)):
        c = lanCosts[j]
        lanCost = 0
        if ord(c) == ord('0'):
            lanCost = 0
        elif ord(c) >= ord('a'):
            lanCost = ord(c) - ord('a') + 1
            heapq.heappush(graph, (lanCost, i, j))
        else:
            lanCost = ord(c) - ord('A') + 27
            heapq.heappush(graph, (lanCost, i, j))

        totalCost += lanCost

useEdges = 0
while graph and useEdges < n - 1:
    cost, start, end = heapq.heappop(graph)
    if find(start) != find(end):
        useEdges += 1
        useCost += cost
        union(start, end)

if useEdges < n - 1:
    print(-1)

else: print(totalCost - useCost)