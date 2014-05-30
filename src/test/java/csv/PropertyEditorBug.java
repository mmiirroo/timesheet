/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-5-30
 */

package csv;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

public class PropertyEditorBug {
    public static void main(String[] args) {
        PropertyEditor pe;
        pe = PropertyEditorManager.findEditor(java.lang.Integer.class);
        System.out.println("Integer.class: " + pe);
        pe = PropertyEditorManager.findEditor(java.lang.Integer.TYPE);
        System.out.println("Integer.TYPE: " + pe);

        pe = PropertyEditorManager.findEditor(java.lang.Boolean.class);
        System.out.println("Boolean.class: " + pe);
        pe = PropertyEditorManager.findEditor(java.lang.Boolean.TYPE);
        System.out.println("Boolean.TYPE: " + pe);
        
        pe = PropertyEditorManager.findEditor(java.lang.Float.class);
        System.out.println("Float.class: " + pe);
        pe = PropertyEditorManager.findEditor(java.lang.Float.TYPE);
        System.out.println("Float.TYPE: " + pe);
    }
}
