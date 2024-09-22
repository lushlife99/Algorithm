
import sys
input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split()))
left, right, ans = 1, (N-1) * (1 + abs(arr[0] - arr[N-1])), 0

while left <= right:
    mid = (left + right) // 2
    sig = False
    visited = [True] + [False] * (N - 1)
    stack = [0]

    while stack:
        current = stack.pop()

        if current == N-1: # 끝까지 탐색한경우. mid
            sig = True
            break

        for i in range(current + 1, N):
            if (i - current) * (1 + abs(arr[i] - arr[current])) <= mid and not visited[i]:
                stack.append(i)
                visited[i] = True

    if sig:
        right = mid - 1
        ans = mid

    else:
        left = mid + 1

print(ans)