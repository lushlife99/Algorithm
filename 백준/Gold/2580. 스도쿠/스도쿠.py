import sys
input = sys.stdin.readline
n = 9
arr=[[0] * n for _ in range(n)]
zero = []

def is_valid(row, col, num):

    if num in arr[row]:
        return False

    for r in range(n):
        if arr[r][col] == num:
            return False

    start_row = (row // 3) * 3
    start_col = (col // 3) * 3
    for i in range(start_row, start_row + 3):
        for j in range(start_col, start_col + 3):
            if arr[i][j] == num:
                return False

    return True
def dfs(cnt):
    if len(zero) == cnt:
        for num in arr:
            print(*num)
        exit()

    x = zero[cnt][0]
    y = zero[cnt][1]
    for num in range(1, n + 1):
        if is_valid(x, y, num):
            arr[x][y] = num
            dfs(cnt + 1)
            arr[x][y] = 0

for i in range(n):
    nums = list(map(int, input().split()))
    arr[i] = nums
    for j in range(len(nums)):
        if nums[j] == 0:
            zero.append([i, j])

dfs(0)
