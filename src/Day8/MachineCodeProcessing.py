from pathlib import Path
import re
from copy import deepcopy

p = Path('.').absolute()
resources = p.parents[1].joinpath('Resources')
file = open(resources.joinpath('Day8.txt'), "r")

cleanList = [[]]
for l in file:
    cleanList.append([l.replace('\n', ''), 0])
cleanList.pop(0)

file.close()


def test(operation, target):
    dirtyList = deepcopy(cleanList)
    acc = 0
    pos = 0
    count = 0
    changed = False
    while True:
        if pos > len(dirtyList) - 1 or dirtyList[pos][1] > 0:
            break
        dirtyList[pos][1] += 1

        split = dirtyList[pos][0].split()
        if count == target and re.match('^' + operation, dirtyList[pos][0]) and changed == False:
            changed = True
            if operation == 'nop':
                dirtyList[pos][0] = 'jmp ' + split[1]
                print("Set '" + "nop " + split[1] + "' to '" + dirtyList[pos][0] + "'")
            elif operation == 'jmp':
                dirtyList[pos][0] = 'nop ' + split[1]
                print("Set '" + "jmp " + split[1] + "' to '" + dirtyList[pos][0] + "'")

        if re.match('^acc', dirtyList[pos][0]):
            acc += int(split[1])
            pos += 1
        elif re.match('^jmp', dirtyList[pos][0]):
            if operation == 'jmp' and changed == False:
                count += 1
            pos += int(split[1])
        elif re.match('^nop', dirtyList[pos][0]):
            if operation == 'nop' and changed == False:
                count += 1
            pos += 1

    return [acc, pos]


nopCount = 0
jmpCount = 0
for line in cleanList:
    if line[0].__contains__('nop'):
        nopCount += 1
    elif line[0].__contains__('jmp'):
        jmpCount += 1

for i in range(0, nopCount):
    print("Testing nop" + str(i))
    result = test('nop', i)
    print(result)
    print()
    if result[1] == 624:
        print(result[0])
        break

for i in range(0, jmpCount):
    print("Testing jmp" + str(i))
    result = test('jmp', i)
    print(result)
    print()
    if result[1] == 624:
        print(result[0])
        break
