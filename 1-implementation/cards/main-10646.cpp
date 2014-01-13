/*
 * main-10646.cpp
 *
 *  Created on: Sep 10, 2013
 *      Author: paul
 */

#include <iostream>
#include <vector>
#include <stdio.h>

using namespace std;

string high_cards = "AKQJT";

int value(pair<char, char> card) {
  char v = card.first;
  if (high_cards.find(v) != string::npos)
    return 10;
  return (int)v - (int)'0';
}

int main() {

  int t;
  cin >> t;
  int cse = 1;
  scanf("\n");
  for (; t > 0; t--) {
    char val, suit;

    vector<pair<char, char> > cards, top;
    for (int i = 0; i < 52 - 25; i++) {
      scanf("%c%c ", &val, &suit);
      cards.push_back(make_pair(val, suit));
    }

    for (int i = 0; i < 25; i++) {
      scanf("%c%c ", &val, &suit);
      top.push_back(make_pair(val, suit));
    }

    int y = 0;

    for (int i = 0; i < 3; i++) {
      pair<char, char> t = *(cards.end() - 1);
      int v = value(t);
      cards.erase(cards.end() - ((10 - v) + 1), cards.end());
      y += v;
    }

    pair<char, char> res;
    if (y < cards.size()) {
      res = cards[y];
    } else {
      res = top[y - cards.size() - 1];
    }

    cout << "Case " << cse << ": ";
    cout << res.first << res.second << endl;
    cse++;
  }

}
