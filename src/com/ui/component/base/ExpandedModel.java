package com.ui.component.base;

import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tseegii on 7/2/14.
 */
public class ExpandedModel extends DefaultTreeModel {
    TreeNode root;
    String[] groupNames;


    public ExpandedModel(List dataList, String... groupNames) {
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
}

