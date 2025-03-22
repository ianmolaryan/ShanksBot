#include <iostream>
#include <iomanip>
#include <sstream>
#include <cmath>
#include <chrono>

using namespace std;

bool isPrime(int num) {
    if (num <= 1) return false;
    if (num <= 3) return true;
    if (num % 2 == 0 || num % 3 == 0) return false;
    for (int i = 5; i * i <= num; i += 6)
        if (num % i == 0 || num % (i + 2) == 0)
            return false;
    return true;
}

int countRepeatingDecimalLength(int denom, int precision) {
    ostringstream result;
    result << fixed << setprecision(precision) << 1.0 / denom;
    string decimalPart = result.str().substr(result.str().find('.') + 1);
    
    for (int length = 1; length <= decimalPart.length() / 2; length++) {
        string pattern = decimalPart.substr(0, length);
        bool isRepeating = true;
        for (int start = length; start + length <= decimalPart.length(); start += length) {
            if (decimalPart.substr(start, length) != pattern) {
                isRepeating = false;
                break;
            }
        }
        if (isRepeating) return length;
    }
    return 0;
}

int main() {
    int prime;
    cout << "Enter a prime number:           ";
    cin >> prime;
    
    if (!isPrime(prime)) {
        cout << prime << " is not a prime number. Exiting.\n";
        return 0;
    }
    
    int precision = prime * 2;
    auto startTime = chrono::high_resolution_clock::now();
    int repeatingLength = countRepeatingDecimalLength(prime, precision);
    auto endTime = chrono::high_resolution_clock::now();
    
    double durationInSeconds = chrono::duration<double>(endTime - startTime).count();
    
    cout << "Prime number:                   " << prime << "\n";
    cout << "Precision used:                 " << precision << "\n";
    cout << "Length of repeating part:       " << repeatingLength << "\n";
    cout << "Calculation execution time:     " << durationInSeconds << " seconds\n";
}
