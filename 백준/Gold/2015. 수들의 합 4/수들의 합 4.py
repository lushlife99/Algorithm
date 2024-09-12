# arr : 1 2 3 4 5 6
# sum : 1 3 6 10 15 21

# 3 4 5 구간합
# index : a = 2 b = 4
#
# sum[2] = arr[0] + arr[1] + arr[2]
# sum[4] = arr[0] + … + arr[4]
# sum[2:5] = sum[4] - sum[2] + arr[2]

# 시간 초과나서 검색해서 풂

N, K = map(int, input().split())
arr = list(map(int, input().split()))
res = 0
dict = {0 : 1} # 0을 1로 초기화 하는이유 : 자기 자신
sum_ = 0

for i in range(N):
    sum_ += arr[i]
    res += dict.get(sum_ - K, 0)
    dict[sum_] = dict.get(sum_, 0) + 1


print(res)

