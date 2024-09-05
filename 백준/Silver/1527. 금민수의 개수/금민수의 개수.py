left, right = map(int, input().split())
res = 0


def find(depth, l, r, num):

    cnt = 0
    if depth >= 10:
        return 0

    if num > r:
        return 0

    if num >= l:
        cnt += 1

    cnt += find(depth + 1, l, r, num * 10 + 4)
    cnt += find(depth + 1, l, r, num * 10 + 7)
    return cnt


print(find(0, left, right, 0))
