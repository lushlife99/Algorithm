import sys, heapq

input = sys.stdin.readline

N, M = map(int, input().split())
arr = list(map(int, input().split()))
left, right, ans = 0, sys.maxsize, 0

def solve(remain, time):
    arr2 = []
    abs_time, cnt = time, 0
    for i in range(M):
        heapq.heappush(arr2, ((time // arr[i] + 1) * arr[i], i + 1))

    heapq.heapify(arr2)
    while cnt < remain:
        while arr2[0][0] == abs_time:
            t, i = heapq.heappop(arr2)
            cnt += 1
            if cnt == remain:
                return i

            heapq.heappush(arr2, (abs_time + arr[i - 1], i))

        abs_time = arr2[0][0]

if N <= M:
    print(N)
else:
    while left <= right:
        mid = (left + right) // 2
        sum_ = 0
        for i in range(M):
            sum_ += 1 + mid // arr[i]

        if N - M <= sum_ < N:
            ans = solve(N - sum_, mid)
            break

        elif sum_ >= N:
            right = mid - 1

        else:
            left = mid + 1

    print(ans)
