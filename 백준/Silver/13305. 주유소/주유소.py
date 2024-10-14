N = int(input())
edge = list(map(int, input().split())) + [0]
money = list(map(int, input().split()))
res = []
q = []
current, result = 0, 0
while current < N:

    next = current + 1
    charge = edge[current]
    while next < N-1:
        if money[current] > money[next]:
            break

        else:
            charge += edge[next]
            next += 1

    res.append((current, charge))
    current = next

while res:
    node, dist = res.pop()
    result += money[node] * dist

print(result)
