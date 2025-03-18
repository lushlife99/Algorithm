import heapq

def solution(n, t, m, timetable):
    answer = ''
    arrive_times = {}
    q = []
    
    for time in timetable:
        hours = int(time.split(":")[0])
        minutes = int(time.split(":")[1])
        
        if hours * 60 + minutes in arrive_times:
            arrive_times[hours * 60 + minutes] += 1
        else: arrive_times[hours * 60 + minutes] = 1
    
    current_minute = 0
    last_arrived_minute = 9 * 60 + t * (n-1)
    last_ride_minute = 9 * 60
        
    while current_minute <= last_arrived_minute:
        
        if current_minute in arrive_times:
            for _ in range(arrive_times[current_minute]):
                heapq.heappush(q, current_minute)
        
        if 9 * 60 <= current_minute and (current_minute - 9 * 60) % t == 0:
            for i in range(m):
                if q:
                    last_ride_minute = heapq.heappop(q) - 1
                    
                else: 
                    last_ride_minute = current_minute
                    break
        current_minute += 1
    
    hour = str(last_ride_minute // 60)
    minute = str(last_ride_minute % 60)
    
    if len(hour) == 1:
        hour = "0" + hour
    
    if len(minute) == 1:
        minute = "0" + minute
    
    answer = hour + ":" + minute
    return answer