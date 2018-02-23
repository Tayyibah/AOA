#include <iostream>
#include "include/UGraph.h"

using namespace std;

int main()
{
    UGraph g(6);

    g.addEdge(0,1);
    g.addEdge(0,4);
    g.addEdge(2,1);
    g.addEdge(3,4);
    g.addEdge(3,5);

    g.findTwoDegree();

    return 0;
}
