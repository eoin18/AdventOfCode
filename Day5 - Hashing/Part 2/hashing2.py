import hashlib

INPUT = "ffykfhsq"

def hashed(input_with_integer):
    return hashlib.md5(input_with_integer).hexdigest()

def has_spaces(password):
    return ' ' in password

def main():
    password = [" ", " ", " ", " ", " ", " ", " ", " "]
    i = 0
    while has_spaces(password):
        input_with_integer = INPUT + str(i)
        hex_hash = hashed(input_with_integer)
        if hex_hash.startswith('00000'):
            if hex_hash[5].isdigit():
                index = int(hex_hash[5])
                print index
                if index >= 0 and index <= 7:
                    if password[index] == " ":
                        password[index] = hex_hash[6]
                        print password
        i += 1
    print "".join(password)


if __name__ == "__main__":
    main()
    