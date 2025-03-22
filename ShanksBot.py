import time
from decimal import Decimal, getcontext

def is_prime(num):
    if num <= 1:
        return False
    if num <= 3:
        return True
    if num % 2 == 0 or num % 3 == 0:
        return False
    i = 5
    while i * i <= num:
        if num % i == 0 or num % (i + 2) == 0:
            return False
        i += 6
    return True

def count_repeating_decimal_length(denom, precision):
    getcontext().prec = precision
    decimal_part = str(Decimal(1) / Decimal(denom)).split('.')[-1]
    for length in range(1, len(decimal_part) // 2 + 1):
        pattern = decimal_part[:length]
        if all(decimal_part[i:i+length] == pattern for i in range(length, len(decimal_part) - length, length)):
            return length
    return 0

def main():
    prime = int(input("Enter a prime number:           "))
    if not is_prime(prime):
        print(f"{prime} is not a prime number. Exiting.")
        return
    precision = prime * 2
    start_time = time.time()
    repeating_length = count_repeating_decimal_length(prime, precision)
    duration_in_seconds = time.time() - start_time
    print(f"Prime number:                   {prime}")
    print(f"Precision used:                 {precision}")
    print(f"Length of repeating part:       {repeating_length}")
    print(f"Calculation execution time:     {duration_in_seconds} seconds")

if __name__ == "__main__":
    main()
