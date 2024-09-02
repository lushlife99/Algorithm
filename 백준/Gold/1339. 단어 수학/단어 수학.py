N = int(input())
arr = []
num_arr = []
results = []
cnts = [[0 for _ in range(10)] for _ in range(26)]
for _ in range(N):
    s = str(input())
    arr.append(s)
    for i in range(len(s) - 1, -1, -1):
        ch = s[i]
        cnts[ord(ch) - ord('A')][len(s) - i - 1] += 1


for j in range(len(cnts)):
    cntArr = cnts[j]
    result = 0
    for i in range(10):
        result += cntArr[i] * pow(10, i)

    if result != 0:
        results.append((result, j))

results = sorted(results, reverse=True)

for i in range(N):
    s = arr[i]
    newS = ""
    for ch in s:
        for j in range(len(results)):
            if results[j][1] == ord(ch) - ord('A'):
                newS += str(9 - j)
                break

        arr[i] = newS

res = 0

for i in range(N):
    res += int(arr[i])

print(res)

