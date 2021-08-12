from pathlib import Path

p = Path('.').absolute()
resources = p.parents[2].joinpath('Resources')
file = open(resources.joinpath('Python.Day9.txt'), "r")
cleanList = []
for l in file:
    cleanList.append(l.replace('\n', ''))
cleanList.pop(0)

for i in range(25, 26):
    dataset = []
    for j in range(0,25):
        dataset.append(cleanList[i - j])
    print(dataset)
    print(cleanList[i])