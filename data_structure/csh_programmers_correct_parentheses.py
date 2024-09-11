'''
programmers basic test : passed
programmers final test : passed

time complexity : O(n)
'''


def solution(s):
    stack = [0]
    if s[-1] == '(' or s[0] == ')':
        return False
    if len(s) % 2 != 0:
        return False
    for i in range(len(s)):
        b = s[i]
        if b != stack[-1] and stack[-1] != 0:
            stack.pop(-1)
            continue
        stack.append(b)
        if stack[1] == ')':
            return False
    if len(stack) > 1:
        return False
        
    return True