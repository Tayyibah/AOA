#include "../include/UGraph.h"

UGraph::UGraph(int count)
{
    this->V = count;
    adj = new list<int> [count];
}

void UGraph::addEdge(int start, int end) {
    adj[start].push_back(end);
    adj[end].push_back(start);
}

void UGraph::display() {

    for(int i = 0; i < V; i++) {
        list<int>::iterator j;
        for(j = adj[i].begin(); j != adj[i].end(); j++) {
            cout << i << "->" << *j << endl;
        }
    }

}

bool UGraph::isBipartite(int src) {
    int color[V];
    for(int i = 0; i < V; i++) {
        color[i] = -1;
    }

    color[src] = 1;

    queue<int> q;
    q.push(src);

    while(!q.empty()) {
        int u = q.front();
        q.pop();
        list<int>::iterator i;
        for(i = adj[u].begin(); i != adj[u].end(); i++) {
            int edg = *i;
            if(color[edg] == -1)
            {
                color[edg] = 1 - color[u];
                q.push(edg);
            }
            else if(color[edg] == color[u])
                return false;
        }

    }
    return true;
}


void UGraph::findTwoDegree() {
    int degree[V];
    int twoDegree[V];

    for(int i = 0; i < V; i++) {
        degree[i] = 0;
        list<int>::iterator j;
        for(j = adj[i].begin(); j != adj[i].end();  j++) {
            degree[i] = degree[i] + 1;
        }
    }

    for(int i = 0; i < V; i++) {
        twoDegree[i] = 0;
        list<int>::iterator j;
        for(j = adj[i].begin(); j != adj[i].end();  j++) {
            twoDegree[i] = twoDegree[i] + degree[*j];
        }
    }


    for(int i = 0; i < V; i++) {
        cout << "Two Degree of vertex " << i << " is: " << twoDegree[i] << endl;
    }

}

UGraph::~UGraph()
{
    delete [] adj;
    adj = NULL;
    V = NULL;
}
