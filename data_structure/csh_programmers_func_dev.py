'''
programmers basic test : passed
programmers final test : passed

time complexity : O(n)
'''


import math
def solution(progresses, speeds):
    answer = []

    max_r = 0
    for i, p in enumerate(progresses):
        r = math.ceil((100 - p) / speeds[i])
        if max_r < r:
            max_r = r
            answer.append(1)
        else:
            answer[-1] += 1
    
    return answer