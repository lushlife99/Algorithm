import sys

input = sys.stdin.readline

N, M = map(int, input().split())
arr = []
for i in range(M):
    arr.append(int(input()))

left, right, ans = 1, max(arr), 0

while left <= right:
    mid = (left + right) // 2
    sum_ = 0
    for i in range(M):
        if arr[i] % mid == 0:
            sum_ += (arr[i] // mid)
        else:
            sum_ += (arr[i] // mid + 1)

    if sum_ > N:
        left = mid + 1

    else:
        right = mid - 1
        ans = mid

print(ans)
