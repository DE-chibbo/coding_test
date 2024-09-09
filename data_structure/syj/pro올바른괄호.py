def solution(s):
    answer = True
    
    stack = []
    for i in s:
        if i == '(':
            stack.append(i)
            continue
            
        if stack:
            stack.pop()
            continue
            
        return False
    
    if stack:
        return False

    return True