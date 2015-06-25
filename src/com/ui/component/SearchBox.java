package com.ui.component;

import org.zkoss.bind.AnnotateBinder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Textbox;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tseegii on 9/2/14.
 */
public class SearchBox<T> extends Textbox {

    private List<T> list;
    private List<T> tempTList;
    private String[] searchNames;
    private Component listbox;
    private AnnotateBinder binder;

    public SearchBox(List<T> list, String[] searchNames, Component listbox, AnnotateBinder binder) {
        this.setInstant(true);
        this.list = list;
        this.searchNames = searchNames;
        this.tempTList = new ArrayList<>();
        tempTList.addAll(list);
        this.listbox = listbox;
        this.binder = binder;
        this.setSclass("form-control input-sm");
        this.setPlaceholder("Хайлт...");
        this.setWidth("250px");
        buildListener();
    }


    private void buildListener() {
        this.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                search();
            }
        });
    }

    public void search() {
        list.clear();
        String value = getValue();
        if (value.length() > 0) {
            for (T fieldEntity : tempTList) {
                try {
                    for (String searchName : searchNames) {
                        String methodName = searchName;
                        Class c = fieldEntity.getClass();
                        Object data = null;
                        if (methodName.contains(".")) {
                            data = getParentObject(fieldEntity, methodName);
                        } else {
                            methodName = "get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                            Method method = c.getMethod(methodName);
                            data = method.invoke(fieldEntity);
                        }
                        if (data != null)
                            if (data.toString().toLowerCase().contains(value.toLowerCase())) {
                                if (!list.contains(fieldEntity)) {
                                    list.add(fieldEntity);
                                }
                            }
                    }
                } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } else {
            list.addAll(tempTList);
        }
        refresh();
    }

    private void refresh() {
        binder.loadComponent(listbox, true);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    private Object getParentObject(Object data, String groupName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object parent;

        String[] methodNames = groupName.split("\\.");
        for (String methodName : methodNames) {
            if (data != null) {
                Class c = data.getClass();
                methodName = "get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                Method method = c.getMethod(methodName);
                data = method.invoke(data);
            } else {
                return null;
            }
        }
        parent = data;
        if (parent != null) {
            return parent;
        }

        throw new NullPointerException("Group parent is null!!!");
    }


}
