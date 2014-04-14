/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-11
 * description: 
 */

package csv;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.ProcessingInstruction;
import org.dom4j.VisitorSupport;

public class MyVisitor extends VisitorSupport {

    @Override
    public void visit(Element node) {
        if (node.isTextOnly()) {  
            System.out.println("Element:---" + node.getName() + "="  
                    + node.getText());  
        }else{  
            System.out.println("--------" + node.getName() + "-------");  
        }  
    }

    @Override
    public void visit(Attribute node) {
        System.out.println("Attibute:---" + node.getName() + "="+ node.getValue());  
    }

    @Override
    public void visit(ProcessingInstruction node) {
        System.out.println("PI:"+node.getTarget()+" "+node.getText());  
    }
}
