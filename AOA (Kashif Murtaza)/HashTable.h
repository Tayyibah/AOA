#ifndef HASHTABLE_H
#define HASHTABLE_H
#include<iostream>
#include <ctime>
#include<string>
using namespace std;
class HashTable
{
private:
	string * table; // Dynamic array of strings to hold names
	int *numbers;
	int S; // Total number of slots in the table
	int n; // Current number of elements present in the table
	int getHashValue (string name) ;
public:
	HashTable (int size);// Constructor to allocate and initialize an empty hash table of the specified size
	~HashTable (); // Destructor
	int Hash(int k,int A);
	bool isEmpty (); // Checks whether hash table is empty or not
	bool isFull (); // Checks whether hash table is full or not
	double loadFactor (); // Calculates & returns the load factor of the hash table (n/S)
	bool insert (string name);
	bool insert (int num);
	bool search (string name);
	void display ();
	bool remove (string name);
	void displayNumner ();
};
#endif

