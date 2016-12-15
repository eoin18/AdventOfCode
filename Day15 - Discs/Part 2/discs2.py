INPUT="input.txt"

DISCS = {}
DISCS_SIZES = {}

def seedDisc(line):
    disc_number = int(line.split()[1][1:])
    DISCS[disc_number] = {}
    positions = int(line.split()[3])
    DISCS_SIZES[disc_number] = positions
    initial_position = int(line.split()[11][:-1])
    for i in range(0, positions):
        position_at_time = (i + initial_position) % positions
        DISCS[disc_number][i] = position_at_time

def main():
    with open(INPUT) as infile:
        for line in infile:
            seedDisc(line)
    finished = False
    startTime = 0
    while not finished:
        tempFinished = True
        disc_time = startTime + 1
        for i in range(1, len(DISCS) + 1):
            tempFinished &= DISCS[i][disc_time % DISCS_SIZES[i]] == 0
            disc_time += 1
        if tempFinished:
            finished = True
        else:
            startTime += 1
    print startTime

if __name__ == "__main__":
    main()