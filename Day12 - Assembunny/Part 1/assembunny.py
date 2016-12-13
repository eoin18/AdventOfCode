INPUT="input.txt"
CPY = "cpy"
INC = "inc"
DEC = "dec"
JNZ = "jnz"

registers = {}
registers["a"] = 0
registers["b"] = 0
registers["c"] = 0
registers["d"] = 0

def performCpy(instruction):
    value, target = instruction.split()
    if value.isalpha():
        registers[target] = registers[value]
    else:
        registers[target] = int(value)

def performInc(instruction):
    registers[instruction] += 1

def performDec(instruction):
    registers[instruction] -= 1

def performJnz(instruction, index):
    test, target = instruction.split()
    check = 0
    if test.isalpha():
        check = registers[test]
    else:
        check = int(test)
    if check != 0:
        return index + int(target)
    return index + 1

def execute(index, current_instruction):
    if current_instruction.startswith(CPY):
        performCpy(current_instruction[len(CPY):].strip())
        return index + 1
    elif current_instruction.startswith(INC):
        performInc(current_instruction[len(INC):].strip())
        return index + 1
    elif current_instruction.startswith(DEC):
        performDec(current_instruction[len(DEC):].strip())
        return index + 1
    elif current_instruction.startswith(JNZ):
        return performJnz(current_instruction[len(JNZ):], index)

def main():
    instructions = []
    with open(INPUT) as infile:
        for line in infile:
            instructions.append(line)
    i = 0
    while i < len(instructions):
        current_instruction = instructions[i]
        i = execute(i, current_instruction)
    print registers

if __name__ == "__main__":
    main()