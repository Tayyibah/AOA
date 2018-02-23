#include <iostream>
#include <cstdlib>
#include <ctime>
#include <bitset>
#include <cmath>
#include<stdio.h>
#include<string>
#include"HashTable.h"

bool HashTable::isPrime(int num) 
{
	if(num < 2) 
	{
		return false;
	} 
	else if(num == 2)
	{
		return true;
	} 
	else if(num % 2 == 0) 
	{
		return false;
	}
	for(int i = 3, max = sqrt(num); i < max; i += 2)
	{
		if(num % i == 0) {
			return false;
		}
	}
	return true;
}

int HashTable::getRandomNumber (int start,int end)
{
	return ( rand() % (end - start+1) ) + start;
}
HashTable::HashTable (int size)// Constructor to allocate and initialize an empty hash table of the specified size
{
	countOfCollisions=0;
	n=size;
	m=n*n;
	while(p<m)
	{
		int r = rand();
		if(isPrime(r)) 
			p=r;
	}
	a=getRandomNumber(1,p-1);
	b=getRandomNumber(0,p-1);
	arr=new LinkedList[m];
	for(int i=0;i<m;i++)
	{
		arr[i].insertStart("-1");
	}
	r=log(m)/log(2);
	n=0;
}


int HashTable::getHashValue (char * name) // Private member function of HashTable class
{
	int temp = 0;
	for (int i=0; i<strlen(name); i++)
	{
		temp = temp + name[i];
	}
	return temp;
}

int HashTable::hash(char *str)
{
    int h = 0;
    while (*str)
       h = h << 1 ^ *str++;
    return h;
}
int HashTable::hashing (CString word) 
{
    int result = 0;
    for(size_t i = 0; i < word.getLength(); ++i)
	{
        result += word[i] * pow(31, i);
    }
    return result;
}


int HashTable::insert(char * k[] , unsigned long long A,const int size)
{ 
	int num[10];
	for(int i=0;i<size;i++)
		num[i]=hash(k[i]);
	int index=0;
	for (int i=0 ; i<size ; i++)
	{	
		if (n>=m)
		{	
			m= m*2;
			LinkedList *NewArray= new LinkedList[m];
			for (int i=0 ; i<m ; i++)
			{
				NewArray[i].insertStart(k[i]);
			}
			n=0;
			for (int j=0 ; j<=i; j++)
			{
				index= HashFunc (num[j], A,m);
				NewArray[index].insertStart(k[j]);
				n++;
			}
			delete [] arr;
			arr=NewArray;
			NewArray=0;
		}
		else //if(n<m)
		{
			index=HashFunc (num[i], A,m); 
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
	k=(key*A) % p;
	return k% m;
}
void HashTable::displayTable ()
{
	cout<<"Entries in Hash Table are :"<<endl;
	int i=0;
	while(i!=n )
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

