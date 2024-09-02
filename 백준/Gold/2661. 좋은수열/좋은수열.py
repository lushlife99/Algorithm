n = int(input())
arr = ["1", "2", "3"]


def dfs(s):

    if len(s) == n:
        print(s)
        exit()

    for v in arr:
        if check(s, v):
            dfs(s + v)

def check(s, ch):
    checkStr = s + ch
    midIdx = len(checkStr)
    for startIdx in range(len(s) - 1, -1, -2):
        midIdx -= 1
        if startIdx != midIdx:
            if checkStr[startIdx:midIdx] == checkStr[midIdx:len(checkStr)]:
                return False


    return True

dfs("")