'''
programmers basic test : passed
programmers final test : passed
- all passed
'''

import heapq

def csh_double_priority_queue(operations):
    heap = []
    for oper in operations:
        oper_list = oper.split(' ')
        if oper_list[0] == 'I':
            heapq.heappush(heap, int(oper_list[1]))
        if heap:
            if oper_list[0] == 'D' and oper_list[1] == '-1':
                heapq.heappop(heap)
            if oper_list[0] == 'D' and oper_list[1] == '1':
                heap.remove(max(heap))
                
    return [max(heap), heapq.heappop(heap)] if heap else [0, 0]