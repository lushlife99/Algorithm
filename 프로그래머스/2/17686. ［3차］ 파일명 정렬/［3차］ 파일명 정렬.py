def get_file_metadata(file):
    
    current_idx = 0
    head_end, number_end = 0, 0
    while current_idx < len(file):
        if "0" <= file[current_idx+1] <= "9":
            head_end = current_idx
            break
        current_idx += 1
    
    while current_idx < len(file):
        if current_idx+1 == len(file) or not ("0" <= file[current_idx+1] <= "9"):
            number_end = current_idx
            break
        current_idx += 1
            
    metadata = [file[:head_end+1], file[head_end+1:number_end+1], file[number_end+1:]]
    return metadata

def solution(files):
    answer = []
    metadata = [[] for _ in range(len(files))]
    for i in range(len(files)):
        file = files[i]
        metadata[i] = get_file_metadata(file)

    metadata.sort(key=lambda x: (x[0].lower(), int(x[1])))
    
    for i in range(len(metadata)):
        answer.append("".join(metadata[i]))
    
    return answer