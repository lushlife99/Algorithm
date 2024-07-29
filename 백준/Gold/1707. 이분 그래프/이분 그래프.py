import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline
testCase = int(input())

def DFS(v, g):
    global visited, A, isBipartiteGraph
    visited[v] = g
    for i in A[v]:
        if visited[i] == 0:
            if visited[v] == 1:
                DFS(i, 2)
            else:
                DFS(i,1)
        elif visited[i] == g:
            isBipartiteGraph = False

for i in range(testCase):
    v, e = map(int, input().strip().split())
    visited = [0] * (v+1)
    isBipartiteGraph = True
    A = [[] for _ in range(v+1)]
    for _ in range(e):
        a, b = map(int, input().strip().split())
        A[a].append(b)
        A[b].append(a)

    for j in range(1, v+1):
        if visited[j] == 0:
            DFS(j, 1)

        if not isBipartiteGraph:
            break

    if isBipartiteGraph:
        print("YES")
    else: print("NO")
