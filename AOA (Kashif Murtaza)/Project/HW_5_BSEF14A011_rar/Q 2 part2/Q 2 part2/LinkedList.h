#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include "CString.h"
#include"Node.h"
class LinkedList
{
private:
	Node * next;
public:
	Node * head;
	void insertStart(char * val);
	LinkedList();
	void display();
	char *  * getnode();
	bool getvalue();
	void insertEnd(char * val);
	LinkedList(const LinkedList &orig);
	LinkedList operator=(LinkedList& val);
	bool isEmpty();
	~LinkedList();
	int GetNth( int index);
};
#endif