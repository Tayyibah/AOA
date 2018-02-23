#ifndef NODE_H
#define NODE_H
#include "CString.h"
class LinkedList;
class Node
{
friend class LinkedList;
public:
	char * data;
	Node * next;
};
#endif