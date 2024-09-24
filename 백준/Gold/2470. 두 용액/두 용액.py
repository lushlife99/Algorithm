import sys
input = sys.stdin.readline

N = int(input())
arr = sorted(list(map(int, input().split())))

if arr[0] < 0 and arr[N-1] > 0:
    pos_idx = sys.maxsize
    for i in range(N):
        if arr[i] > 0:
            pos_idx = i
            break

    answer, min_sum = (arr[0], arr[N-1]), abs(arr[0] + arr[N-1])

    for i in range(0, pos_idx):
        left, right = pos_idx, N-1

        while left <= right:
            mid = (left + right) // 2
            sum_ = arr[i] + arr[mid]

            if abs(sum_) < min_sum:
                min_sum = abs(sum_)
                answer = arr[i], arr[mid]

            if sum_ < 0:
                left = mid + 1
            else:
                right = mid - 1

    if pos_idx + 1 < N:
        if abs(arr[pos_idx] + arr[pos_idx + 1]) < min_sum:
            answer = arr[pos_idx], arr[pos_idx + 1]

    if pos_idx - 2 >= 0:
        if abs(arr[pos_idx - 2] + arr[pos_idx - 1]) < min_sum:
            answer = arr[pos_idx - 2], arr[pos_idx - 1]

    print(answer[0], answer[1])

elif arr[0] > 0:
    print(arr[0], arr[1])

else:
    print(arr[N-2], arr[N-1])
