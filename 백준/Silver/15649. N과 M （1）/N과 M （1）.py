import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

n, m = map(int, input().split())
visited = [False] * n
arr = [0] * m
def dfs(n, m, depth):
    if depth == m:
        for i in range(len(arr)):
            print(arr[i], end=' ')

        print()
        return

    for i in range(n):
        if(not visited[i]):
            visited[i] = True
            arr[depth] = i + 1
            dfs(n, m, depth + 1)
            visited[i] = False

dfs(n, m, 0)