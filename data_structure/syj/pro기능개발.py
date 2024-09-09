from collections import deque
import math

def solution(progresses, speeds):
    answer = []
    
    q = deque()
    for p,s in zip(progresses, speeds):
        q.append(math.ceil((100-p)/s))
    
    while q:
        now = q.popleft()
        cnt = 1
        while q and now >= q[0]:
            q.popleft()
            cnt += 1
        answer.append(cnt)

    return answer