import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline
INF = sys.maxsize

v, e = map(int, input().split())
graph = []
cycle = [i for i in range(v+1)]
answer = []

def find(a):
    if cycle[a] == a:
        return a

    cycle[a] = find(cycle[a])
    return cycle[a]

def union(a, b):
    a = find(a)
    b = find(b)
    cycle[a] = min(a,b)
    cycle[b] = min(a,b)

for i in range(e):
    a, b, c = map(int, input().split())
    graph.append((a, b, c))

graph.sort(key=lambda x: x[2])
count = 0
for i in range(e):
    start, end, cost = graph[i]
    if find(start) == find(end):
        continue

    answer.append((start, end, cost))
    union(start, end)
    count += 1
    if count == v:
        break
sum = 0
for edge in answer:
    sum += edge[2]

print(sum)
