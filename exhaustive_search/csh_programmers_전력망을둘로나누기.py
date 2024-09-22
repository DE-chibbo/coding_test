'''
programmers basic test : passed
programmers final test : passed
- all passed
'''

def csh_splitting_power_grid_two(n, wires):
    answer = -1
    
    # 끊는 wire 번호에 따른 송전탑 개수 차이 [index:wire번호,데이터:[그룹1송전탑개수, 그룹2송전탑개수]]]
    group_cnts = [[1, 1] for _ in range(len(wires))]
    for cut_wire_idx, cut_wire in enumerate(wires):
        
        # 끊긴 트리 구성 [index:송전탑번호,데이터:[연결된송전탑]]
        towers = [[] for _ in range(n)]
        for wire in wires:
            if wire != cut_wire:
                towers[wire[0] - 1].append(wire[1] - 1)
                towers[wire[1] - 1].append(wire[0] - 1)

        isCounted_flags = [0] * n

        for target_group in range(2):
            for tower, isCounted in enumerate(isCounted_flags):
                if isCounted == 0:
                    q = [tower]
                    
            while q:
                current_tower = q.pop(0)

                isCounted_flags[current_tower] += 1
                for connected_tower in towers[current_tower]:
                    if isCounted_flags[connected_tower] == 0:
                        isCounted_flags[connected_tower] = 1
                        group_cnts[cut_wire_idx][target_group] += 1
                        q.append(connected_tower)
                                
    gaps = [abs(group1 - group2) for (group1, group2) in group_cnts]
    answer = min(gaps)
    
    return answer