/** This is solution is just for reference. Notice how much faster it
    is than the Java version (0.004s vs 0.131s). This is due to JVM
    startup time:
    http://en.wikipedia.org/wiki/Java_performance#Startup_time */

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
          cout << x << " " << y << " " << z << endl;
          found = true;
        }
      }
    }
    
    if (!found)
      cout << "No solution." << endl;
  }

  return 0;
}
