# dict에 id, name 저장
def solution(record):
    answer = []
    result = []
    user_dict = {}
    ENTER_MESSAGE = "님이 들어왔습니다."
    LEAVE_MESSAGE = "님이 나갔습니다."
    
    for r in record:
        command = r.split()
        if command[0] == "Enter":
            user_dict[command[1]] = command[2]
            answer.append(command[1] + " Enter")
        elif command[0] == "Leave":
            answer.append(command[1] + " Leave")
        else:
            user_dict[command[1]] = command[2]
    
    for a in answer:
        command = a.split()
        if command[1] == "Enter":
            result.append(user_dict[command[0]] + ENTER_MESSAGE)
        else:
            result.append(user_dict[command[0]] + LEAVE_MESSAGE)
            
            
    return result