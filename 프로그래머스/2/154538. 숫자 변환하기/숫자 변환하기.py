from collections import deque

def solution(x, y, n):
    answer = []
    count = 0
    queue = deque()
    queue.append([y, count])

    while queue:
        now = queue.popleft()
        count = now[1] + 1
        if now[0] == x:
            answer = now
            break
        else:
            if now[0] > x:
                if now[0] % 3 == 0:
                    queue.append([now[0]//3, count])
                if now[0] % 2 == 0:
                    queue.append([now[0]//2, count])
                queue.append([now[0]-n, count])

    if not answer:
        return -1
    else:
        return answer[1]