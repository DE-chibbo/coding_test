'''
programmers basic test : passed
programmers final test : failed
- failed tests number : 6, 7

time complexity : O(n^logn)
- n : operations 수
'''

import heapq

def csh_double_prior_queue_solution(operations):
    heap = []
    for oper in operations:
        oper_list = oper.split(' ')
        if oper_list[0] == 'I':
            heapq.heappush(heap, int(oper_list[1]))
        if heap:
            if oper_list[0] == 'D' and oper_list[1] == '-1':
                heapq.heappop(heap)
            if oper_list[0] == 'D' and oper_list[1] == '1':
                heap.pop()
                
    return [heap[-1], heap[0]] if heap else [0, 0]