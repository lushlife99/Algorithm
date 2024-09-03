def check(existN1, existN2, num):
    if num == existN1 or num == existN2:
        return True

    return False

N = int(input())

arr = list(map(int, input().split()))

num1 = -1
num2 = -1
cnt = 0
result = 0
for i in range(N):
    if num1 == -1:
        num1 = arr[i]
        cnt += 1
    elif num2 == -1 and num1 != arr[i]:
        num2 = arr[i]
        cnt += 1

    else:
        if check(num1, num2, arr[i]):
            cnt += 1

        else :
            num1 = arr[i]
            num2 = -1
            cnt = 0
            for j in range(i, 0, -1):
                if arr[j] != num1:
                    if num2 == -1:
                        num2 = arr[j]
                        cnt += 1
                    elif num2 != arr[j]:
                        break

                    else : cnt += 1
                else : cnt += 1

        if result < cnt:
            result = cnt

print(max(result, cnt))