#include <iostream>
#include <cstdlib>
#include <ctime>
#include <bitset>
#include <cmath>
#include<stdio.h>
#include<string>
#include"HashTable.h"

HashTable::HashTable (int size)// Constructor to allocate and initialize an empty hash table of the specified size
{
	countOfCollisions=0;
	m=size;
	arr=new LinkedList[m];
	for(int i=0;i<m;i++)
	{
		arr[i].insertStart(NULL);
	}
	r=log(m)/log(2);
	n=0;
}
int HashTable::insert(unsigned long long k[] , unsigned long long A,int size)
{ 
	int index=0;
	for (int i=0 ; i<size ; i++)
	{	
		if (n>=m)
		{	
			m= m*2;
			LinkedList *NewArray= new LinkedList[m];
			for (int i=0 ; i<m ; i++)
			{
				NewArray[i].insertStart(NULL);
			}
			n=0;
			for (int j=0 ; j<=i; j++)
			{
				index= HashFunc (k[j], A,m);
				NewArray[index].insertStart(k[j]);
				n++;
			}
			delete [] arr;
			arr=NewArray;
			NewArray=0;
		}
		else //if(n<m)
		{
			index=HashFunc (k[i], A,m); 
			if(arr[index].head!=NULL)
			{
				countOfCollisions++;
			}
			arr[index].insertStart(k[i]);
			n++;
		}
	}
	return countOfCollisions;
}
int HashTable::HashFunc(unsigned long long key,unsigned long long A,int m)
{
	r=log(m)/log(2);
	int k=0;
	unsigned long long  z=pow(2,64);
	k=(((key*A) % (z))>>(64-r));
	return k% m;

}
void HashTable::displayTable ()
{
	cout<<"Entries in Hash Table are :"<<endl;
	int i=0;
	while(i!=m )
	{
		if(arr[i].isEmpty())
			cout<<"Empty";
		else
			arr[i].display();
		cout<<endl;
		i++;
	}
}
HashTable::~HashTable () // Destructor
{
	if(!arr)
		delete arr;
	arr=0;
	m=0;
	n=0;
}

int HashTable::determineCountOfCollision()
{
	return countOfCollisions;
}

