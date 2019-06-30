import java.util.*;

class bfs1
{
    int linha;
    int coluna;
    char inicio;
    bfs1(int linha, int coluna, char inicio)
    {
	this.linha = linha;
	this.coluna = coluna;
	this.inicio = inicio;
    }
}

class pacman
{
    public static boolean cond (char b) {
	if( b != '#' && b != 'H' && b != 'D')
	    return true;
	else
	    return false;
    }
    public static void main (String args[])
    {
	Scanner ler = new Scanner(System.in);
	
	int nlin = ler.nextInt();
	int ncol = ler.nextInt();
	ler.nextLine();
	char [][] lab = new char [nlin][ncol];
 
	for(int i=0; i<nlin; i++)
	    {
		String lerlab = ler.nextLine();
		for(int j=0; j<ncol; j++)
		    lab[i][j] = lerlab.charAt(j);
	    }
	//	ler.nextLine();
	int posy = ler.nextInt();
	int posx = ler.nextInt();
	int nfant = ler.nextInt();

	int [][] pfant = new int [500][2];

	for(int i=0; i<nfant; i++)
	    {
		int firstf = ler.nextInt();
		int seconf = ler.nextInt();
		pfant[i][0] = firstf;
		pfant[i][1] = seconf;
	    }
	    
	int tpu = ler.nextInt();
	int turno = ler.nextInt();

	int vidas = ler.nextInt();
	int ident = ler.nextInt();

	ler.nextLine();

	String dir = ler.next();

	int nfan = 0;

	///////////////////////////////Fantasmas\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	if(tpu < 3)
	    {
		for(int i=0; i<nfant; i++)
		    {
			lab[pfant[i][0]][pfant[i][1]] = '#';

			if(pfant[i][1]-1 >= 0 && cond(lab[pfant[i][0]][pfant[i][1]-1]))
			    lab[pfant[i][0]][pfant[i][1]-1] = '#';

			if(pfant[i][0]-1 >= 0 && cond(lab[pfant[i][0]-1][pfant[i][1]]))
			    lab[pfant[i][0]-1][pfant[i][1]] = '#';

			if(pfant[i][1]+1 < ncol && cond(lab[pfant[i][0]][pfant[i][1]+1]))
			    lab[pfant[i][0]][pfant[i][1]+1] = '#';

			if(pfant[i][0]+1 < nlin && cond(lab[pfant[i][0]+1][pfant[i][1]]))
			    lab[pfant[i][0]+1][pfant[i][1]] = '#';
		    }
	    }
	else
	    {
		for(int i=0; i<nfant; i++)
		    {
			for(int x=0; x<nlin; x++)
			    for(int j=0; j<ncol; j++)
				{
				    if(lab[pfant[i][0]][pfant[i][1]] != 'H' && lab[pfant[i][0]][pfant[i][1]] != 'D')
					{
					    lab[pfant[i][0]][pfant[i][1]] = 'F';
					    nfan++;
					}
				}  
		    }
		if(nfan != 0)
		    {
			for(int x=0; x<nlin; x++)
			    for(int j=0; j<ncol; j++)
				{
				    if(lab[x][j] == 'o') 
					lab[x][j]='.';
				    if(lab[x][j] == 'O') 
					lab[x][j]='.';
				}
		    }
	    }
	
	///////////////////////////////COMIDA\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	
	LinkedList <bfs1> bfs = new LinkedList <bfs1>();

	if(posy+1 < nlin)
	    {
		if(cond(lab[posy+1][posx])) //DOWN
		    bfs.add(new bfs1(posy+1,posx,'D'));
	    }
	else if(posy+1 >= nlin && cond(lab[0][posx]))
	    bfs.add(new bfs1(0,posx,'D'));

	if(posy-1 >= 0)
	    {
		if(cond(lab[posy-1][posx])) //UP
		    bfs.add(new bfs1(posy-1,posx,'U'));
	    }
	else if(posy-1 < 0 && cond(lab[nlin-1][posx]))
	    bfs.add(new bfs1(nlin-1,posx,'U'));

	if(posx-1 >= 0)
	    {
		if(cond(lab[posy][posx-1])) //LEFT
		    bfs.add(new bfs1(posy,posx-1,'L'));
	    }
	else if(posx-1 < 0 && cond(lab[posy][ncol-1]))
	    bfs.add(new bfs1(posy,ncol-1,'L'));

	if(posx+1 < ncol)
	    {
		if(cond(lab[posy][posx+1])) //RIGTH
		    bfs.add(new bfs1(posy,posx+1,'R'));
	    }
	else if(posx+1 >= ncol && cond(lab[posy][0]))
	    bfs.add(new bfs1(posy,0,'R'));


	while(true)
	    {
		if(bfs.size()==0)
		    System.out.println('N');
			
		bfs1 um = bfs.remove();

	        if(lab[um.linha][um.coluna] == 'o' || lab[um.linha][um.coluna] == 'O' || lab[um.linha][um.coluna] == 'F')
		    System.out.println(um.inicio);


		else
		    {
			lab[um.linha][um.coluna] = '#';
			if(um.linha+1 < nlin)
			    {
				if(cond(lab[um.linha+1][um.coluna])) //DOWN
				    bfs.add(new bfs1(um.linha+1,um.coluna,um.inicio));
			    }
			else if(um.linha+1 >= nlin && cond(lab[0][um.coluna]))
			    bfs.add(new bfs1(0,um.coluna,um.inicio));
			
			if(um.linha-1 >= 0)
			    {
				if(cond(lab[um.linha-1][um.coluna])) //UP
				    bfs.add(new bfs1(um.linha-1,um.coluna,um.inicio));
			    }
		    
			else if(um.linha-1 < 0 && cond(lab[nlin-1][um.coluna]))
			    bfs.add(new bfs1(nlin-1,um.coluna,um.inicio));
			
			if(um.coluna-1 >= 0)
			    {
				if(cond(lab[um.linha][um.coluna-1])) //LEFT
				    bfs.add(new bfs1(um.linha,um.coluna-1,um.inicio));
			    }
			else if(um.coluna-1 < 0 && cond(lab[um.linha][ncol-1]))
			    bfs.add(new bfs1(um.linha,ncol-1,um.inicio));
			
			if(um.coluna+1 < ncol)
			    {
				if(cond(lab[um.linha][um.coluna+1])) //RIGTH
				    bfs.add(new bfs1(um.linha,um.coluna+1,um.inicio));
			    }
			else if(um.coluna+1 >= ncol && cond(lab[um.linha][0]))
			    bfs.add(new bfs1(um.linha,0,um.inicio));

		    
		    }

	    }
    
		   

    }
}
