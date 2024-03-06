package pj03;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SortedDoubleLinkedList_STUDENT_Test {

    SortedDoubleLinkedList<MusicGroup> linkedGroup;
    GroupComparator comparator;

    // Sample music groups
    public MusicGroup musicGroup1 = new MusicGroup("PNL", "Rap", 2015);
    public MusicGroup musicGroup2 = new MusicGroup("The Twins", "RnB", 2002);
    public MusicGroup musicGroup3 = new MusicGroup("The Who", "Rock", 1964);
    public MusicGroup musicGroup4 = new MusicGroup("The Doors", "Rock", 1965);
    public MusicGroup musicGroup5 = new MusicGroup("Les freres", "Rock", 1999);

    
   
    @Before
    public void setUp() throws Exception {
        // Setting up the linked list and comparator
        comparator = new GroupComparator();
        linkedGroup = new SortedDoubleLinkedList<MusicGroup>(comparator);
    }

    @After
    public void tearDown() throws Exception {
        // Clean up
        linkedGroup = null;
        comparator = null;
    }

    @Test
    public void testAddToEnd() {
        // Testing the add to end method
        try {
            linkedGroup.addToEnd(musicGroup2);
            assertTrue("Did not throw UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw " + e + " when it should have thrown UnsupportedOperationException", false);
        }
    }

    @Test
    public void testAddToFront() {
        // Testing the add to front method
        try {
            linkedGroup.addToFront(musicGroup2);
            assertTrue("Did not throw UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw " + e + " when it should have thrown UnsupportedOperationException", false);
        }
    }

    @Test
    public void testIteratorSuccessfulNext() {
        // Testing iterator for successful next
        linkedGroup.add(musicGroup1);
        linkedGroup.add(musicGroup2);
        linkedGroup.add(musicGroup3);
        linkedGroup.add(musicGroup4);
        linkedGroup.add(musicGroup5);
        ListIterator<MusicGroup> iterator = linkedGroup.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup5, iterator.next());
        assertEquals(musicGroup1, iterator.next());
        assertEquals(musicGroup4, iterator.next());
        assertEquals(musicGroup2, iterator.next());
        assertEquals(true, iterator.hasNext());
    }

    @Test
    public void testIteratorSuccessfulPrevious() {
        // Testing iterator for successful previous
        linkedGroup.add(musicGroup1);
        linkedGroup.add(musicGroup2);
        linkedGroup.add(musicGroup3);
        linkedGroup.add(musicGroup4);
        linkedGroup.add(musicGroup5);
        ListIterator<MusicGroup> iterator = linkedGroup.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup5, iterator.next());
        assertEquals(musicGroup1, iterator.next());
        assertEquals(musicGroup4, iterator.next());
        assertEquals(musicGroup2, iterator.next());
        assertEquals(musicGroup3, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(musicGroup3, iterator.previous());
        assertEquals(musicGroup2, iterator.previous());
        assertEquals(musicGroup4, iterator.previous());
        assertEquals(musicGroup1, iterator.previous());
        assertEquals(musicGroup5, iterator.previous());
        assertEquals(false, iterator.hasPrevious());
    }

    @Test
    public void testIteratorNoSuchElementExceptionNext() {
        // Testing NoSuchElementException for next
        linkedGroup.add(musicGroup1);
        linkedGroup.add(musicGroup2);
        linkedGroup.add(musicGroup3);
        linkedGroup.add(musicGroup4);
        linkedGroup.add(musicGroup5);
        ListIterator<MusicGroup> iterator = linkedGroup.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup5, iterator.next());
        assertEquals(musicGroup1, iterator.next());
        assertEquals(musicGroup4, iterator.next());
        assertEquals(musicGroup2, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup3, iterator.next());
        try {
            iterator.next();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorNoSuchElementExceptionPrevious() {
        // Testing NoSuchElementException for previous
        linkedGroup.add(musicGroup1);
        linkedGroup.add(musicGroup2);
        linkedGroup.add(musicGroup3);
        linkedGroup.add(musicGroup4);
        linkedGroup.add(musicGroup5);
        ListIterator<MusicGroup> iterator = linkedGroup.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup5, iterator.next());
        assertEquals(musicGroup1, iterator.next());
        assertEquals(musicGroup4, iterator.next());
        assertEquals(musicGroup2, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup3, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(musicGroup3, iterator.previous());
        assertEquals(musicGroup2, iterator.previous());
        assertEquals(musicGroup4, iterator.previous());
        assertEquals(musicGroup1, iterator.previous());
        assertEquals(musicGroup5, iterator.previous());
        try {
            iterator.previous();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorUnsupportedOperationException() {
        // Testing UnsupportedOperationException for iterator
        linkedGroup.add(musicGroup1);
        linkedGroup.add(musicGroup2);
        linkedGroup.add(musicGroup3);
        linkedGroup.add(musicGroup4);
        linkedGroup.add(musicGroup5);
        ListIterator<MusicGroup> iterator = linkedGroup.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup5, iterator.next());
        assertEquals(musicGroup1, iterator.next());
        assertEquals(musicGroup4, iterator.next());
        assertEquals(musicGroup2, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup3, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(musicGroup3, iterator.previous());
        assertEquals(musicGroup2, iterator.previous());
        assertEquals(musicGroup4, iterator.previous());
        assertEquals(musicGroup1, iterator.previous());
        assertEquals(musicGroup5, iterator.previous());
        try {
            iterator.remove();
            assertTrue("Did not throw a UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw a UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testRemove() {
        // Testing removal of elements
        linkedGroup.add(musicGroup1);
        linkedGroup.add(musicGroup2);
        linkedGroup.add(musicGroup3);
        linkedGroup.add(musicGroup4);
        linkedGroup.add(musicGroup5);
        assertEquals(5, linkedGroup.getSize());
        linkedGroup.remove(musicGroup1, comparator);
        assertEquals(4, linkedGroup.getSize());
        linkedGroup.remove(musicGroup5, comparator);
        assertEquals(3, linkedGroup.getSize());
        linkedGroup.remove(musicGroup3, comparator);
        assertEquals(2, linkedGroup.getSize());
        linkedGroup.remove(musicGroup2, comparator);
        assertEquals(1, linkedGroup.getSize());
        linkedGroup.remove(musicGroup4, comparator);
        assertEquals(0, linkedGroup.getSize());
    }




    @Test
    public void testRetrieveFirstElement() {
        linkedGroup.add(musicGroup1);
        linkedGroup.add(musicGroup4);
        linkedGroup.add(musicGroup5);
        linkedGroup.add(musicGroup2);
        linkedGroup.add(musicGroup3);
        assertEquals(musicGroup5, linkedGroup.getFirst());
    }

    @Test
    public void testRetrieveLastElement() {
        linkedGroup.add(musicGroup1);
        linkedGroup.add(musicGroup4);
        linkedGroup.add(musicGroup5);
        linkedGroup.add(musicGroup2);
        linkedGroup.add(musicGroup3);
        assertEquals(musicGroup3, linkedGroup.getLast());
    }

    @Test
    public void testAddGroup(){
        linkedGroup.add(musicGroup1);
        linkedGroup.add(musicGroup2);
        linkedGroup.add(musicGroup3);
        linkedGroup.add(musicGroup4);
        linkedGroup.add(musicGroup5);
        assertEquals(musicGroup5, linkedGroup.getFirst());
        assertEquals(musicGroup3, linkedGroup.getLast());
        assertEquals(5, linkedGroup.getSize());

    }



    private class GroupComparator implements Comparator<MusicGroup> {

        @Override
        public int compare(MusicGroup first, MusicGroup second) {
          return first.getName().compareTo(second.getName());
        }
      }  


    private class MusicGroup{
		String name;
		String music;
		int year;
		
		public MusicGroup(String name, String music, int year){
			this.name = name;
            this.music = music;
            this.year = year;
        }
		
		public String getName(){
			return name;
		}
		public String getMusic(){
			return music;
		}
		public int getYear(){
			return year;
		}
		
		public String toString() {
			return (getName()+" "+getMusic()+" "+getYear());
		}
	}
    
}
