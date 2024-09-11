'''
programmers basic test : passed
programmers final test : passed
- all passed

time complexity : O(n^2)
'''

import heapq

def csh_stock_price_solution(prices):
    head_prices = [prices[0]]
    hold_counts = [1]
    
    mapped_prices_idx_reverse = [(-price, i) for i, price in enumerate(head_prices)]
    heapq.heapify(mapped_prices_idx_reverse)
    max_heap_mpir = mapped_prices_idx_reverse
    
    body_prices = prices[1:]
    
    for i, price in enumerate(body_prices):
        hold_counts.append(0)
        heapq.heappush(max_heap_mpir, (-price, len(head_prices) + i))
        
        while max_heap_mpir and price < -max_heap_mpir[0][0]:
            heapq.heappop(max_heap_mpir)
                
        if i != (len(body_prices) - 1):
            for node in max_heap_mpir:
                hold_counts[node[1]] += 1
                
    return hold_counts
        
    