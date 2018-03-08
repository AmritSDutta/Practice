package com.trs.estimation;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class provides implementation of lock for items which are Set<String>
 *     for example , lets next 3 request comes almost same time-
 *     1) {1,2,3}
 *     2){3,4,5}
 *     3){7,8,9}
 *
 *     Then 3rd req will always acquire lock , but
 *     only one of the 1 and 2 will get the lock others have
 *     to wait for the set which got the lock first time.
 *
 *     End result :
 *     MultiSet lock algorithm should have following properties
 *     1) Mutual Exclusion
 *     2) Progress
 *     3) Deadlock freedom
 *     4) Indefinite wait - not addressed through this algorithm.
 */
public final class MultiSetLock_V1 {

    private static Map<String, ReentrantReadWriteLock> allLocks = new ConcurrentHashMap<>();
    private static final ReentrantReadWriteLock allLocksProtector = new ReentrantReadWriteLock();
    private final Set<String> allItemSet;
    private static volatile int lockCount = 0;


    public MultiSetLock_V1(Set<String> allItemSet) {
        this.allItemSet = new TreeSet<>(allItemSet);
    }

    /**
     * Algorithm summary:
     * 1) check if any member element of the set is locked by other thread. (Under global consensus)
     * 2) if yes, wait for the that (those) lock. (No global consensus)
     * 3) check once more before issuing locks , as step 2 is not under global consensus.(Under global consensus)
     * 4) if no other thread holding lock, actually create new lock , add it to global map, lock it, and return. (Under global consensus)
     * 5) if step 3 finds others thread interventions, restart the lock algorithm again.
     *
     *
     * Note : all locks are checked in certain order , thus it is deadlock-safe. (for details check getExistingLocks())
     */
    public void lock() {
        //Needs certain order to examine the locks , otherwise leads to deadlock, thus LinkedHashMap.
        LinkedHashSet<ReentrantReadWriteLock> existingLock = getExistingLocks();
        if (existingLock != null && !existingLock.isEmpty()) {
            //wait if all or some of the elements under lock protection.
            waitForExistingLocks(existingLock);
        }

        allLocksProtector.writeLock().lock();
        try {
            //re-examine lock status, before issuing newer locks
            existingLock = getExistingLocks();
            if (existingLock == null || existingLock.isEmpty()) {
                allocateLocks();
                return;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            allLocksProtector.writeLock().unlock();
        }

        /*
        should not have reached this line under normal circumstances,
        only if locks are retaken by other threads then only come to this line.
        this means begin the journey to acquire lock again.
        */
        this.lock();
    }

    public void unlock() {
        Set<ReentrantReadWriteLock> existingLock = new HashSet<>();

        allLocksProtector.writeLock().lock();
        try {
            for (String item : this.allItemSet) {
                ReentrantReadWriteLock oneOfExistingLock = allLocks.get(item);
                if (oneOfExistingLock != null)
                    existingLock.add(oneOfExistingLock);
                allLocks.remove(item);
            }

            for (ReentrantReadWriteLock lock : existingLock) {
                lock.writeLock().unlock();
            }

            lockCount--;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            allLocksProtector.writeLock().unlock();
        }
    }

    /**
     * Order of the locks are according to the sorted order of the elements of set (subjects of locking)
     * @return
     */
     private LinkedHashSet<ReentrantReadWriteLock> getExistingLocks() {
        LinkedHashSet<ReentrantReadWriteLock> existingLock = null;
        allLocksProtector.readLock().lock();
        try {
            boolean lockAlreadyHeld = this.allItemSet.stream().anyMatch(item -> allLocks.containsKey(item));
            if (lockAlreadyHeld) {
                existingLock = new LinkedHashSet<>();
                for (String item : this.allItemSet) {
                    ReentrantReadWriteLock oneOfExistingLock = allLocks.get(item);
                    if (oneOfExistingLock != null)
                        existingLock.add(oneOfExistingLock);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            allLocksProtector.readLock().unlock();
            return existingLock;
        }
    }

    private void allocateLocks() {
        allLocksProtector.writeLock().lock();
        try {
            ReentrantReadWriteLock newLock = new ReentrantReadWriteLock();
            newLock.writeLock().lock();
            for (String item : this.allItemSet) {
                allLocks.put(item, newLock);
            }
            lockCount++;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            allLocksProtector.writeLock().unlock();
        }
    }

    private void waitForExistingLocks(final Set<ReentrantReadWriteLock> existingLock) {
        for (ReentrantReadWriteLock lock : existingLock) {
            try {
                lock.writeLock().lock();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    /**
     * Only should be used for verification of lock algorithm.
     * @return
     */
    public static int getLockCount() {
        return lockCount;
    }
}
