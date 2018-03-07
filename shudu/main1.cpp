#include<iostream>
#include<cstdlib>
#include<cstdio>
#include<ctime>
#include<vector>
#include<fstream>
using namespace std;

/*����˼��
���û��ݷ���������ģ�壬����ģ���㷨�������յ���������
���ַ����ŵ����ٶȿ죬����ȱ���ǵ�ÿ��ģ�����ɵ�����������࣬��GRAND������ܿ��ܻ�����ظ���
������GRANDȡ30�������˻��ݷ�ʹ�õĴ����������Ч�ʣ�����Ϊ0.0744%�������Ҫ����100���������ƽ���ظ���������Ϊ744����
�޸���num[0][0]�ĸ�ֵ��ֻ���ڿ�ʼ����ʱ��ֵ���������ڱ�����ʱ���ж��Ƿ��λ��Ϊ[0][0]�����Ч�ʣ���������ʱ��
*/

const int SIZE = 9;
const int GRAND = 30;
/*
ÿ��������������Ĺ�ģΪGRAND����GRANDԽ����������������ظ����ʾ�Խ�ߣ�
��GRANDԽС����������������ظ����ʾ�Խ�͡�
����GRANDȡ30�������㣬�ظ�����Ϊ0.0744%�������Ҫ����100���������ƽ���ظ���������Ϊ744����
*/
vector<vector <int> > num(SIZE, vector<int>(SIZE));
char model[9][9];
int temp[9];
int sum;


//IsS IsSu;
//Gen Gene;
ofstream ocout;//�����������һ������


bool IsSuitable(int row, int col) {
	int n = num[row][col];
	for (int i = 0; i < row; i++) {
		if (num[i][col] == n) return false;
	}   												//�ж�n�Ƿ��Ѿ����������� 
	for (i = 0; i < col; i++) {
		if (num[row][i] == n) return false;
	}  													//�ж�n�Ƿ��Ѿ����������� 
	int RowStart = row / 3;
	RowStart *= 3;
	int RowEnd = RowStart + 2;
	int ColStart = col / 3;
	ColStart *= 3;
	int ColEnd = ColStart + 2;
	i = RowStart;
	int j = ColStart;  					//RowStart,RowEnd,ColStart,ColEnd��־��λ�����ڵľŹ������ʼ	

	for (int k = 1; k <= 8; k++) {
		if (row != i || col != j) {
			if (num[i][j] == n) return false;
		}
		else  break;                                    //�б����������Ƿ���Ź����е����ִ��ڳ�ͻ 
		if (j == ColEnd) {
			j = ColStart;
			i++;
		}
		else {
			j++;
		}
	}  return true;
}  																	//�б�������ڸ�λ���Ƿ���� 

bool generate(int row, int col) {
	int nextrow, nextcol;
	vector<int> number;
	//if (row == 0 && col == 0) 
	//	number.push_back((3 + 2) % 9 + 1);			//Ӧ��ҵҪ�󣬾����һ��λ�ù̶�Ϊ6 
	//else
	for (int i = 1; i <= 9; i++)
		number.push_back(i);										//��1-9װ������ 
	while (!number.empty()) {
		int randindex = rand() % number.size();  				//�������1-9��� 1 �� ����num
		int randnum = number[randindex];
		number.erase(number.begin() + randindex);  				//ɾ������λ�õ����� 
		num[row][col] = randnum;  								//���������ڵ�row�У���col�� 
		if (!IsSuitable(row, col))
			continue;  											//��� randnum��������number[row][col]���λ�ã������ѭ����һ�����ʵ��� 
		if (row == SIZE - 1 && col == SIZE - 1) {
			return true;
		}  														//��������½ǵĿ�Ҳ�����ˣ�����ture���ɹ�������������  
		if (col == SIZE - 1) {
			nextrow = ++row;
			nextcol = 0;
		}  														//���̽�������һ�У����� 
		else {
			nextrow = row;
			nextcol = ++col;
		}  														//nextrow��nextcolָ����һ���ո� 
		bool next = generate(nextrow, nextcol);						//�ݹ����������������  
		if (next)  return true; 								 	//������tureʱ ����ɹ�����
	}
	if (number.empty()) {

		return false;
	} 															//���ɵ�ʱ��ס�˱������һ�� 

}

void generate1to9() {
	for (int k = 0; k < 9; k++) {
		temp[k] = 0;
	}//��ʼ������ 

	for (int i = 0; i<9; i++) {
		temp[i] = 1 + rand() % 9;                //�õ������(��Χ��1-9֮��)
		for (int j = 0; j<i; j++)                 //�жϺ�ǰ������Ƿ��ظ�
			if (temp[i] == temp[j]) { i--; break; }  //����ظ�,���²��������
	}//����9�������

}
// �������1-9���ظ�����������temp������ 
void init()
{
	generate1to9();
	for (int i = 0; i<9; i++) {
		if (temp[i] == 6) {
			temp[i] = temp[8];
			temp[8] = 6;
		}
	}

}

void outputTocmd() {
	for (int i = 0; i < SIZE; i++) {
		for (int j = 0; j < SIZE; j++) {
			printf(" %d", num[i][j]);
			}
		printf("\n");
			}
	printf("\n");
}
//��ӡ�������Ļ
void outputTotext() {


	for (int i = 0; i < SIZE; i++) {
		for (int j = 0; j < SIZE; j++) {
			ocout << num[i][j] << " ";
		}
		ocout << "\n";
	}
	ocout << "\n";

}
//��ӡ������ļ�
void finalgenerator()
{
	for (int i = 0; i<9; i++)
	{
		for (int j = 0; j<9; j++)
		{
			if (model[i][j] == 'a') num[i][j] = temp[0];
			else if (model[i][j] == 'b') num[i][j] = temp[1];
			else if (model[i][j] == 'c') num[i][j] = temp[2];
			else if (model[i][j] == 'd') num[i][j] = temp[3];
			else if (model[i][j] == 'e') num[i][j] = temp[4];
			else if (model[i][j] == 'f') num[i][j] = temp[5];
			else if (model[i][j] == 'g') num[i][j] = temp[6];
			else if (model[i][j] == 'h') num[i][j] = temp[7];
			else if (model[i][j] == 'i') num[i][j] = temp[8];
		}
	}
}
int main(int agrc,char* agrv[]) {
	int CharJduge;
	clock_t start, finish;
	double totaltime;

	start = clock();
	int N;
	ocout.open("sudoku.txt");
	printf("��������Ҫ���ɵ������������:\n");
	scanf("%d", &N);
	CharJduge = N;
	srand((unsigned)time(NULL));
	sum = CharJduge;
	cout << "����������ɵ�" << N << "�����������������:" << endl;
	int count = 0;
	for (int i = 0; i < CharJduge / GRAND + 1; i++) {
		num[0][0] = 6;
		generate(0, 1); 													//��0,0λ�ÿ�ʼ������������������� 																	
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (num[x][y] == 6) model[x][y] = 'i';
				else if (num[x][y] == 1) model[x][y] = 'a';
				else if (num[x][y] == 2) model[x][y] = 'b';
				else if (num[x][y] == 3) model[x][y] = 'c';
				else if (num[x][y] == 4) model[x][y] = 'd';
				else if (num[x][y] == 5) model[x][y] = 'e';
				else if (num[x][y] == 7) model[x][y] = 'f';
				else if (num[x][y] == 8) model[x][y] = 'g';
				else if (num[x][y] == 9) model[x][y] = 'h';
			}
		}
		if (sum >= GRAND) {
			count = GRAND;
			sum = sum - GRAND;
		}
		else {
			count = sum;
			sum = 0;
		}
	}
		for (int z = 0; z < N; z++)
		{
			init();
			finalgenerator();
			outputTotext();
			outputTocmd();
		}
	
	
	
		
	
	ocout.close();

	finish = clock();

	totaltime = (double)(finish - start) / CLOCKS_PER_SEC;

	cout << "\n�˳��������ʱ��Ϊ" << totaltime<<endl;

	system("pause");

	return 0;
}