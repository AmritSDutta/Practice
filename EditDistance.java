public class EditDistance {
    static int[][] memory;

    public static void main(String[] args) {
        System.out.println(getEditDistance("abc","abc"));

    }

    static int getEditDistance(String a, String b)
    {
        if(a.length() == 0) return b.length();
        if(b.length() == 0 ) return a.length();
        memory =new int[a.length()][b.length()];
        for(int i = 0 ;i < a.length();i++)
            for(int j =0; j< b.length();j++ )
                memory[i][j] =-1;
        return findEditDistance(a.toCharArray(),0,b.toCharArray(),0);
    }

    static int findEditDistance(char[] a, final int indexA, char[] b,final int indexB)
    {
        if(indexA == a.length || indexB == b.length )
            return Math.abs((a.length-indexA) - (b.length -indexB));

        if(memory[indexA][indexB] != -1)
        {
            return memory[indexA][indexB];
        }

        if(a[indexA] == b[indexB])
        {
            memory[indexA][indexB] = findEditDistance(a,indexA+1, b, indexB+1);
        }
        else{
            memory[indexA][indexB] = 1+ Math.min(Math.min( findEditDistance(a,indexA+1, b, indexB),
                    findEditDistance(a,indexA, b, indexB+1)),
                    findEditDistance(a,indexA+1, b, indexB+1));
        }


        return memory[indexA][indexB];
    }
}
