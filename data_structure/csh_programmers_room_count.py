'''
programmers basic test : passed
programmers final test : failed
- all failed

time complexity : O(n)
- n : arrows 수
'''

movement = {
    0: (0, 1),
    1: (1, 1),
    2: (1, 0),
    3: (1, -1),
    4: (0, -1),
    5: (-1, -1),
    6: (-1, 0),
    7: (-1, 1),
}

def csh_room_count_solution(arrows):
    location = (0, 0)
    location_log = {
        location : set({})
    }
    result = 0
    
    for arrow in arrows:
        prev_location = location
        location = tuple(map(sum, zip(location, movement[arrow])))
        
        if location in location_log and prev_location not in location_log[location]:
            result += 1
        
        location_log[location] = location_log.get(location, set({}))
        location_log[location].add(prev_location)
    
    return result