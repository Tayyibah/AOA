#ifndef NODE_H
#define NODE_H
class LinkedList;
class Node
{
friend class LinkedList;
public:
	unsigned long long data;
	Node * next;
};
#endif