import re

INPUT = "input.txt"

def contains_abba(sequence):
    range_bound = len(sequence)-3
    for i in range(0, range_bound):
        if sequence[i] == sequence[i+3] and sequence[i+1] == sequence[i+2] and sequence[i] != sequence[i+1]:
            return True
    return False

def hypernet_contains_abba(line):
    hypernet_sequences = re.findall("(\[[a-z]*\])", line)
    for group in hypernet_sequences:
        group = group.replace(']', '').replace('[', '')
        if contains_abba(group):
            return True
    return False

def supernet_contains_abba(line):
    supernet_sequences = []
    supernet_sequences.append(line.split('[')[0])
    supernet_sequences.append(line.split(']')[-1])
    supernet_sequences.extend(re.findall("(\][a-z]*\[)", line))
    for supernet_sequence in supernet_sequences:
        supernet_sequence = supernet_sequence.replace(']', '').replace('[', '').replace('\n', '')
        if contains_abba(supernet_sequence):
            return True
    return False

def supports_tls(line):
    supernet_abba = supernet_contains_abba(line)
    hypernet_abba = hypernet_contains_abba(line)
    return supernet_abba and not hypernet_abba

def main():
    abba_addresses = 0
    with open(INPUT) as fopen:
        for line in fopen:
            if supports_tls(line):
                abba_addresses += 1
    print abba_addresses

if __name__ == "__main__":
    main()