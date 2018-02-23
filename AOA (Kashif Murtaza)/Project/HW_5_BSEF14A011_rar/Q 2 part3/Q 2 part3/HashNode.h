#ifndef HASHNODE_H
#define HASHNODE_H
#include "CString.h"
class HashTable;
class HashNode
{
friend class HashTable;
public:	
	char * data; //  unsigned long long value;
	HashNode * next;
	HashNode(int key, char * value)
	{
		this->data = value;
		this->next = NULL;
	}
};
#endif