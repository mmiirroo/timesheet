/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-5-8
 */

package mockito;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.timesheet.service.TimesheetService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:persistence-beans.xml")
@PrepareForTest(IdGenerator.class)
public class TestMockStaticMethod{
    
    @Resource
    private TimesheetService timesheetService;
    
    @Rule
    public PowerMockRule rule = new PowerMockRule();
    
    @Test
    @PrepareForTest(ClassUnderTest.class)
    public void testCallSystemStaticMethod() {
        Assert.assertNotNull(timesheetService);
        
        ClassUnderTest underTest = new ClassUnderTest();
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getProperty("aaa")).thenReturn("bbb");
        Assert.assertEquals("bbb", underTest.callSystemStaticMethod("aaa"));
    }
    
    @Test
    public void mockStaticMethod() throws Exception {
        // Given
        final long expectedId = 2L;
        mockStatic(IdGenerator.class);
        when(IdGenerator.generateNewId()).thenReturn(expectedId);
        
        Assert.assertNotNull(timesheetService);
        assertEquals(expectedId, 2);


    }
}

class ClassUnderTest {

    public boolean callSystemFinalMethod(String str) {
        return str.isEmpty();
    }

    public String callSystemStaticMethod(String str) {
        return System.getProperty(str);
    }
}
