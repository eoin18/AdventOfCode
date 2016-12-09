INPUT = "input.txt"  

def parse_marker(marker_string):
    split = marker_string.split('x')
    return int(split[0].replace('(','')), int(split[1].replace(')',''))

def get_decompressed_string(length, iterations, rest_of_line):
    decompressed_string = ""
    for i in range(0, iterations):
        decompressed_string += rest_of_line[:length]
    return decompressed_string

def decompress(line):
    decompressed = ""
    i = 0
    while i < len(line):
        if line[i] == '(':
            marker_end = line[i:].find(')') + i + 1
            length, iterations = parse_marker(line[i:marker_end])
            print length
            print iterations
            decompressed += get_decompressed_string(length, iterations, line[marker_end:])
            i = marker_end + length
        else:
            decompressed += line[i]
            i += 1
    return decompressed

def main():
    with open(INPUT) as infile:
        for line in infile:
            decompressed = decompress(line.strip())
            print decompressed
            print len(decompressed)

if __name__ == "__main__":
    main()