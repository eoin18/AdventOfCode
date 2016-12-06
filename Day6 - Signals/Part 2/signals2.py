from string import ascii_lowercase

INPUT = "input.txt"

def get_most_common_character(characters):
    characters_to_occurences = {}
    for c in ascii_lowercase:
        characters_to_occurences[c] = characters.count(c)
    reduced = {k:v for k, v in characters_to_occurences.items() if v}
    return sorted(reduced.items(), key=lambda x: (x[1], x[0]))[0][0]

def main():
    error_corrected = ""
    lines = []
    with open(INPUT) as f:
        for line in f:
            lines.append(line)
    #I wish I didn't have to loop twice...
    for i in range(0, 8):
        characters = [line[i] for line in lines]
        error_corrected += get_most_common_character(characters)
    print error_corrected

if __name__ == "__main__":
    main()