INPUT = "00111101111101000"
LENGTH = 35651584

def perform_checksum(checksum):
    result = ""
    i = 0
    while i < len(checksum):
        if checksum[i] == checksum[i+1]:
            result = result + '1'
        else:
            result = result + '0'
        i += 2
    return result

def dragonify(a):
    b = a[::-1]
    b = b.replace('1', '2')
    b = b.replace('0', '1')
    b = b.replace('2', '0')
    return a + '0' + b

def main():
    dragon_data = INPUT
    while len(dragon_data) < LENGTH:
        dragon_data = dragonify(dragon_data)
    checksum = dragon_data[:LENGTH]
    while len(checksum) % 2 == 0:
        checksum = perform_checksum(checksum)
    print checksum

if __name__ == "__main__":
    main()