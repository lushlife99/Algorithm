def solution(n, stations, w):
    answer = 0
    end = 0
    
    for station in stations:
        
        start = max(0, station - w)
        
        if start - 1 > end:
            answer += (start - end - 1) // (2*w+1)
            if (start - end - 1) % (2*w+1) != 0:
                answer += 1
            
        end = min(station + w, n)
    
    if end < n:
        answer += (n - end) // (2*w+1)
        if (n - end) % (2*w+1) != 0:
                answer += 1
    return answer