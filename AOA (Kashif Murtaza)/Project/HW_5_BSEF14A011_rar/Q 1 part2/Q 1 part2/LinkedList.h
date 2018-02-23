#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include"Node.h"
class LinkedList
{
private:
	Node * next;
public:
	Node * head;
	void displayRecursively(Node * t);
	int findRecursively(Node * t);
	void insertStart(unsigned long long val);
	LinkedList();
	void display();
	unsigned long long  * getnode();
	//void shuffleMerge(LinkedList & list1, LinkedList & list2);
	//void combine (LinkedList& list1, LinkedList& list2);
	//void swapp(Node * val1, Node * val2);
	bool getvalue();
	void insertEnd(unsigned long long val);
	void displayRecursiveCall();
	int findRecursiveCall();
	LinkedList(const LinkedList &orig);
	LinkedList operator=(LinkedList& val);
	bool isEmpty();
	~LinkedList();
	int GetNth( int index);
};
#endif