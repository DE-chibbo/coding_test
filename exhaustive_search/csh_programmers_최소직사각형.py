'''
programmers basic test : passed
programmers final test : passed
- all passed
'''

def csh_minimum_rectangle(sizes):
    line1 = 0
    line2 = 0
    for size in sizes:
        long_line = max(size)
        short_line = min(size)
        if line1 < long_line:
            line1 = long_line
        if line2 < short_line:
            line2 = short_line
    
    return line1 * line2