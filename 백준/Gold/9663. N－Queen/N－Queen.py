import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

n = int(input())
visited_col = [False] * n
visited_diag1 = [False] * (2 * n)
visited_diag2 = [False] * (2 * n)
res = 0

def dfs(row):

    global n, res
    if row == n:
       res += 1

    for col in range(0, n):
        if not visited_col[col] and not visited_diag1[row + col] and not visited_diag2[row - col]:
            visited_col[col] = True
            visited_diag1[row + col] = True
            visited_diag2[row - col] = True
            dfs(row + 1)
            visited_col[col] = False
            visited_diag1[row + col] = False
            visited_diag2[row - col] = False

dfs(0)
print(res)

