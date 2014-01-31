#include <iostream>

using namespace std;

int main() {

  bool found;
  int nt, a, b, c;
  cin >> nt;
  
  for (int t = 0; t < nt; t++) {
    int bound = 34;
    cin >> a >> b >> c;
    found = false;
    for (int x = -bound; x <= bound && !found; x++) {
      for (int y = x + 1; y <= bound && !found; y++) {
        int z = a - x - y;
        if ( y != z && z != x &&
             x * y * z == b &&
             x * x + y * y + z * z == c) {
          int small = min(x, min(y, z));
          int large = max(x, max(y, z));
          int mid = a - small - large;
          cout << small << " " << mid << " " << large << endl;
          found = true;
        }
      }
    }
    
    if (!found)
      cout << "No solution." << endl;
  }

  return 0;
}
