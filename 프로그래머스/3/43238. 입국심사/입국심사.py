def solution(n, times):
    start = 1
    answer = 0
    end = n * min(times)
    
    while start <= end:
        mid = (end + start) // 2
        cnt = 0
        for time in times:
            cnt += mid // time
        if cnt >= n:
            answer = mid
            end = mid - 1
        else: 
            start = mid + 1
        
    return answer