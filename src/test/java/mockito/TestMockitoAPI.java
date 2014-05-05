package mockito;

import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.hamcrest.Matcher;
import org.junit.Test;

public class TestMockitoAPI {

	@SuppressWarnings({"unchecked"})
	@Test
	public void testMockito(){
		LinkedList <String> mockedList = mock(LinkedList .class);
		mockedList.add("one");
		mockedList.clear();
		
		//verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
		
		//stubbing 
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new RuntimeException());
		
		System.out.println(mockedList.get(0));
		System.out.println(mockedList.get(1));
		System.out.println(mockedList.get(999));
		
		verify(mockedList).get(0);
		when(mockedList.get(anyInt())).thenReturn("element");
		//stubbing using built-in anyInt() argument matcher
		when(mockedList.get(anyInt())).thenReturn("element");
		 
		//stubbing using hamcrest (let's say isValid() returns your own hamcrest matcher):
		when(mockedList.contains(argThat(isValid()))).thenReturn(true);
		 
		//following prints "element"
		System.out.println(mockedList.get(999));
		 
		//you can also verify using an argument matcher
		verify(mockedList).get(anyInt());
		}

	private Matcher<Boolean> isValid() {
		// TODO Auto-generated method stub
		return null;
	}
}
