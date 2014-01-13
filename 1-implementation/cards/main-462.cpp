#include <iostream>
#include <string>
#include <string.h>
#include <stdio.h>

using namespace std;

int value(char suit) {
  if (suit == 'S')
    return 0;
  if (suit == 'H')
    return 1;
  if (suit == 'D')
    return 2;
  if (suit == 'C')
    return 3;
  return 4;
}

char code(int suit) {
  switch (suit) {
  case 0:
    return 'S';
  case 1:
    return 'H';
  case 2:
    return 'D';
  case 3:
    return 'C';
  }
}

int main() {

  while (!feof(stdin)) {
    int aces[4], kings[4], jacks[4], queens[4], suits[4];
    memset(aces, 0, sizeof(int) * 4);
    memset(kings, 0, sizeof(int) * 4);
    memset(jacks, 0, sizeof(int) * 4);
    memset(queens, 0, sizeof(int) * 4);
    memset(suits, 0, sizeof(int) * 4);

    int points = 0;

    for (int i = 0; i < 13; i++) {
      char val, suit;
      scanf("%c%c ", &val, &suit);
      if (val == 'A') {
        aces[value(suit)] = 1;
        suits[value(suit)] += 1;
        points += 4;
      } else if (val == 'K') {
        kings[value(suit)] = 1;
        suits[value(suit)] += 1;
        points += 3;
      } else if (val == 'Q') {
        queens[value(suit)] = 1;
        suits[value(suit)] += 1;
        points += 2;
      } else if (val == 'J') {
        jacks[value(suit)] = 1;
        suits[value(suit)] += 1;
        points += 1;
      } else {
        suits[value(suit)] += 1;
      }
    }

    int point_aux = 0;

    //2.
    for (int j = 0; j < 4; j++) {
      if (kings[j] == 1 && suits[j] == 1)
        points--;
    }

    //3.
    for (int j = 0; j < 4; j++) {
      if (queens[j] == 1 && suits[j] <= 2)
        points--;
    }

    //4.
    for (int j = 0; j < 4; j++) {
      if (jacks[j] == 1 && suits[j] <= 3)
        points--;
    }


    //5
    for (int j = 0; j < 4; j++) {
      if (suits[j] == 2)
        point_aux++;
    }
    // 6 & 7
    for (int j = 0; j < 4; j++) {
      if (suits[j] <= 1)
        point_aux += 2;
    }

    // find stopped suits
    int stopped[4];
    memset(stopped, 0, sizeof(int) * 4);
    int t_stopped = 0;
    for (int j = 0; j < 4; j++) {
      if (aces[j] == 1 || (kings[j] == 1 && suits[j] > 1)
          || (queens[j] == 1 && suits[j] > 2)) {
        stopped[j] = 1;
        t_stopped++;
      }
    }

    if (points + point_aux < 14)
      cout << "PASS";
    else if (points >= 16 && t_stopped == 4)
      cout << "BID NO-TRUMP";
    else {
      int max_j = 0;
      for (int j = 1; j < 4; j++) {
        if (suits[j] > suits[max_j])
          max_j = j;
      }
      cout << "BID " << code(max_j);
    }
    cout << endl;
  }

  return 0;
}
