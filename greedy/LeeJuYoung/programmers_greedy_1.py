def solution(n, lost, reserve):

    lost_set = set(lost)
    reserve_set = set(reserve)

    overlap = lost_set & reserve_set
    lost_real = lost_set - overlap
    reserve_real = reserve_set - overlap

    answer = n - len(lost_real)
    
    for student in sorted(lost_real):
        if student - 1 in reserve_real:
            reserve_real.remove(student - 1)
            answer += 1
        elif student + 1 in reserve_real:
            reserve_real.remove(student + 1)
            answer += 1
    
    return answer