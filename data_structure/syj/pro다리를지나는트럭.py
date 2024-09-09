from collections import deque

def solution(bridge_length, weight, truck_weights):
    n_time, n_sum = 0, 0
    q = deque([0 for _ in range(bridge_length)])
    
    for t in truck_weights:
        while True:
            n_sum -= q.popleft()
            if n_sum + t > weight:
                q.append(0)
                n_time += 1
                continue
            
            n_sum += t
            q.append(t)
            n_time += 1
            break
    
    while q:
        n_time += 1
        q.popleft()
        
    return n_time