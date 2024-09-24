import sys
input = sys.stdin.readline

N, C = map(int, input().split())
arr = [int(input()) for _ in range(N)]
arr.sort()
left, right, ans = 1, arr[N-1] - arr[0], 0

while left <= right:
    mid = (left + right) // 2
    current = arr[0]
    cnt = 1

    for i in range(1, N):
        if arr[i] - current >= mid:
             cnt += 1
             current = arr[i]

    if cnt >= C:
        left = mid + 1
        ans = mid

    else:
        right = mid - 1

print(ans)