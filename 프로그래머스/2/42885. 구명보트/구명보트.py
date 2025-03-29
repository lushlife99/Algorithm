def solution(people, limit):
    answer = len(people)
    people.sort()
    right = len(people) - 1
    left = 0
    
    while left < right:
        if people[left] + people[right] <= limit:
            answer -= 1
            left += 1
            right -= 1
        elif people[left] + people[right] > limit:
            right -= 1
    
    return answer