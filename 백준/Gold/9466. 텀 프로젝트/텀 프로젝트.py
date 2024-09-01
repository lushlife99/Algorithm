import sys
sys.setrecursionlimit(100000)

def dfs(v):
    global result
    visited[v] = True
    cycle.append(v)

    next_student = students[v]
    if visited[next_student]:
        if next_student in cycle:
            result += cycle[cycle.index(next_student):]
        return
    else:
        dfs(next_student)

t = int(input())

for _ in range(t):
    n = int(input())
    students = [0] + list(map(int, input().split()))
    visited = [False] * (n + 1)
    result = []

    for i in range(1, n + 1):
        if not visited[i]:
            cycle = []
            dfs(i)

    print(n - len(result))
