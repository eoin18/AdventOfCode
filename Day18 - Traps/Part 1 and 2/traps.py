INPUT = ".^^^^^.^^^..^^^^^...^.^..^^^.^^....^.^...^^^...^^^^..^...^...^^.^.^.......^..^^...^.^.^^..^^^^^...^."
ROWS = 400000

def generate_space_for_index(index, prev_row) :
    if index == 0 :
        left = '.'
    else :
        left = prev_row[index - 1]
    if index == len(prev_row) - 1 :
        right = '.'
    else :
        right = prev_row[index + 1]
    centre = prev_row[index]

    return '^' if (
        (left == '^' and centre == '^' and right == '.') or 
        (left == '.' and centre == '^' and right == '^') or 
        (left == '^' and centre == '.' and right == '.') or 
        (left == '.' and centre == '.' and right == '^')) else '.'

def generate_next_row(prev_row) :
    row = []
    for i in range (0, len(prev_row)):
        row.append(generate_space_for_index(i, prev_row))
    return row

def seed_initial_row(board) :
    board[0] = []
    for i in range(0, len(INPUT)) :
        board[0].append(INPUT[i])

def main():
    board = {}
    seed_initial_row(board)
    for i in range(1, ROWS):
        board[i] = generate_next_row(board[i-1])
    safe_spaces = sum(v.count('.') for v in board.itervalues())
    print safe_spaces


if __name__ == "__main__":
    main()