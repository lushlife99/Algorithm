import heapq

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]
ans = 0
visited = set()
result = []
# i j k l m n o p
for i in range(1, N-1):
    for j in range(1, N-1):
        arr1 = {(i, j), (i+1, j), (i-1, j), (i, j+1), (i, j-1)}
        sum1 = arr[i][j] + arr[i+1][j] + arr[i-1][j] + arr[i][j+1] + arr[i][j-1]
        visited.update(arr1)

        for k in range(i, N-1):
            l_start = j + 1 if k == i else 1
            for l in range(l_start, N-1):
                arr2 = {(k,l), (k+1, l), (k-1, l), (k, l+1), (k, l-1)}
                sum2 = arr[k][l] + arr[k+1][l] + arr[k-1][l] + arr[k][l+1] + arr[k][l-1]

                if arr2 & visited:
                    continue

                visited.update(arr2)

                for m in range(k, N-1):
                    n_start = l + 1 if m == k else 1
                    for n in range(n_start, N-1):
                        arr3 = {(m, n), (m+1, n), (m-1, n), (m, n+1), (m, n-1)}
                        sum3 = arr[m][n] + arr[m+1][n] + arr[m-1][n] + arr[m][n+1] + arr[m][n-1]

                        if not arr3 & visited:
                            result.append(sum1 + sum2 + sum3)

                visited.difference_update(arr2)

        visited.difference_update(arr1)

result.sort()
print(result[0])
