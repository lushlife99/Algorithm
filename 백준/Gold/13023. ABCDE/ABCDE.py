import sys
sys.setrecursionlimit(1000000)
input = sys.stdin.readline

n, m = map(int, input().split())
myList = [[] for _ in range(n)]
visited = [False] * n
found = False

for i in range(m):
    a, b = map(int, input().split())
    myList[a].append(b)
    myList[b].append(a)

def DFS(s, count):
    global found
    if count == 4:
        found = True
        return

    visited[s] = True
    for i in myList[s]:
        if not visited[i]:
            DFS(i, count + 1)
            if found:
                return
    visited[s] = False

for i in range(n):
    DFS(i, 0)
    if found:
        break

if found:
    print(1)
else:
    print(0)
