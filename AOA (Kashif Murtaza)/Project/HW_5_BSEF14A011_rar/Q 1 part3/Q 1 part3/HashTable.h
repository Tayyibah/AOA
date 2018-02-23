#ifndef HASHTABLE_H
#define HASHTABLE_H
#include"HashNode.h"
#include<iostream>
#include <ctime>
#include<string>
using namespace std;
#include <ctime>
#include"LinkedList.h"	

class HashTable
{
private:
	int a,b;
	int p;//prime no.
	LinkedList *arr;	//array of linked list
	int r;    
	int m;    //size of table
	int countOfCollisions;	//no. of collisions
	int n; // Current number of elements present in the table
public:
	int getRandomNumber (int start, int end);
	bool isPrime(int num) ;
	HashTable();
	~HashTable();
	int HashFunc(unsigned long long key, unsigned long long A,int m);  //generate index
	HashTable(const HashTable &orig);
	HashTable operator=(HashTable& val);
	HashTable (int size);// Constructor to allocate and initialize an empty hash table of the specified size
	bool isEmpty (); // Checks whether hash table is empty or not
	bool isFull (); // Checks whether hash table is full or not
	void displayTable ();//display table
	int  insert(unsigned long long k[] , unsigned long long A,int i);
	int determineCountOfCollision();
};
#endif