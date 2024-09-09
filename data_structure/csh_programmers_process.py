'''
programmers basic test : passed
programmers final test : passed

time complexity : O(n log n)
'''


import heapq
from collections import deque

def solution(priorities, location):
    
    indices = [i for i in range(len(priorities))]
    
    run_queue = []
    run_q_indicies = []
    dq = deque(priorities)
    dq_indicies = deque(indices)
    
    max_heap = [-1 * x for x in priorities]
    heapq.heapify(max_heap)
    
    max_prior = -heapq.heappop(max_heap)
    while len(run_queue) != len(priorities):
        prior = dq.popleft()
        i = dq_indicies.popleft()
        if prior == max_prior:
            run_queue.append(prior)
            run_q_indicies.append(i)
            
            if i == location:
                return len(run_queue)
            
            max_prior = -heapq.heappop(max_heap)
        else:
            dq.append(prior)
            dq_indicies.append(i)