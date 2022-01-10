#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <cs50.h>

//Defining byte
typedef uint8_t BYTE;

int main(int argc, char *argv[])
{
    //Error due to Invalid entry in terminal
    if (argc !=2)
    {
        printf("USAGE: ./recover filename \n");
        return 1;
    }
    
    //opening file
    FILE *fr=fopen(argv[1],"r");
    
    //Handling If error occurs during file open
    if (fr==NULL)
    {
        printf("File cannot be opened.\n");
        return 1;
    }
    
    //Finding total size of the file provided
    int res=0;
    char ch;
    while((ch=fgetc(fr))!=EOF)
    {
        res++;
    }
    //File closed 
    fclose(fr);
    
    //File opened again to reset file stream to beginning of the file
    FILE *fq=fopen(argv[1],"r");
    
    //allocating memory for filename string 
    string filename=malloc(41*(sizeof(char)));
    
    int numbering=-1;
    //Allocating memory for buffer
    BYTE buff[res];
    
    while(fread(buff, sizeof(BYTE), 512, fq))
    {
        //beginning of new file
        if ((buff[0]==0xff) && (buff[1]==0xd8) && (buff[2]==0xff) && ((buff[3] & 0xf0)==0xe0))
        {
            numbering++;
            sprintf(filename, "%03i.jpg", numbering);
            FILE *fn=fopen(filename,"w");
            fwrite(buff, sizeof(BYTE), 512, fn);
            fclose(fn);
        }
        //continuing writing to current file
        else
        {
            sprintf(filename, "%03i.jpg", numbering);
            FILE *fe=fopen(filename,"a");
            fwrite(buff, sizeof(BYTE), 512, fe);
            fclose(fe);
        }
    }
    //Free used resources to prevent memory leak
    free(filename);
    //close used file
    fclose(fq);
    //Since the first jpg maynot be at the very beginning of the file, the first few bytes would be written to a file called -01.jpg due to code implementation. So the file has been removed.
    remove("-01.jpg");
    
    return 0;
    
}