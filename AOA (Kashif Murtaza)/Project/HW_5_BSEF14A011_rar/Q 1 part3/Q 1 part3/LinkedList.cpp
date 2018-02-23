#include<iostream>
using namespace std;
#include"LinkedList.h"
LinkedList::LinkedList()
{
	head=0;
}
unsigned long long * LinkedList::getnode()
{
	unsigned long long * ar2=new unsigned long long [100];
	Node * t1=head;
	unsigned long long  i=0;
	while(t1!=NULL)
	{
		ar2[i]=t1->data;
		t1=t1->next;
		i++;
	}
	ar2[i]=NULL;
	return ar2;
}
int LinkedList::GetNth(int index)
{
    Node* current = head;
    int count = 0; /* the index of the node we're currently looking at */
    while (current != NULL /*||current->next != NULL */)
    {
       if (count == index)
          return(current->data);
       count++;
       current = current->next;
    } 
    /* if we get to this line, the caller was asking for a non-existent element so we assert fail */
     return 0;
}
bool LinkedList::isEmpty()
{
	if(head==0)
	{
		return true;
	}
	return false;
}
void LinkedList::display()
{
	Node *temp=head;
	while(temp!=0)
	{
		cout<<temp->data<<" ";
		temp=temp->next;
	}
}
bool LinkedList::getvalue()
{
	if(head==0)
	{
		return true;
	}
	return false;
}
void LinkedList::insertEnd(unsigned long long val)
{
	Node *temp=new Node;
	temp->data=val;
	temp->next=0;
	Node * curr=head;
	if(curr!=0)
	{
		while (curr->next!=0)
		{
			curr=curr->next;
		}
		curr->next=temp;
	}
	else
		head=temp;
}

LinkedList::LinkedList(const LinkedList &	ref)
{
	if (ref.head)
	{
		head = new Node;
		head->data = ref.head->data;

		Node * o = ref.head->next;
		Node * temp = head;
		while (o!=0)
		{
			temp->next = new Node;
			temp = temp->next;
			temp->data = o->data;
			o = o->next;
		}
		temp->next = 0;
	}
	head = 0;
}
LinkedList::~LinkedList()
{
	Node *t1=head;
	Node *t2=0;
	while(t1!=0)
	{
		t2=t1->next;
		delete t1;
		t1=t2;
	}
	head=0;
}
LinkedList LinkedList::operator=(LinkedList& val)
{
	if (val.head==0)
	{
		head=0;
	}
	head=val.head;
	return *this;
}

void LinkedList::displayRecursively(Node * t)
{
	if(t!=0)
	{
		cout<<t->data<<" ";
		displayRecursively(t->next);
	}
}

void LinkedList::displayRecursiveCall()
{
	displayRecursively(head);
}

int LinkedList::findRecursively(Node * t)
{
	if(t->next==0)
		return t->data;
	else if(head==0)
		return head->data;
	else
	{
		int largest=t->data;
		int lar=findRecursively(t->next);
		if(largest<lar)
			largest=lar;
		return largest;
	}
}

void LinkedList::insertStart(unsigned long long val)
{
	Node *temp=new Node;
	temp->data=val;
	temp->next=head;
	head=temp;
}
int LinkedList::findRecursiveCall()
{
	return findRecursively(head);
}
//void LinkedList::combine(LinkedList& list1, LinkedList& list2)
//{
//	this->head = list1.head;
//	list1.head = 0;
//	Node * curr = head;
//	while (curr->next!=0)
//	{
//		curr = curr->next;
//	}
//	curr->next = list2.head;
//	list2.head = 0;
//}
//void LinkedList::shuffleMerge(LinkedList& l1, LinkedList& l2)
//{
//	head = l1.head;
//	l1.head = 0; 
//	Node * curr1 = head;
//	Node * curr2 = l2.head;
//	Node * temp = 0;
//	Node * temp2 = 0;
//	l2.head = 0;
//
//	while (curr1!=0)
//	{
//		temp = curr1->next;
//		curr1->next = curr2;
//		temp2 = curr2->next;
//		curr2->next = temp;
//		curr1 = temp;
//		curr2 = temp2;
//	}
//
//}
//void LinkedList::swapp(Node * val1, Node * val2)
//{
//	Node * temp = val1;
//	val1 = val2;
//	val2 = temp;
//}