#include <iostream>
#include "Graph.h"
using namespace std;

int main()
{
    Graph g(5);

    g.addEdge(0,1);
    g.addEdge(1,2);
    g.addEdge(2,3);
    g.addEdge(3,4);
    g.addEdge(4,0);

    g.display();
    cout << endl;
    g.transpose();
    g.display();

    return 0;
}
