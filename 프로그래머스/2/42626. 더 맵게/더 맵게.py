import heapq

def solution(scoville, K):
    answer = 0
    q = []
    
    for i in range(len(scoville)):
        heapq.heappush(q, scoville[i])
    
    while len(q) > 1:
        min_scoville = heapq.heappop(q)
        
        if min_scoville >= K:
            return answer
        
        second_min_scoville = heapq.heappop(q)
        heapq.heappush(q, min_scoville + second_min_scoville * 2)
        answer += 1
        
    if heapq.heappop(q) < K:
        return -1
    
    return answer