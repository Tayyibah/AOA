#include <iostream>
#include <cstdlib>
#include <ctime>
#include <bitset>
#include<stdio.h>
#include"LinkedList.h"
#include "CString.h"
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
	char *  D[n]={"abc","de","f","gh","ij","kl","mno","pqrst","uvw","xyz"};

	unsigned long long  A,k=0;
	A=getRandomNumber(9823372036854775808,11446744073709551615);
	cout<<"\nA:"<<A<<endl;
	i=0;
	table.insert(D,A,n);
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