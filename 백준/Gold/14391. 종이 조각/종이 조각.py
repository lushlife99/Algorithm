row, col = map(int, input().split())
arr = [list(map(str, input())) for _ in range(row)]
max_value = 0

for i in range(0, 1 << (row * col)):
    S = i
    answer = 0

    for i in range(row):
        res = 0
        for j in range(col):
            digit = 1 << ((row-1-i) * col + (col-1-j))
            if S & digit:
                res = res * 10 + int(arr[i][j])

            else:
                answer += res
                res = 0

        answer += res

    for i in range(col):
        res = 0
        for j in range(row):
            digit = 1 << ((row-1-j) * col + (col-1-i))
            if S & digit == 0:
                res = res * 10 + int(arr[j][i])

            else:
                answer += res
                res = 0

        answer += res

    max_value = max(answer, max_value)

print(max_value)
