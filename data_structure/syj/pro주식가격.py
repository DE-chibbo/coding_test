def solution(prices):
    answer = [0 for _ in range(len(prices))]
    
    stack = []
    for idx, p in enumerate(prices):

        while stack and stack[-1][1] > p:
            answer[stack[-1][0]] = idx - stack[-1][0]
            stack.pop()
        
        stack.append((idx,p))
        
    while stack:
        now = stack.pop()
        answer[now[0]] = (len(prices)-1) - now[0]
    
    return answer