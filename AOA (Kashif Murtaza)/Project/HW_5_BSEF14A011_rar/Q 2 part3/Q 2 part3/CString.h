#ifndef CSTRING_H
#define CSTRING_H
#include<iostream>
using namespace std;
class CString
{
public:
	char * data;
	int size;
	CString();
	CString(const char c);
	CString(const char * const);
	~CString();
	int isEmpty() const;
	void copy(char * const dest, const char *  const source);
	int getLength(const char * const str) const;
	int getLength() const;
	bool isValidIndex(int i) const;
	int find(const char *const substr, const int start = 0)const;
	int find(const char ch, const int start = 0)const;
	int insert(const int index, const char* const substr);
	int insert(const int index, const char ch);
	int remove(const int index, const int count = 1);
	int remove(const char ch);
	void replace(const char newC);
	int replace(const char old, const char newC);
	int replace(const char*const  old, const char* const newC);
	void trim();
	void trimLeft();
	void trimRight();
	void makeUpper();
	void makeLower();
	void reverse();
	void reSize(const int add);
	CString(const CString &);
	CString left(int count);
	CString right(int count);
	void rightTrim();
	void resize(const int ns);
	void leftTrim();
	char & operator [](const int index);//char & at( const int index);
	const char & operator [](const int index)const;//const char & at( const int index) const;
	void operator +=( const char * const s2 );//void CString :: concatEqual(const char * const s2)
	void operator +=(const CString& s2);//void concatEqual( const CString& s2 );
	int operator ==(const CString& s2);//int isEqual(const CString & s2 )const  ;
	int operator ==(const char *const s2);//int isEqual(const char *const s2 ) ;
	CString operator + (const CString& s2)const;//CString concat( const CString& s2 ) const  ;
	CString operator +(const char * const s2)const;//	CString concat( const char * const s2 )  const;
	CString operator () (const char * const delim);  //CString tokenzie( const char * const delim ); 
	friend istream & operator >> (istream &,  CString &); //void input();
	friend ostream & operator << (ostream &, CString &);//void display()const;
	void display()const;
};
#endif