from collections import Counter
import sys
sys.setrecursionlimit(100000)

def divide_quadrants(arr):
    middle = len(arr) // 2
    top_left = [row[:middle] for row in arr[:middle]]
    top_right = [row[middle:] for row in arr[:middle]]
    bottom_left = [row[:middle] for row in arr[middle:]]
    bottom_right = [row[middle:] for row in arr[middle:]]
    return top_left, top_right, bottom_left, bottom_right

def dfs(arr):
    if len(arr) == 1:
        if arr[0][0] == 0:
            return [1, 0]
        else:
            return [0, 1]
    
    signal = True
    for i in range(len(arr)):
        counter = Counter(arr[i])
        if counter[0] != len(arr):
            signal = False
            break
    
    if signal:
        return [1, 0]
    
    else:
        signal = True
        for i in range(len(arr)):
            counter = Counter(arr[i])
            if counter[1] != len(arr):
                signal = False
                break
    
    if signal:
        return [0, 1]
    
    # 0과 1 둘다 해당되지 않는 경우 분할
    top_left, top_right, bottom_left, bottom_right = divide_quadrants(arr)
    cnt = [0, 0]
    res = dfs(top_left)
    cnt[0] += res[0]
    cnt[1] += res[1]
    res = dfs(top_right)
    cnt[0] += res[0]
    cnt[1] += res[1]
    res = dfs(bottom_left)
    cnt[0] += res[0]
    cnt[1] += res[1]
    res = dfs(bottom_right)
    cnt[0] += res[0]
    cnt[1] += res[1]
    return cnt
    

def solution(arr):
    answer = dfs(arr)
    return answer