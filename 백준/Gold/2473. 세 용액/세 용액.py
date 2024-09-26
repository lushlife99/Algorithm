import sys
input = sys.stdin.readline

N = int(input())
arr = sorted(list(map(int, input().split())))
left, right, ans, min_sum = 0, 3000000000, (0, 0, 0), sys.maxsize

if arr[0] >= 0:
    print(arr[0], arr[1], arr[2])

elif arr[-1] <= 0:
    print(arr[N-3], arr[N-2], arr[N-1])

else:
    for i in range(N):
        for j in range(i+1, N):
            left, right = j+1, N-1

            while left <= right:
                mid = (left + right) // 2
                sum_ = arr[i] + arr[j] + arr[mid]

                if abs(sum_) < min_sum:
                    min_sum = abs(sum_)
                    ans = arr[i], arr[j], arr[mid]

                if sum_ < 0:
                    left = mid + 1

                else: right = mid - 1

    print(ans[0], ans[1], ans[2])

