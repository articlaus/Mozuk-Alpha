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
 * Date: 7/16/12
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseGroupModel extends DefaultTreeModel {
    TreeNode root;
    String[] groupNames;


    public BaseGroupModel(List dataList, String... groupNames) {
        super(new DefaultTreeNode(null, new ArrayList()));


        if (dataList != null) {

            this.root = (TreeNode) this.getRoot();
            this.groupNames = groupNames;

            List<TreeNode> treeNodeList = new ArrayList<TreeNode>();

            for (Object data : dataList) {
                treeNodeList.add(new DefaultTreeNode(data));
            }

            if (treeNodeList.size() > 0) {
                treeNodeList = buildTree(0, treeNodeList);
            }

            for (TreeNode treeNode : treeNodeList) {
                this.root.add(treeNode);
            }
        } else {
            throw new NullPointerException("dataList is required.");
        }
    }

    private List<TreeNode> buildTree(int index, List<TreeNode> dataList) {
        try {

            HashMap<Object, List<TreeNode>> dataMap = new HashMap<Object, List<TreeNode>>();
            String groupName = groupNames[index];

            for (TreeNode treeNode : dataList) {


                Object parent = getParentObject(treeNode.getData(), groupName);

                if (!dataMap.containsKey(parent)) {
                    dataMap.put(parent, new ArrayList());
                }
                dataMap.get(parent).add(treeNode);
            }

            List<TreeNode> treeNodeList = new ArrayList<TreeNode>();

            for (Object data : dataMap.keySet()) {
                TreeNode parent = new DefaultTreeNode(data, dataMap.get(data));
                treeNodeList.add(parent);
            }

            if (groupNames.length > index + 1) {
                treeNodeList = buildTree(index + 1, treeNodeList);
            }

            return treeNodeList;

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
                            return obj1.toString().compareTo(obj2.toString())*1;
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
