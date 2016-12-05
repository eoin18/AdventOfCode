INPUT = "input.txt"

def is_valid_triangle(one_side, other_sides):
    sum_of_other_sides = 0
    for side in other_sides:
        sum_of_other_sides += side
    return one_side < sum_of_other_sides

def check_is_valid(tokens):
    valid = True
    if len(tokens) == 3:
        print tokens
        numeric_tokens = map(int, tokens)
        for numeric_token in numeric_tokens: 
            clone_tokens = list(numeric_tokens)
            clone_tokens.remove(numeric_token)
            if not is_valid_triangle(numeric_token, clone_tokens):
                valid = False
                break
    else:
        valid = False
    return valid   

def main():
    valid_triangles = 0
    three_lines = []
    with open(INPUT) as f:
        for line in f:
            if len(three_lines) == 3:
                for i in range(0,3):
                    newline = [one_line[i] for one_line in three_lines]
                    if check_is_valid(newline):
                        valid_triangles += 1
                three_lines = []
            line = line.strip()
            tokens = line.split()
            three_lines.append(tokens)
    if len(three_lines) == 3:
        for i in range(0,3):
            newline = [one_line[i] for one_line in three_lines]
            if check_is_valid(newline):
                valid_triangles += 1
    print valid_triangles

if __name__ == "__main__":
    main()