#include <iostream>
#include <cstdlib>
#include <ctime>

#include <bitset>
#include<stdio.h>
#include<string>
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
    unsigned long long results = 0ULL;
    for ( int count = 8; count > 0; -- count ) 
	{
        results = 256U * results + rand256();
    }
    return results;
}
unsigned long long generateDistinctRandomKeys(int n)
{
    return rand64bits();
}
void initialize ()
{
	srand ( time(NULL) );
}
int getRandomNumber (int start, int end)
{
	return ( rand() % (end - start+1) ) + start;
}
int main()
{

	const int n=10;
	unsigned long long D[n];
	for(int i=0;i<n;i++)
		D[i] = generateDistinctRandomKeys(n);
	for(int i=0;i<n;i++)
		cout<<D[i]<<".\n";
	int A,k,r;
	A=rand64bits();
	int m=1;//size of table
	HashTable table(10);
	initialize ();
	int sum=0;
	table.Hash(2,A);//*******************************************************************************
	for(int i=0;i<10;i++)
	{
		while(table.insert (D[i]))
		{
			if(!table.insert (D[i]))
				sum++;
		}
	}

	 unsigned short short11 = 1024;
    unsigned short short12 = short11 >> 1;  // 512
    cout << short12 << endl;     // 0000001000000000
	
	int a = 60;

	printf("\nNumber is Shifted By 1 Bit  : %d",a >> 1);//30
	printf("\nNumber is Shifted By 2 Bits : %d",a >> 2);//15
	printf("\nNumber is Shifted By 3 Bits : %d",a >> 3);//7
	cout<<sum<<endl;
	return 0;
}