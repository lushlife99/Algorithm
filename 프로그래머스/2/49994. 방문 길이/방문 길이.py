def solution(dirs):
    answer_set = set()
    instruction = {"U" : (1, 0), "D" : (-1, 0), "R" : (0, 1), "L" : (0, -1)}
    x, y = 5, 5  # 시작점

    for direction in dirs:
        dx, dy = instruction[direction]
        cx, cy = x + dx, y + dy

        if 0 <= cx <= 10 and 0 <= cy <= 10:
            path = frozenset([(x, y), (cx, cy)])
            answer_set.add(path)
            x, y = cx, cy

    return len(answer_set)
