def solution(progresses, speeds):
    require = []
    answer = []
    for i in range(len(speeds)):
        left_date = ceil((100-progresses[i]) / speeds[i])
        if require != [] and require[0] < left_date:
            answer.append(len(require))
            require = []
        require.append(left_date)
    answer.append(len(require))
    return answer

def ceil(n : float):
    if n // 1 == n:
        return int(n)
    else:
        return int(n//1 + 1)