import math

minkyum_number = str(input())


def get_max(number):
    cnt = 0
    res = []
    for i in range(len(number)):
        if number[i] == "K":
            res.append(str(5*(10**cnt)))
            cnt = 0
        else:
            cnt += 1

    if cnt > 0:
        res.append(str(1) * cnt)

    return res


def get_min(number):
    cnt = 0
    res = []

    for i in range(len(number)):
        if number[i] == "K":
            if cnt > 0:
                res.append(str(10**cnt + 5))
            else:
                res.append(str(5))
            cnt = 0
        elif number[i] == "M":
            cnt += 1

    if cnt > 0:
        res.append(str(10**(cnt-1)))

    return res


print("".join(get_max(minkyum_number)))
print("".join(get_min(minkyum_number)))
