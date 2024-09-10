# 1 <= N <= 10^5
# 1 <= Q <= 10^5
# O(N^2) = 10^10
# 시간제한 : 0.5 -> 5 * 10^7
# 브루트포스로 풀면 시간초과.

N = int(input()) # 악보의 개수
akbo = [0] + list(map(int, input().split())) # 악보의 난이도 리스트
sum_list = [0] * (N + 1) # sum_list[i] = 0부터 i+1번까지 악보를 연주할 때 실수 하는 총 악보 수
Q = int(input()) # 질문의 개수

def solve():
    for i in range(1, N):
        sum_list[i] = sum_list[i-1]
        if akbo[i] > akbo[i+1]:
            sum_list[i] += 1

    sum_list[N] = sum_list[N-1]


solve()
for _ in range(Q):
    a, b = map(int, input().split())
    print(sum_list[b-1] - sum_list[a-1])