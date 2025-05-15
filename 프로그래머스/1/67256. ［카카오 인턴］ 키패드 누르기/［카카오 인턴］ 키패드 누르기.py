def solution(numbers, hand):
    string = ''
    left = [3,0]
    right = [3,2]
    ls = [[1,4,7,'*'],[2,5,8,0],[3,6,9,'#']]

    for i in range(len(numbers)):
        if (numbers[i] == 1) or (numbers[i] == 4) or (numbers[i] == 7):
            left = [ls[0].index(numbers[i]), 0]
            string += 'L'
        elif (numbers[i] == 3) or (numbers[i] == 6) or (numbers[i] == 9):
            right = [ls[2].index(numbers[i]), 2]
            string += 'R'
        elif (numbers[i] == 2) or (numbers[i] == 5) or (numbers[i] == 8) or (numbers[i] == 0):
            if (abs(ls[1].index(numbers[i])-left[0]) + abs(1-left[1])) < (abs(ls[1].index(numbers[i])-right[0]) + abs(1-right[1])):
                left = [ls[1].index(numbers[i]), 1]
                string += 'L'
            elif (abs(ls[1].index(numbers[i])-left[0]) + abs(1-left[1])) > (abs(ls[1].index(numbers[i])-right[0]) + abs(1-right[1])):
                right = [ls[1].index(numbers[i]), 1]
                string += 'R'
            else:
                if hand == 'right':
                    right = [ls[1].index(numbers[i]), 1]
                else:
                    left = [ls[1].index(numbers[i]),1]
                string += hand[0].upper()
    return string