from random import random

def base10tobase2(value, zfill=0):
    new_value = []
    val = int(value)
    if val < 0:
        neg = True
        val *= -1
    else:
        neg = False

    if val == 0:
        new_value = ['0']

    while val > 0:
        new_value.append(str(val % 2))
        val = val / 2

    new_value.reverse()
    new_value_str = ''.join(new_value)
    if zfill:
        if len(new_value_str) > zfill:
            raise ValueError("""
            Base 2 version of %s is longer, %s, than the zfill limit, %s
            """ % (value, new_value_str, zfill))
        else:
            new_value_str = new_value_str.zfill(zfill)

    if neg:
        new_value_str = "-" + new_value_str

    return new_value_str


def base2tobase10(value):
    new_value = 0
    val = str(value)
    if val < 0:
        neg = True
        val *= -1
    else:
        neg = False

    val = str(value)

    factor = 0
    for i in range(len(val) - 1, -1, -1):
        if not val[i] == '-':
            new_value += int(val[i]) * pow(2, factor)
        else:
            neg = True
        factor += 1

    if neg:
        new_value *= -1

    return new_value
