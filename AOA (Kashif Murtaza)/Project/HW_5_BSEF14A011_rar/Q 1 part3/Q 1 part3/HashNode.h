#ifndef HASHNODE_H
#define HASHNODE_H
class HashTable;
class HashNode
{
friend class HashTable;
private:
	unsigned long long data; //  unsigned long long value;
	HashNode * next;
public:	
	HashNode(int key, unsigned long long value)
	{
		this->data = value;
		this->next = NULL;
	}
};
#endif