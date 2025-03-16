from collections import deque

def solution(stones, k):
    dq = deque()
    result = float('inf')

    for i in range(len(stones)):
        if dq and dq[0] < i - k + 1:
            dq.popleft()

        while dq and stones[dq[-1]] <= stones[i]:
            dq.pop()
        dq.append(i)

        if i >= k - 1:
            result = min(result, stones[dq[0]])

    return result
