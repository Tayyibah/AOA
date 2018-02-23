#include"HashTable.h"
HashTable::HashTable (int size)// Constructor to allocate and initialize an empty hash table of the specified size
{
	S=size; 
	table=new string[size];
	for(int i=0;i<S;i++)
	{
		table[i] = "-1";
	}
	numbers=new int[size];
	for(int i=0;i<S;i++)
	{
		numbers[i] = -1;
	}
	n=0;
}
HashTable::~HashTable () // Destructor
{
	if(table)
	{
		delete[]table;
		table=0;
	}
	if(table)
	{
		delete[]numbers;
		table=0;
	}
	S=0; 
	n=0;
}

int HashTable::Hash(int k,int A)
{
	k=(A*k % 2^64)>>(64-1);
	return k;
}
bool HashTable::isEmpty () // Checks whether hash table is empty or not
{
	if(S==0)
		return true;
	return false;
}
bool HashTable::isFull ()// Checks whether hash table is full or not
{
	if(S==n)
		return true;
	return false;
}
double HashTable::loadFactor ()
{
	return n/S;
}
int HashTable::getHashValue (string name) // Private member function of HashTable class
{
	int temp = 0;
	for (int i=0; i<name.length(); i++)
	{
		temp = temp + name[i];
	}
	return temp;
}

bool HashTable::insert (string name)
{
	int index=getHashValue(name)%S;
	int ind=index;
	if(table[index]=="-1")
	{
		table[index]=name;
		return true;
	}
	else
	{
		while(table[index]!="-2"||table[index]!="-1"||index!=ind)
		{
			if(table[index]=="-1"||table[index]!="-2")
			{
				table[index]=name;
				return true;
			}
			cout<<index++<<endl;
		}
	}
	return false;
}
bool  HashTable::insert (int num)
{
	int index=num%S;
	int ind=index;
	if(numbers[index]==-1)
	{
		numbers[index]=num;
		n++;
		return true;
	}
	else
			{
				return false;
			}
	/*else
	{
		while(numbers[index]!=-2||numbers[index]!=-1||index!=ind)
		{
			if(numbers[index]==-1||numbers[index]!=-2)
			{
				numbers[index]=num;
				return true;
			}
			else
			{
				return false;
			}
			cout<<index++<<endl;
		}
	}
	return false;*/
}
bool HashTable::search (string name)
{
	int index=getHashValue(name)%S;
	int ind=index;
	if(table[index]==name)
	{
		return true;
	}
	else
	{
		while(table[index]!=name&&index!=ind)
		{
			if(table[index]==name)
			{
				return true;
			}
			cout<<index++<<endl;
		}
	}
	return false;
}
void HashTable::display ()
{
	int i=0;
	while(i!=S)
	{
		if(table[i]=="-1"||table[i]=="-2")
			cout<<"EMPTY"<<endl;
		else
			cout<<table[i]<<endl;
		i++;
	}
}
void HashTable::displayNumner ()
{
	int i=0;
	while(i!=S)
	{
		if(numbers[i]==-1||numbers[i]==-2)
			cout<<"EMPTY"<<endl;
		else
			cout<<numbers[i]<<endl;
		i++;
	}
}
bool HashTable::remove (string name)
{
	for(int i=0;i<S;i++)
	{
		if(search(name))
		{
			int index=getHashValue(name)%S;
			table[index]="-2";
			return true;
		}
		else
			return false;
	}
}