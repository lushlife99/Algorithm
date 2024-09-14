# 시간의 길이가 21억..
# s , e -> (시간, 입장) (시간, 퇴장)
# 시간 순으로 정렬. NlogN

# 정렬된 배열에서 내림차순으로 꺼내 구간합 계산
# dict -> 시간 : 개수 로 저장.
# 모기의 개수가 가장 많은 시간을 찾고, 구간합을 계산하여 max값을 토대로 정답 출력


import sys
import heapq

input = sys.stdin.readline
N = int(input().strip())
arr = []
res = {}
sum_ = 0
sig = False
max_size = -1
answer_s = 0
answer_e = 0

for i in range(N):
    s, e = map(int, input().split())
    heapq.heappush(arr, (s, 1))
    heapq.heappush(arr, (e, -1))

while len(arr) != 0:
    time, value = heapq.heappop(arr)
    sum_ += value
    if sum_ > max_size:
        max_size = sum_
        answer_s = time

    res[time] = sum_

for key in res.keys():
    if res[key] == max_size:
        sig = True
        continue

    if sig and res[key] < max_size:
        answer_e = key
        break

print(max_size)
print(answer_s, end=' ')
print(answer_e)
