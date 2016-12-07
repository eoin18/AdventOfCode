import re

INPUT = "input.txt"

def find_corresponding_bab(aba, hypernet_groups):
    for group in hypernet_groups:
        range_bound = len(group)-2
        for i in range(0, range_bound):
            if group[i] == aba[1] and group[i+1] == aba[0] and group[i+2] == aba[1]:
                return True
    return False

def find_aba(sequence, index):
    return sequence[index] == sequence[index+2] and sequence[index] != sequence[index+1]

def sequence_supports_ssl(sequence, hypernet_groups):
    range_bound = len(sequence)-2
    for i in range(0, range_bound):
        if find_aba(sequence, i):
            aba = sequence[i:i+2]
            if find_corresponding_bab(aba, hypernet_groups):
                return True
    return False

def get_supernet_sequences(line):
    supernet_sequences = []
    supernet_sequences.append(line.split('[')[0])
    supernet_sequences.append(line.split(']')[-1])
    supernet_sequences.extend(re.findall("(\][a-z]*\[)", line))
    return [s.replace(']', '').replace('[', '').replace('\n', '') for s in supernet_sequences]

def get_hypernet_sequences(line):
    hypernet_sequences = re.findall("(\[[a-z]*\])", line)
    return [s.replace('[', '').replace(']', '') for s in hypernet_sequences]

def supports_ssl(line):
    supernet_sequences = get_supernet_sequences(line)
    hypernet_sequences = get_hypernet_sequences(line)
    for supernet_sequence in supernet_sequences:
        if sequence_supports_ssl(supernet_sequence, hypernet_sequences):
            return True
    return False

def main():
    abba_addresses = 0
    with open(INPUT) as fopen:
        for line in fopen:
            if supports_ssl(line):
                abba_addresses += 1
    print abba_addresses

if __name__ == "__main__":
    main()