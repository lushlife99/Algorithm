import math
from collections import defaultdict

def solution(survey, choices):
    
    survey_dict = defaultdict(int)
    
    for i in range(len(survey)):
        score = choices[i] - 4
        if score < 0:
            survey_dict[survey[i][0]] -= score
        else:
            survey_dict[survey[i][1]] += score
            
    answer = ''
    
    if survey_dict["T"] > survey_dict["R"]:
        answer += "T"
    else:
        answer += "R"
    if survey_dict["F"] > survey_dict["C"]:
        answer += "F"
    else:
        answer += "C"
    if survey_dict["M"] > survey_dict["J"]:
        answer += "M"
    else:
        answer += "J"
    if survey_dict["N"] > survey_dict["A"]:
        answer += "N"
    else:
        answer += "A"
    return answer