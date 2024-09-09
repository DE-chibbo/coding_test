'''
programmers basic test : passed
programmers final test : passed

time complexity : O(n)
'''

def solution(arr):
    answer = []

    prev = None
    for n in arr:
        if prev != n:
            answer.append(n)
        prev = n
    return answer