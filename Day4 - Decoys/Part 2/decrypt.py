from string import ascii_lowercase

INPUT="input.txt"

def shift_character(character, amount_to_shift):
    character_code = ord(character) - 97
    character_code = (character_code + amount_to_shift) % 26
    return chr(character_code + 97)

def shift_word(encrypted_word, amount_to_shift):
    decrypted_word = ""
    for c in encrypted_word:
        decrypted_word += shift_character(c, amount_to_shift)
    return decrypted_word

def decrypt(encrypted_name, sector_id):
    amount_to_shift = sector_id % 26
    if amount_to_shift > 0:
        decrypted_names = []
        for token in encrypted_name:
            decrypted_names.append(shift_word(token, amount_to_shift))
        return ' '.join(decrypted_names)
    else:
        return ' '.join(encrypted_name)

def main():
    with open(INPUT) as f:
        for line in f:
            code = line.split('[')
            if len(code) == 2:
                encrypted_name_and_sector_id = code[0]
                checksum = code[1].replace('\n', '')
                checksum = checksum.replace(']', '')
                encrypted_name_and_sector_id = encrypted_name_and_sector_id.split('-')
                sector_id = int(encrypted_name_and_sector_id[len(encrypted_name_and_sector_id) - 1])
                encrypted_name_and_sector_id.pop()
                encrypted_name = encrypted_name_and_sector_id
                decrypted_name = decrypt(encrypted_name, sector_id)
                if 'north' in decrypted_name:
                    print decrypted_name
                    print sector_id
            else:
                print 'Skipping %s' + line

if __name__ == "__main__":
    main()