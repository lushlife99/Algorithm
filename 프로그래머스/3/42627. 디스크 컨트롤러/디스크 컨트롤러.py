import heapq

def solution(jobs): 
    q = [] # 소요시간, 요청 시각, 번호
    request_times = {}
    for i in range(len(jobs)):
        req_time, job_time = jobs[i]
        if req_time in request_times:
            request_times[req_time].append((job_time, i))
        else: request_times[req_time] = [(job_time, i)]
    
    
    time = 0
    cnt = 0
    N = len(jobs)
    end_time = 0
    answer = []
    
    while cnt < N:
        
        if time in request_times:
            for job_time, index in request_times[time]:
                heapq.heappush(q, (job_time, time, index))
        
        if q and end_time <= time:
            j, t, i = heapq.heappop(q)
            end_time = time + j
            answer.append(end_time - t)
            cnt += 1
    
        time += 1
    
    return sum(answer) // N