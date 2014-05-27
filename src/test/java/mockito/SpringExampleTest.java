/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-5-9
 */

package mockito;

/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.timesheet.service.TimesheetService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/persistence-beans.xml")
@PrepareForTest(IdGenerator.class)
public class SpringExampleTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Resource
    private TimesheetService timesheetService;

    @Test
    public void mockStaticMethod() throws Exception {
        Assert.assertNotNull(timesheetService);

        final long expectedId = 2L;
        mockStatic(IdGenerator.class);
        when(IdGenerator.generateNewId()).thenReturn(expectedId);

        assertEquals(expectedId, 2);
    }
}