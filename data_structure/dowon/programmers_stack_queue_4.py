from collections import deque

def solution(priorities, location):
    q = deque([(p, i) for i, p in enumerate(priorities)])
    answer = 0
    
    while q:
        current = q.popleft()
        if any(current[0] < item[0] for item in q):
            q.append(current)
        else:
            answer += 1
            if current[1] == location:
                return answer