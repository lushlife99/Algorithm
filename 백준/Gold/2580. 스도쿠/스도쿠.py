import sys
input = sys.stdin.readline
n = 9
arr=[list(map(int, input().split())) for _ in range(9)]
zero = []

def checkHeight(n,x):
    for idx in range(9):
        if arr[idx][n]==x:
            return False
    return True

def checkWidth(n,x):
    for idx in range(9):
        if arr[n][idx]==x:
            return False
    return True

def checkBox(n,m,k):
    x = n//3*3
    y = m//3*3
    for i in range(3):
        for j in range(3):
            if arr[x+i][y+j] == k:
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
        if checkHeight(y, num) == True and checkWidth(x, num) == True and checkBox(x, y, num) == True:
            arr[x][y] = num
            dfs(cnt + 1)
            arr[x][y] = 0



for i in range(n):
    for j in range(n):
        if arr[i][j] == 0:
            zero.append([i, j])

dfs(0)
