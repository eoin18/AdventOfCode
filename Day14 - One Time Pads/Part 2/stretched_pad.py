import hashlib
import re

INPUT = "qzyelonm"
RANGE = 2016
STRETCHED_HASHES = {}

def hash_with_stretch(salt_and_index):
    if salt_and_index in STRETCHED_HASHES:
        md5_hash = STRETCHED_HASHES[salt_and_index]
    else:
        md5_hash = hashlib.md5(salt_and_index).hexdigest()
        for i in range(0, RANGE):
            md5_hash = hashlib.md5(md5_hash).hexdigest()
        STRETCHED_HASHES[salt_and_index] = md5_hash
    return md5_hash

def has_five_in_a_row(range_start, range_end, character):
    for index in range(range_start, range_end):
        salt_and_index = INPUT + str(index)
        md5_hash = hash_with_stretch(salt_and_index)
        quints = re.findall(r"("+character+")\\1{4}", md5_hash)
        if len(quints) > 0:
            return True
    return False

def get_triplet(md5_hash):
    triplets = re.findall(r"(.)\1{2}", md5_hash)
    if len(triplets) > 0:
        return triplets[0][0]
    return None

def main():
    index = 0
    hashes = []
    while len(hashes) < 64:
        salt_and_index = INPUT + str(index)
        md5_hash = hash_with_stretch(salt_and_index)
        triplet_character = get_triplet(md5_hash)
        if triplet_character:
            if has_five_in_a_row(index+1, index+1000, triplet_character):
                print md5_hash
                hashes.append(md5_hash)
        index += 1
    print index-1


if __name__ == "__main__":
    main()