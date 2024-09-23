import sys
input = sys.stdin.readline

N = int(input())
budget = list(map(int, input().split()))
total = int(input())
left, right, ans = 0, max(budget), 0

while left <= right:
    mid = (left + right) // 2
    sum_ = 0
    sig = False
    for i in range(N):
        if budget[i] <= mid:
            sum_ += budget[i]

        else: sum_ += mid

        if sum_ > total:
            sig = True
            break

    if sig:
        right = mid - 1
    else :
        left = mid + 1
        ans = mid


print(ans)
