import sys
sys.setrecursionlimit(100000)

N = int(input())
arr = sorted(list(map(int, input().split())))
visited = [False] * 2000000


def solve(idx, sum):

    if idx >= N:
        return

    for i in range(idx, N):
        visited[arr[i] + sum] = True
        solve(i+1, arr[i] + sum)



solve(0,0)

for i in range(1, len(visited)):
    if not visited[i]:
        print(i)
        exit()