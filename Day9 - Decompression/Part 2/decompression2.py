INPUT = "input.txt"  

def parse_marker(marker_string):
    split = marker_string.split('x')
    return int(split[0].replace('(', '')), int(split[1].replace(')', ''))

def decompress(line):
    decompressed_length = 0
    i = 0
    while i < len(line):
        if line[i] == '(':
            marker_end = line[i:].find(')') + i + 1
            length, iterations = parse_marker(line[i:marker_end])
            decompressed_length += decompress(line[marker_end:marker_end+length]) * iterations
            i = marker_end + length
        else:
            decompressed_length += 1
            i += 1
    return decompressed_length

def main():
    with open(INPUT) as infile:
        for line in infile:
            decompressed_length = decompress(line.strip())
            print decompressed_length

if __name__ == "__main__":
    main()