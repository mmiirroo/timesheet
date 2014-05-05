package reference;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import org.junit.Test;

public class TestReference {

	@Test
	public void testStrongReference() {
		Object referent = new Object();
		Object strongReference = referent;
		referent = null;
		System.gc();
		assertNotNull(strongReference);
	}

	@Test
	public void testSoftReference() {
		String str = "test";
		SoftReference<String> softReference = new SoftReference<String>(str);
		str = null;
		System.gc();
		assertNotNull(softReference.get());
	}

	@Test
	public void testWeakReference() {
		String str = "test";
		WeakReference<String> weakReference = new WeakReference<String>(str);
		str = null;
		System.gc();
		assertNull(weakReference.get());
	}

	@Test
	public void testReferenceQueue() throws InterruptedException {
		Object referent = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
		WeakReference<Object> weakReference = new WeakReference<Object>(
				referent, referenceQueue);
		
		assertFalse(weakReference.isEnqueued());
		Reference<? extends Object> polled = referenceQueue.poll();
		assertNull(polled);
		
		referent = null;
		System.gc();
		
		assertTrue(weakReference.isEnqueued());
		Reference<? extends Object> removed = referenceQueue.remove(); 
		assertNotNull(removed);
	}
}
