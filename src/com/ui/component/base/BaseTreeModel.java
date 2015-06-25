package com.ui.component.base;

//import mn.monsource.framework.util.exception.BaseException;
import org.zkoss.zul.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nasanjargal
 * Date: 6/19/12
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseTreeModel extends DefaultTreeModel {

    private String[] methodName;

    public BaseTreeModel(List dataList, String... childPropertyName) {
        super(new DefaultTreeNode(null, new ArrayList()));

        methodName = childPropertyName;

        TreeNode root = (TreeNode) this.getRoot();

        for (int i = 0; i < methodName.length; i++) {
            String propertyName = methodName[i];
            methodName[i] = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
//            System.out.println("pr : "+propertyName);

        }


        buildChildNode(root, dataList);
    }

    private void buildChildNode(TreeNode node, List dataList) {
//        System.out.println("ls L "+dataList.size());
        try {
            TreeNode childNode = null;

            for (Object data : dataList) {
                try {
                    Class dataClass = data.getClass();
                    Method method = null;
                    for (String m : methodName) {
                        try{
                            method = dataClass.getDeclaredMethod(m);
                        }catch (NoSuchMethodException e){}
                    }

                    List childList = (List) method.invoke(data);
                    if (childList != null && childList.size() > 0) {
                        childNode = new DefaultTreeNode(data, new ArrayList());
                        buildChildNode(childNode, childList);
                    }else{
                        childNode = new DefaultTreeNode(data);
                    }
                } catch (NullPointerException e) {
                    childNode = new DefaultTreeNode(data);
                }

                node.add(childNode);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object getParentObject(Object data, String groupName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object parent;

        String[] methodNames = groupName.split("\\.");
        for (String methodName : methodNames) {
            Class c = data.getClass();
            methodName = "get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
            Method method = c.getMethod(methodName);
            data = method.invoke(data);
        }
        parent = data;
        if (parent != null) {
            return parent;
        }

        throw new NullPointerException("Group parent is null!!!");
    }


    public void setSort(Tree tree, final HashMap<Integer, String> sortColumns, final String clazzName) {
        List<Treecol> treecols = tree.getTreecols().getChildren();
        for (final Integer integer : sortColumns.keySet()) {
            Treecol treecol = treecols.get(integer);
            treecol.setSortAscending(new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    DefaultTreeNode s = ((DefaultTreeNode) o1);
                    DefaultTreeNode s1 = ((DefaultTreeNode) o2);
                    if (s.getData().getClass().getSimpleName().equals(clazzName) && s1.getData().getClass().getSimpleName().equals(clazzName)) {
                        try {
                            Object obj1 = getParentObject(s.getData(), sortColumns.get(integer));
                            Object obj2 = getParentObject(s1.getData(), sortColumns.get(integer));
                            return obj1.toString().compareTo(obj2.toString()) * 1;
                        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                    return 0;
                }
            });

            treecol.setSortDescending(new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    DefaultTreeNode s = ((DefaultTreeNode) o1);
                    DefaultTreeNode s1 = ((DefaultTreeNode) o2);
                    if (s.getData().getClass().getSimpleName().equals(clazzName) && s1.getData().getClass().getSimpleName().equals(clazzName)) {
                        try {
                            Object obj1 = getParentObject(s.getData(), sortColumns.get(integer));
                            Object obj2 = getParentObject(s1.getData(), sortColumns.get(integer));
                            return obj1.toString().compareTo(obj2.toString()) * (-1);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                    return 0;
                }
            });

        }
    }
}
