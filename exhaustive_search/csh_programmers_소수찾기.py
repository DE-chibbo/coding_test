'''
programmers basic test : passed
programmers final test : passed
- all passed
'''

from itertools import permutations

def csh_find_prime_numbers(numbers):
    answer = 0
    
    subset = set()
    max_sub = 0
    for i in range(1, len(numbers) + 1):
        for comb in permutations(numbers, i):
            sub_str = ''.join(comb)
            sub_num = int(sub_str)
            if sub_num > max_sub:
                max_sub = sub_num
            subset.add(sub_num)
    
    is_prime = [True for _ in range(max_sub + 1)]
    is_prime[0:2] = [False, False]
    p = 2
    while p * p < max_sub:
        if is_prime[p]:
            for i in range(p * p, max_sub + 1, p):
                is_prime[i] = False
        p += 1
    
    for num in subset:
        if is_prime[num]:
            answer += 1
    return answer