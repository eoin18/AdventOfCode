import operator
from string import ascii_lowercase

INPUT="input.txt"

def is_real_encrypted_name(encrypted_name, checksum):
    entries_by_length = {}
    for c in ascii_lowercase:
        entries_by_length[c] = encrypted_name.count(c)
    sorted_by_value = sorted(entries_by_length.items(), key=lambda x: (-x[1],x[0]))
    if len(sorted_by_value) >= 5:
        first_five = sorted_by_value[:5]
        expected_checksum = ""
        for i in range(0,5):
            expected_checksum += first_five[i][0]
        return expected_checksum == checksum
    print 'Skipped %s' + encrypted_name
    return False

def main():
    sum_of_sector_ids = 0
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
                encrypted_name = ''.join(encrypted_name_and_sector_id)
                if is_real_encrypted_name(encrypted_name, checksum):
                    sum_of_sector_ids += sector_id
            else:
                print 'Skipping %s' + line
    print sum_of_sector_ids

if __name__ == "__main__":
    main()