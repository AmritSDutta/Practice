import java.util.*;

public class PermutationWithRepetition {

    static  int count=0;
    static Set<LinkedList<Integer>> masterList = new HashSet<>();

    public static void main(String args[])
    {
        int[] ar = {1,1,1,1};
        HashMap<Integer,Integer> countingMap;
        countingMap= prepare(ar);

        System.out.println("Permute WITHOUT Set in use : ");

        permuteWithOutUsingSet(countingMap,null);
        System.out.println("Set : \n"+masterList);
        System.out.println(count);

        System.out.println("\n\nPermute With Set in use : ");

        permuteUsingSet(countingMap,null);
        System.out.println("Set : \n"+masterList);
        System.out.println("Number of Permutation : "+masterList.size());

    }

    static HashMap<Integer,Integer> prepare(int arr[])
    {
        HashMap<Integer,Integer> countingMAp= new HashMap<>();
        for(int i : arr)
        {
            if(countingMAp.containsKey(i))
            {
                countingMAp.put(i,countingMAp.get(i)+1);
                continue;
            }
            countingMAp.put(i,1);
        }
        return countingMAp;
    }

    static void permuteWithOutUsingSet(final HashMap<Integer,Integer> countingMap,LinkedList<Integer> alreadySeen)
    {
        if(countingMap.isEmpty())
            return;

        if(alreadySeen == null)
            alreadySeen=new LinkedList<>();

        if(countingMap.size()==1 && countingMap.get(countingMap.keySet().iterator().next()) ==1)
        {
                alreadySeen.addAll(countingMap.keySet());
                System.out.println(alreadySeen);
                count++;
                return;
        }

        for (Map.Entry<Integer, Integer> pair : countingMap.entrySet()) {
            int target = pair.getKey();
            int targetCount = pair.getValue();
            List<Integer> localAlreadySeen= new ArrayList<>();
            for(int i=1;i<=targetCount;i++)
            {
                if(alreadySeen.peekLast() != null && alreadySeen.peekLast()== target)
                    continue;


                 localAlreadySeen.add(target);

                HashMap<Integer,Integer> clonedMap = (HashMap<Integer, Integer>) countingMap.clone();

                int newTargetCount =targetCount - i;
                if(newTargetCount == 0)
                    clonedMap.remove(target);
                else {
                    clonedMap.put(target, newTargetCount);
                }
                LinkedList<Integer> alreadySeenCloned = (LinkedList<Integer>) alreadySeen.clone();
                alreadySeenCloned.addAll(localAlreadySeen);
                permuteWithOutUsingSet(clonedMap,alreadySeenCloned);
            }
        }
    }


    static void permuteUsingSet(final HashMap<Integer,Integer> countingMap,LinkedList<Integer> alreadySeen)
    {
        if(countingMap.isEmpty())
            return;

        if(alreadySeen == null)
            alreadySeen=new LinkedList<>();

        if(countingMap.size()==1 && countingMap.get(countingMap.keySet().iterator().next()) ==1)
        {
            alreadySeen.addAll(countingMap.keySet());
            masterList.add(alreadySeen);
            return;
        }

        for (Map.Entry<Integer, Integer> pair : countingMap.entrySet()) {
            int target = pair.getKey();
            int targetCount = pair.getValue();
            List<Integer> localAlreadySeen= new ArrayList<>();
            for(int i=1;i<=targetCount;i++)
            {
                if(alreadySeen.peekLast() != null && alreadySeen.peekLast()== target)
                    continue;


                localAlreadySeen.add(target);

                HashMap<Integer,Integer> clonedMap = (HashMap<Integer, Integer>) countingMap.clone();

                int newTargetCount =targetCount - i;
                if(newTargetCount == 0)
                    clonedMap.remove(target);
                else {
                    clonedMap.put(target, newTargetCount);
                }
                LinkedList<Integer> alreadySeenCloned = (LinkedList<Integer>) alreadySeen.clone();
                alreadySeenCloned.addAll(localAlreadySeen);
                permuteUsingSet(clonedMap,alreadySeenCloned);
            }
        }
    }
}
