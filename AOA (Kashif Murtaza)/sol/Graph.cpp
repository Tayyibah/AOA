#include "Graph.h"

Graph::Graph(int count) {
    if(count <= 0)
        return;
    this->adj = new list<int> [count];
    this->V = count;
}


bool Graph::isValidEdge(int start, int end) {
    return (start >= 0 && start < V) && (end >= 0 && end < V);
}


bool Graph::addEdge(int start, int end) {
    if(!isValidEdge(start, end))
        return false;
    this->adj[start].push_back(end);
    return true;
}

void Graph::display() {
    for(int i = 0; i < V; i++) {
        list<int>::iterator j;
        int count = 0;
        for(j = adj[i].begin(); j != adj[i].end(); j++) {
                cout << i << "->" << *j << endl;
        }
    }
}

void Graph::transpose() {
    list<int> * temp = new list<int> [V];
    for(int i = 0; i < V; i++) {
        list<int>::iterator j;
        int count = 0;
        for(j = adj[i].begin(); j != adj[i].end(); j++) {
                temp[*j].push_back(i);
        }
    }
    delete [] adj;
    adj = temp;

}

Graph::~Graph() {
    delete [] adj;
    adj = NULL;
    V = 0;
}
