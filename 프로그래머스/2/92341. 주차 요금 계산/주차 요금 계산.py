# dict에 저장
# 현재 주차되어 있는 자동차의 현황 map, 총 시간 계산 map 총 2개에 저장
from collections import defaultdict

def solution(fees, records):
    answer = []
    current_parking = defaultdict(int)
    total_parking_time = defaultdict(int)
    basic_time, basic_fee, unit_time, unit_fee = fees
    
    for record in records:
        time, car_id, s = record.split()
        hour, minute = time.split(":")
        current_minute = int(hour) * 60 + int(minute)
        if s == "IN":
            current_parking[car_id] = current_minute
        else:
            parking_time = current_minute - current_parking[car_id]
            total_parking_time[car_id] += parking_time
            del current_parking[car_id]

    time = 23 * 60 + 59        
    for car_id in current_parking.keys():
        total_parking_time[car_id] += time - current_parking[car_id]
    
    
    for car_id in sorted(total_parking_time.keys()):
        if basic_time >= total_parking_time[car_id]:
            answer.append(basic_fee)
        else:
            additional_time = (total_parking_time[car_id] - basic_time) // unit_time
            if (total_parking_time[car_id] - basic_time) % unit_time != 0:
                additional_time += 1
            
            additional_fee = additional_time * unit_fee
            answer.append(basic_fee + additional_fee)
    
    
    return answer