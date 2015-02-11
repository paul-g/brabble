#include <string>
#include <random>
#include <iostream>
#include <chrono>

using namespace std;

constexpr int n = 1024;

double a[n][n], b[n][n], c[n][n];

int main() {


  std::random_device rd;
  std::mt19937 gen(rd());
  std::uniform_real_distribution<> dis(1, 2);

  for (int i = 0; i < n; i++)
    for (int j = 0; j < n; j++) {
      a[i][j] = dis(gen);
      b[i][j] = dis(gen);
    }

  std::chrono::time_point<std::chrono::system_clock> start, end;
  start = std::chrono::system_clock::now();
  for (int i = 0; i < n; i++)
    for (int j = 0; j < n; j++)
      for (int k = 0; k < n; k++)
        c[i][j] = a[i][k] * b[k][j];
  end = std::chrono::system_clock::now();
  std::chrono::duration<double> elapsed_seconds = end - start;
  cout << "Naive took: " << elapsed_seconds.count() << endl;

  start = std::chrono::system_clock::now();
  for (int i = 0; i < n; i++)
    for (int k = 0; k < n; k++)
      for (int j = 0; j < n; j++)
        c[i][j] = a[i][k] * b[k][j];
  end = std::chrono::system_clock::now();
  elapsed_seconds = end - start;
  cout << "Interchanged took: " << elapsed_seconds.count() << endl;

  // Blocked
  for (int ii = 32; ii <= 1024; ii += 32) {
    start = std::chrono::system_clock::now();
    int bb = ii;
    for (int i0 = 0; i0 < n; i0 += bb) {
      int mi = min(i0 + bb, n);
      for (int j0 = 0; j0 < n; j0 += bb) {
        int mj  = min(j0 + bb, n);
        for (int k0 = 0; k0 < n; k0 += bb) {
          int mk = min(k0 + bb, n);
          for (int i = i0; i < mi ; i++)
            for (int k = k0; k < mk ; k++)
              for (int j = j0; j < mj; j++) {
                c[i][j] = a[i][k] * b[k][j];
              }
        }
      }
    }
    end = std::chrono::system_clock::now();
    elapsed_seconds = end - start;
    cout << "Blocked " << ii << " took " << elapsed_seconds.count() << endl;
  }

  return 0;
}
