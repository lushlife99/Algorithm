import sys
input = sys.stdin.readline

N, K = map(int, input().strip().split())
X = list(map(int, input().strip().split()))
my_dict = {}
Q = int(input())
arr = [0 for _ in range(N)]

for x in X:
    my_dict[x] = my_dict.get(x, 0) + 1

for key in my_dict.keys():
    value = my_dict.get(key)
    i = 0
    while key * i < N:
        arr[key * i] += value
        i += 1

for i in range(1, N):
    arr[i] += arr[i-1]

for _ in range(Q):
    s, e = map(int, input().split())
    if s == 0 :
        print(arr[e])
    else : print(arr[e] - arr[s-1])