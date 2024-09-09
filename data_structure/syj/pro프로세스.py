from collections import deque

def solution(priorities, location):
    answer = 0
    q = deque([(p,idx) for idx,p in enumerate(priorities)])
    
    while q:
        now = q.popleft()
        if any(now[0] < a[0] for a in q):
            q.append(now)
            continue
        
        ## any() 안쓰고
        # flag = False
        # for a,b in q:
        #     if now[0] < a:
        #         flag = True
        #         break
                
        # if flag:
        #     q.append(now)
        #     continue

        answer += 1
        if now[1] == location:
            return answer