from collections import deque

def solution(cacheSize, cities):
    answer = 0
    cache_queue = deque()
    
    if cacheSize == 0:
        return len(cities) * 5
    
    for city in cities:
        city = city.upper()
        if city in cache_queue:
            cache_queue.remove(city)
            cache_queue.append(city)
            answer += 1
        else:
            if len(cache_queue) >= cacheSize:
                cache_queue.popleft()
            cache_queue.append(city)
            answer += 5
            
    return answer