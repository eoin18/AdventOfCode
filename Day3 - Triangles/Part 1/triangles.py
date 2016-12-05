INPUT = "input.txt"

def is_valid_triangle(one_side, other_sides):
    sum_of_other_sides = 0
    for side in other_sides:
        sum_of_other_sides += side
    return one_side < sum_of_other_sides

def check_is_valid(line):
    line = line.strip()
    tokens = line.split("  ")
    valid = True
    if len(tokens) == 3:
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
    with open(INPUT) as f:
        for line in f:
            if check_is_valid(line):
                valid_triangles+=1
    print(valid_triangles)        

if __name__ == "__main__":
    main()