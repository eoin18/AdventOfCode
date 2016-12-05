import hashlib

INPUT = "ffykfhsq"

def hashed(input_with_integer):
    return hashlib.md5(input_with_integer).hexdigest()

def main():
    password = ""
    i = 0
    while len(password) < 8:
        input_with_integer = INPUT + str(i)
        hex_hash = hashed(input_with_integer)
        if hex_hash.startswith('00000'):
            password += hex_hash[5]
        i += 1
    print password


if __name__ == "__main__":
    main()
