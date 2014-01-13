#include <iostream>
#include <stdio.h>
#include <string>
#include <sstream>
#include <vector>

using namespace std;

void display(pair<int, int> card) {
  string s;
  int suit = card.first;
  if (suit == 0) s = "Clubs";
  else if (suit == 1) s = "Diamonds";
  else if (suit == 2) s = "Hearts";
  else if (suit == 3) s = "Spades";

  string v;
  int val  = card.second;
  if (val == 14) v = "Ace";
  else if (val == 13) v = "King";
  else if (val == 12) v = "Queen";
  else if (val == 11) v = "Jack";
  else {
    ostringstream convert;
    convert << val;
    v = convert.str();
  }

  cout << v  << " of " << s << endl;

}

vector<pair<int, int> > shuffle_deck(vector<pair<int, int> > deck, vector<int> shuffle) {
  vector<pair<int, int> > new_deck;
  for (int i = 0; i <=52; i++)
    new_deck.push_back(make_pair(-1, -1));

  for (int j = 1; j <= 52; j++) {
    int i = shuffle[j];
    new_deck[j] = deck[i];
  }

  return new_deck;
}

int main() {

  int t;
  cin >> t;

  for (; t > 0; t--) {
    vector<pair<int, int> > deck;
    pair<int, int> c = make_pair(-1 , -1);
    deck.push_back(c);
    for (int suit = 0; suit < 4; suit++) {
      for (int card = 2; card <= 14; card++) {
        pair<int, int> c = make_pair(suit, card);
        deck.push_back(c);
      }
    }
    int n;
    cin >> n;
    vector<vector<int> > shuffles;
    for (int i = 0; i < n; i++) {
      vector<int> s;
      s.push_back(-1);
      for (int j = 0; j < 52; j++) {
        int x;
        cin >> x;
        s.push_back(x);
      }
      shuffles.push_back(s);
    }

    vector<pair<int, int> >::iterator it = deck.begin();
    string line;
    getline(cin, line);
    while (!cin.eof()) {
      getline(cin, line);
      if (line.empty()) break;
      int sf;
      sscanf(line.c_str(), "%d", &sf);
      deck = shuffle_deck(deck, shuffles[sf - 1]);
      it = deck.begin() + 1;
    }

    it = deck.begin() + 1;
    for (; it != deck.end(); it++)
      display(*it);

    if (t > 1)
      cout << endl;
  }

  return 0;
}
