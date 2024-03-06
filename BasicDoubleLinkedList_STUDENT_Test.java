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


public class BasicDoubleLinkedList_STUDENT_Test {

    BasicDoubleLinkedList<MusicGroup> linkedGroup;
    GroupComparator comparator;

    public MusicGroup musicGroup1= new MusicGroup("PNL", "Rap", 2015);
    public MusicGroup musicGroup2 = new MusicGroup("The Twins", "RnB", 2002);
    public MusicGroup musicGroup3 = new MusicGroup("The Who", "Rock", 1964);
    public MusicGroup musicGroup4 = new MusicGroup("The Doors", "Rock", 1965);
    public MusicGroup musicGroup5 = new MusicGroup("Les freres", "Rock", 1999);

    @Before
    public void setUp() throws Exception{
        linkedGroup = new BasicDoubleLinkedList<MusicGroup>();
        linkedGroup.addToEnd(musicGroup1);
        linkedGroup.addToEnd(musicGroup2);
        linkedGroup.addToEnd(musicGroup3);
        comparator = new GroupComparator();
    }

    @After
    public void tearDown() throws Exception{
        linkedGroup = null;
        comparator = null;
    }

    @Test
    public void testGetSize() {
        assertEquals(3,linkedGroup.getSize());
    }

    @Test
    public void testAddToEnd() {
        assertEquals(musicGroup3,linkedGroup.getLast());
        linkedGroup.addToEnd(musicGroup4);
        assertEquals(musicGroup4,linkedGroup.getLast());
    }

    @Test
    public void testAddToFront() {
        assertEquals(musicGroup1,linkedGroup.getFirst());
        linkedGroup.addToFront(musicGroup5);
        assertEquals(musicGroup5,linkedGroup.getFirst());
    }

    @Test
    public void testGetFirst() {
        assertEquals(musicGroup1,linkedGroup.getFirst());
        linkedGroup.addToFront(musicGroup5);
        assertEquals(musicGroup5,linkedGroup.getFirst());
    }

    @Test
    public void testGetLast() {
        assertEquals(musicGroup3,linkedGroup.getLast());
        linkedGroup.addToEnd(musicGroup4);
        assertEquals(musicGroup4,linkedGroup.getLast());
    }

    @Test
    public void testToArrayList() {
        ArrayList<MusicGroup> list = linkedGroup.toArrayList();
        assertEquals(musicGroup1,list.get(0));
        assertEquals(musicGroup2,list.get(1));
        assertEquals(musicGroup3,list.get(2));
    }

    @Test
    public void testIteratorSuccessfulNext() {
        linkedGroup.addToFront(musicGroup5);
        ListIterator<MusicGroup> iterator = linkedGroup.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup5, iterator.next());
        assertEquals(musicGroup1, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup2, iterator.next());
        assertEquals(musicGroup3, iterator.next());
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testIteratorSuccessfulPrevious() {
        linkedGroup.addToFront(musicGroup5);
        ListIterator<MusicGroup> iterator = linkedGroup.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup5, iterator.next());
        assertEquals(musicGroup1, iterator.next());
        assertEquals(musicGroup2, iterator.next());
        assertEquals(musicGroup3, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(musicGroup3, iterator.previous());
        assertEquals(musicGroup2, iterator.previous());
        assertEquals(musicGroup1, iterator.previous());
        assertEquals(musicGroup5, iterator.previous());
        assertEquals(false, iterator.hasPrevious());
    }

    @Test
    public void testIteratorNoSuchElementExceptionNext() {
        linkedGroup.addToFront(musicGroup5);
        ListIterator<MusicGroup> iterator = linkedGroup.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup5, iterator.next());
        assertEquals(musicGroup1, iterator.next());
        assertEquals(musicGroup2, iterator.next());
        assertEquals(musicGroup3, iterator.next());
        assertEquals(false, iterator.hasNext());
        
        try{
            iterator.next();
            assertTrue("Did not throw a NoSuchElementException",false);
        }
        catch (NoSuchElementException e){
            assertTrue("Successfully threw a NoSuchElementException",true);
        }
        catch (Exception e){
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorNoSuchElementExceptionPrevious() {
        linkedGroup.addToFront(musicGroup5);
        ListIterator<MusicGroup> iterator = linkedGroup.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup5, iterator.next());
        assertEquals(musicGroup1, iterator.next());
        assertEquals(musicGroup2, iterator.next());
        assertEquals(musicGroup3, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(musicGroup3, iterator.previous());
        assertEquals(musicGroup2, iterator.previous());
        assertEquals(musicGroup1, iterator.previous());
        assertEquals(musicGroup5, iterator.previous());
        assertEquals(false, iterator.hasPrevious());
        
        try{
            iterator.previous();
            assertTrue("Did not throw a NoSuchElementException",false);
        }
        catch (NoSuchElementException e){
            assertTrue("Successfully threw a NoSuchElementException",true);
        }
        catch (Exception e){
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorUnsupportedOperationException() {
        linkedGroup.addToFront(musicGroup5);
        ListIterator<MusicGroup> iterator = linkedGroup.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(musicGroup5, iterator.next());
        assertEquals(musicGroup1, iterator.next());
        assertEquals(musicGroup2, iterator.next());
        assertEquals(musicGroup3, iterator.next());
        assertEquals(false, iterator.hasNext());
        
        try{
            iterator.remove();
            assertTrue("Did not throw a UnsupportedOperationException",false);
        }
        catch (UnsupportedOperationException e){
            assertTrue("Successfully threw a UnsupportedOperationException",true);
        }
        catch (Exception e){
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testRemove() {
        assertEquals(musicGroup1,linkedGroup.getFirst());
        assertEquals(musicGroup3,linkedGroup.getLast());
        assertEquals(3,linkedGroup.getSize());
        linkedGroup.remove(musicGroup1, comparator);
        assertEquals(musicGroup2,linkedGroup.getFirst());
        assertEquals(musicGroup3,linkedGroup.getLast());
        assertEquals(2,linkedGroup.getSize());
        linkedGroup.remove(musicGroup3, comparator);
        assertEquals(musicGroup2,linkedGroup.getFirst());
        assertEquals(musicGroup2,linkedGroup.getLast());
        assertEquals(1,linkedGroup.getSize());
    }

    @Test
    public void testRetrieveFirstElement() {
        assertEquals(musicGroup1,linkedGroup.getFirst());
        assertEquals(musicGroup3,linkedGroup.getLast());
        assertEquals(3,linkedGroup.getSize());
        linkedGroup.retrieveFirstElement();
        assertEquals(musicGroup2,linkedGroup.getFirst());
        assertEquals(musicGroup3,linkedGroup.getLast());
        assertEquals(2,linkedGroup.getSize());
        linkedGroup.retrieveFirstElement();
        assertEquals(musicGroup3,linkedGroup.getFirst());
        assertEquals(musicGroup3,linkedGroup.getLast());
        assertEquals(1,linkedGroup.getSize());
    }

    @Test
    public void testRetrieveLastElement() {
        assertEquals(musicGroup1,linkedGroup.getFirst());
        assertEquals(musicGroup3,linkedGroup.getLast());
        assertEquals(3,linkedGroup.getSize());
        linkedGroup.retrieveLastElement();
        assertEquals(musicGroup1,linkedGroup.getFirst());
        assertEquals(musicGroup2,linkedGroup.getLast());
        assertEquals(2,linkedGroup.getSize());
        linkedGroup.retrieveLastElement();
        assertEquals(musicGroup1,linkedGroup.getFirst());
        assertEquals(musicGroup1,linkedGroup.getLast());
        assertEquals(1,linkedGroup.getSize());
    }


    private class GroupComparator implements Comparator<MusicGroup> {

        @Override
        public int compare(MusicGroup first, MusicGroup second) {
          return first.toString().compareTo(second.toString());
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