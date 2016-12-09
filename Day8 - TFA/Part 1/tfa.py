from copy import deepcopy

INPUT = "input.txt"
RECT = "rect"
ROTATE = "rotate"
COLUMN = "column"
ROW = "row"

def handle_rect(instruction, screen):
    x, y = instruction.split('x')
    for i in range(0, int(y)):
        for j in range(0, int(x)):
            screen[i][j] = 1
    return screen

def replace_twos_with_ones(screen):
    for i in range(0, len(screen)):
        for j in range(0, len(screen[i])):
            if screen[i][j] == 2:
                screen[i][j] = 1

def handle_rotate_column(instruction, screen):
    column = int(instruction.split()[0][2:])
    range_of_column = len(screen)
    amount_to_rotate = int(instruction.split()[2])
    temp_screen = deepcopy(screen)
    for i in reversed(range(0, range_of_column)):
        target_index = i + amount_to_rotate
        if screen[i][column] == 1:
            if target_index >= range_of_column:
                target_index = target_index - range_of_column
            if temp_screen[i][column] != 2:
                temp_screen[i][column] = 0
            temp_screen[target_index][column] = 2
    replace_twos_with_ones(temp_screen)
    return temp_screen

def  handle_rotate_row(instruction, screen):
    row = int(instruction.split()[0][2:])
    range_of_row = len(screen[row])
    amount_to_rotate = int(instruction.split()[2])
    temp_screen = deepcopy(screen)
    for i in reversed(range(0, range_of_row)):
        target_index = i + amount_to_rotate
        if screen[row][i] == 1:
            if target_index >= range_of_row:
                target_index = target_index - range_of_row
            if temp_screen[row][i] != 2:
                temp_screen[row][i] = 0
            temp_screen[row][target_index] = 2
    replace_twos_with_ones(temp_screen)
    return temp_screen

def handle_rotate(instruction, screen):
    if instruction.startswith(COLUMN):
        return handle_rotate_column(instruction[len(COLUMN):].strip(), screen)
    elif instruction.startswith(ROW):
        return handle_rotate_row(instruction[len(ROW):].strip(), screen)
    else:
        return screen

def handle_line(instruction, screen):
    if instruction.startswith(RECT):
        return handle_rect(instruction[len(RECT):].strip(), screen)
    elif instruction.startswith(ROTATE):
        return handle_rotate(instruction[len(ROTATE):].strip(), screen)
    else:
        return screen

def pretty_print_screen(screen):
    for i in range(0, len(screen)):
        print screen[i]
    print ''

def count(screen):
    count = 0
    for i in range(0, len(screen)):
        for j in range(0, len(screen[i])):
            count += screen[i][j]
    return count

def main():
    range_x, range_y = 50, 6
    screen = [[0 for x in range(range_x)] for y in range(range_y)]
    with open(INPUT) as infile:
        for line in infile:
            print line
            screen = handle_line(line, screen)
    print count(screen)
    pretty_print_screen(screen)

if __name__ == "__main__":
    main()