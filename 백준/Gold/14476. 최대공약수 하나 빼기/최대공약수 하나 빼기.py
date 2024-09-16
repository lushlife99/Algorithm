import sys
input = sys.stdin.readline

def gcd(a, b):
    while b > 0:
        a, b = b, a % b

    return a

N = int(input().strip())
arr = list(map(int, input().strip().split()))
left_gcds = arr.copy()
right_gcds = arr.copy()
max_gcd = -sys.maxsize
k_idx = -1

for i in range(1, N):
    left_gcds[i] = gcd(left_gcds[i-1], arr[i])
    right_gcds[N-i-1] = gcd(right_gcds[N-i], arr[N-i-1])

for i in range(N):
    if i == 0:
        gcd_ = right_gcds[i+1]
    elif i == N-1:
        gcd_ = left_gcds[i-1]
    else : gcd_ = gcd(left_gcds[i-1], right_gcds[i+1])

    if gcd_ > max_gcd and arr[i] % gcd_ != 0:
        max_gcd = gcd_
        k_idx = i

if k_idx > -1:
    print(max_gcd, arr[k_idx])
else : print(-1)