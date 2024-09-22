'''
programmers basic test : passed
programmers final test : passed
- all passed
'''

import math
def csh_carpet(brown, yellow):
        # a <= b
        # a*b = yellow
        # (a+2)*(b+2)-a*b = brown
        # ab+2a+2b+4-ab
        # [a+2, b+2]
        # brown / 2 -2 = a+b
        # b = yellow/a
        # brown / 2 - 2 = a + yellow/a
        # a(brown/2) - 2a = a^2 + yellow
        # a^2 - a(brown/2-2) +yellow = 0
        # ((brown/2-2) +- sqrt((brown/2-2)^2 - 4yellow)) / 2
        # 3+-1/2
        # 2 1
        
    return [((brown/2-2) + math.sqrt((brown/2-2)**2 - 4*yellow)) / 2 + 2, ((brown/2-2) - math.sqrt((brown/2-2)**2 - 4*yellow)) / 2 + 2]