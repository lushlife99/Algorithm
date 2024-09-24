import sys

N, M = map(int, input().split())
arr = []

for _ in range(N):
    arr.append(int(sys.stdin.readline()))

arr.sort()
left, right, ans = 0, max(arr) * M, 0

while left <= right:
    mid = (left + right) // 2
    sum_ = 0
    visited = [0] * N
    for i in range(N):
        sum_ += mid // arr[i]

    if sum_ >= M:
        right = mid - 1

    else : left = mid + 1

print(left)
