from itertools import combinations

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]
numbers = list(range(0, N))
res = 1000000000

all_combinations = []
for n in range(1, 11):
    all_combinations.extend(list(combinations(numbers, n)))

for comb in all_combinations:
    sour = 1
    bitter = 0
    for gradient in comb:
        sour = sour * arr[gradient][0]
        bitter = bitter + arr[gradient][1]

    res = min(res, abs(sour - bitter))

print(res)

