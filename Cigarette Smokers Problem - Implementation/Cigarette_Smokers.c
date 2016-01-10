#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define SEMAPHORE0 0 //semaphore for tobacco and matches
#define SEMAPHORE1 1 //semaphore for matches and paper
#define SEMAPHORE2 2 //semaphore for paper and matches

int process=1,semaphore_val[3];
char semaphore[4][30];

enum ingredients
{ paper,tobacco,matches,none };// paper=0 , tobacco=1 , matches=2 , none=3

typedef struct smoker
{
	char smoker_no[50];
	int item;
}Smoker;

typedef struct agent
{
	char agent_name[50];
	int item1,item2;
}Agent;

char *GetSelectedIngredients(int item_num)
{
	if(item_num==paper)
	return "Paper";
	else if(item_num==tobacco)
	return "Tobacco";
	else if(item_num==matches)
	return "Matches";
}

void GetAgentIngredientsOnTable(Agent *agent)
{
	agent->item1=random()%3;
	while(1)
	{
		agent->item2=random()%3;
		if( agent->item1!=agent->item2 )
		break;
	}
	printf("process-%d\n",process);process++;
	printf("Agent provides \"%s\" and \"%s\" ingredients\n",GetSelectedIngredients(agent->item1),GetSelectedIngredients(agent->item2) );
}

void SetSemaphore() // set the values for semaphore
{
	int i;
	char *str1,*str2,*temp;
	semaphore_val[0]=SEMAPHORE0;
	semaphore_val[1]=SEMAPHORE1;
	semaphore_val[2]=SEMAPHORE2;

	str1=(char *)malloc(30*sizeof(char));
	str2=(char *)malloc(30*sizeof(char));
	temp=(char *)malloc(30*sizeof(char));

	for(i=0;i<3;i++)
	{
		strcpy(str1,GetSelectedIngredients((i+1)%3) );
		strcpy(str2,GetSelectedIngredients((i+2)%3));
		strcat(str1," and ");
		strcpy(semaphore[i],strcat(str1,str2));
	}
}

char *getSemaphore(int item)
{
	if(item==SEMAPHORE0)
	{
		return semaphore[item];
	}
	else if(item==SEMAPHORE1)
	{
		return semaphore[item];
	}
	else if(item==SEMAPHORE2)
	{
		return semaphore[item];
	}
}
void GiveIngredientsToSmoker( Agent *agent,Smoker *smoker)
{
	int i=0;
	while(smoker[i].smoker_no!=NULL)
	{
		if( (smoker[i].item != agent->item1) && (smoker[i].item != agent->item2) )
		{
			printf(" smoker who is having \"%s\" has been selected\n",smoker[i].smoker_no);
			printf(" smoker rolls his cigarette by taking %s from the agent\n SMOKING ====~~\n",getSemaphore(semaphore_val[i]) );
			printf("\n");
			agent->item1=none;
			agent->item2=none;
			break;
		}
		i++;
		if(i==3)
		{
			printf("error happened \n");
		}
	}		
}
void main()
{
	Agent agent;
	Smoker smoker[4]={ {"Paper",paper},{ "Tobacco",tobacco },{ "Matches",matches },{"\0",none} };
	int flag=0;

	strcpy(agent.agent_name,"Agent");
	agent.item1=none;agent.item2=none;
	//setting the values for semaphore

	SetSemaphore();
	system("clear");
	//loop forever
	
	while(1)
	{
		GetAgentIngredientsOnTable(&agent);
		GiveIngredientsToSmoker(&agent,smoker);
		sleep(5);
		printf("enter SPACE to exit , any key to cont. \n");
		flag=getchar();		
		if(flag==32)
		break ;
	}
}
