#include <iostream>
#include <cstdlib>
#include <ctime>
#include <bitset>
#include<stdio.h>
#include"LinkedList.h"
#include<string>
#include <cmath>
#include"HashTable.h"
using namespace std;

unsigned rand256()
{
    static unsigned const limit = RAND_MAX - RAND_MAX % 256;
    unsigned result = rand();
    while ( result >= limit ) {
        result = rand();
    }
    return result % 256;
}

unsigned long long rand64bits()
{
    unsigned long long results = NULL;
    for ( int count = 8; count > 0; -- count ) 
	{
        results = 256 * results + rand256();
    }
    return results;
}
unsigned long long generateDistinctRandomKeys(int n)
{
    return rand64bits();
}
unsigned long long  getRandomNumber (unsigned long long start, unsigned long long end)
{
	return ( rand() % (end - start+1) ) + start;
}

int fun()
{
	const int n=10;
	int i=0;
	HashTable table(1);
	 int seed =time(NULL);
	srand(seed);
	unsigned long long D[n];
	/*for(int i=0;i<n;i++)
	{
		D[i]=getRandomNumber(rand64bits(),rand64bits());
	}*/
	for(int i=0;i<n/2;i++)
	{
		D[i]=getRandomNumber(rand64bits(),rand64bits());
	}
	for(int i=n/2;i<n;i++)
	{
		D[i]=getRandomNumber(1,rand64bits());
	}
	//unsigned long long D[n]={141,22392,9343,6,96564,6686,9896,908,99,4};
	/*for(int i=0;i<n;i++)
		cout<<D[i]<<".  "<<endl;*/
	unsigned long long  A,k=0;
	A=getRandomNumber(9823372036854775808,11446744073709551615);
	cout<<"\nA:"<<A<<endl;
	i=0;
	table.insert(D,A,n);
	while(table.determineCountOfCollision()!=0)
	{
		for(int i=0;i<n;i++)
		{
			D[i]=getRandomNumber(rand64bits()*rand(),rand64bits());
			table.insert(D,A,n);
		}
	}
	table.displayTable ();
	cout<<"Collision : "<<table.determineCountOfCollision()<<endl;
	return 0;
}


// The main program calls fun() and measures time taken by fun()
int main()
{
    // Calculate the time taken by fun()
    clock_t t;
    t = clock();
    fun();
    t = clock() - t;
    double time_taken = ((double)t)/CLOCKS_PER_SEC; // in seconds
 
    printf("fun() took %f seconds to execute \n", time_taken);
    return 0;
}