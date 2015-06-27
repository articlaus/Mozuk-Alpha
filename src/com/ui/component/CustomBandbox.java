package com.ui.component;

import com.model.bean.BandboxBean;
import com.ui.component.base.EBeanUtils;
import com.ui.util.UiHelper;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tseegii on 7/1/14.
 */
public class CustomBandbox<T> extends Bandbox {
//    todo new EntitySelected yanzlah

    private BandboxBean bandboxBean;
    private List<T> TList;
    private List<T> tempTList;
    private T selectedT;
    private Listbox listbox;
    private String namedQuery;
    private String newWindowUrl;
    /**
     * Хайлтанд оролцох нэрс
     */
    private String[] searchNames = new String[]{"fullName", "register"};
    /**
     * гаргах нэрсүү
     */
    private Method[] methods;
    private Button newButton;

    private Class<T> clazz;

    private HashMap<String, Object> parameterMap;
    private Integer selectedIndex;
    private Button refreshButton;

    /**
     * @param clazz       entity class ийг зааж өгнө.
     * @param namedQuery  bandbox дээр гарах жагсаалтийн query
     * @param searchNames Хайлт хийхэд зориулсан нэрсийг дамжуулна.
     *                    Хамгийн багадаа 2 parameter дамжуулах ёостой ба
     *                    энэ parameter үүдийн эхний утга bandbox сонгогдож харагдах утга 2
     *                    дахь утга bandbox ийн жагсаалтатд дахь багана болно.
     */
    public CustomBandbox(Class<T> clazz, String namedQuery, String[] searchNames) {
        this.namedQuery = namedQuery;
        this.searchNames = searchNames;
        this.clazz = clazz;
        init();
    }

    public CustomBandbox(Class<T> clazz, String namedQuery, String[] searchNames, Integer selectedIndex) {
        this.namedQuery = namedQuery;
        this.searchNames = searchNames;
        this.clazz = clazz;
        this.selectedIndex = selectedIndex;
        init();
    }

    public CustomBandbox(Class<T> clazz, String namedQuery, String[] searchNames, Integer selectedIndex, HashMap<String, Object> parameterMap) {
        this.namedQuery = namedQuery;
        this.searchNames = searchNames;
        this.clazz = clazz;
        this.selectedIndex = selectedIndex;
        this.parameterMap = parameterMap;
        init();
    }

    /**
     * @param clazz        entity class ийг зааж өгнө.
     * @param namedQuery   bandbox дээр гарах жагсаалтийн query
     * @param searchNames  Хайлт хийхэд зориулсан нэрсийг дамжуулна.Хамгийн багадаа 2 parameter дамжуулах ёостой ба энэ parameter үүдийн эхний утга bandbox сонгогдож харагдах утга 2 дахь утга bandbox ийн жагсаалтатд дахь багана болно.
     * @param parameterMap Query оролцох parameter. key=paramName,value = value;
     */
    public CustomBandbox(Class<T> clazz, String namedQuery, String[] searchNames, HashMap<String, Object> parameterMap) {
        this.namedQuery = namedQuery;
        this.searchNames = searchNames;
        this.clazz = clazz;
        this.parameterMap = parameterMap;
        init();
    }

    /**
     * @param clazz        entity class ийг зааж өгнө.
     * @param namedQuery   bandbox дээр гарах жагсаалтийн query
     * @param searchNames  Хайлт хийхэд зориулсан нэрсийг дамжуулна.Хамгийн багадаа 2 parameter дамжуулах ёостой ба энэ parameter үүдийн эхний утга bandbox сонгогдож харагдах утга 2 дахь утга bandbox ийн жагсаалтатд дахь багана болно.
     * @param parameterMap Query оролцох parameter. key=paramName,value = value;
     * @param newWindowUrl Bandpopup дотор харагдах нэмэх товч дарагдах үед дуудагдах component ийн зам.
     */
    public CustomBandbox(Class<T> clazz, String namedQuery, String[] searchNames, HashMap<String, Object> parameterMap, String newWindowUrl) {
        this.namedQuery = namedQuery;
        this.searchNames = searchNames;
        this.clazz = clazz;
        this.parameterMap = parameterMap;
        this.newWindowUrl = newWindowUrl;
        init();
    }

    /**
     * @param clazz        entity class ийг зааж өгнө.
     * @param namedQuery   bandbox дээр гарах жагсаалтийн query
     * @param searchNames  Хайлт хийхэд зориулсан нэрсийг дамжуулна.Хамгийн багадаа 2 parameter дамжуулах ёостой ба энэ parameter үүдийн эхний утга bandbox сонгогдож харагдах утга 2 дахь утга bandbox ийн жагсаалтатд дахь багана болно.
     * @param newWindowUrl Bandpopup дотор харагдах нэмэх товч дарагдах үед дуудагдах component ийн зам.
     */
    public CustomBandbox(Class<T> clazz, String namedQuery, String[] searchNames, String newWindowUrl) {
        this.namedQuery = namedQuery;
        this.searchNames = searchNames;
        this.clazz = clazz;
        this.newWindowUrl = newWindowUrl;
        init();
    }

    public void init() {
        this.setInstant(true);
        bandboxBean = EBeanUtils.getBean(BandboxBean.class);
        methods = new Method[2];
        int i = 0;
        for (String searchName : searchNames) {
            String methodName = searchName;

            methodName = "get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
            Method method = null;
            try {
                method = clazz.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (i <= 1)
                methods[i++] = method;
        }
        refreshTList();
        buildBandbox();
        buildListeners();

    }

    private void buildListeners() {
        listbox.addEventListener(Events.ON_SELECT, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                selectedT = (T) listbox.getModel().getElementAt(listbox.getSelectedIndex());
                CustomBandbox.this.setRawValue(methods[0].invoke(selectedT));
                CustomBandbox.this.close();
            }
        });

        newButton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                Map<String, Object> map = new HashMap<>();
                map.put("customerBandboxOBJECT", CustomBandbox.this);
                map.put("selectedEntity", selectedT);
                Executions.createComponents(newWindowUrl, null, map);
            }
        });

        refreshButton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                refreshTList(selectedT);
            }
        });

        this.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                search();
            }
        });
        this.addEventListener(Events.ON_CHANGING, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                setOpen(true);
                setAutodrop(true);
                search();
            }
        });
    }

    public void search() {
        TList.clear();
//        System.out.println("1 = ");
        String value = getValue();
        if (value.length() >= 0) {
            for (T fieldEntity : tempTList) {
                try {
                    for (String searchName : searchNames) {
                        String methodName = searchName;
                        Class c = fieldEntity.getClass();
                        methodName = "get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                        Method method = c.getMethod(methodName);
                        Object data = method.invoke(fieldEntity);
                        if (data != null)
                            if (data.toString().toLowerCase().contains(value.toLowerCase())) {
                                if (!TList.contains(fieldEntity)) {
                                    TList.add(fieldEntity);
                                }
                            }
                    }
                } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } else {
            TList.addAll(tempTList);
        }

        listbox.setModel(new ListModelArray<>(TList));
    }

    public void refreshTList() {
        TList = bandboxBean.findAll(namedQuery, clazz, parameterMap);
        if (clazz.getSimpleName().equals("Employee")) {
            TList.remove(Executions.getCurrent().getSession().getAttribute("user"));
        }
        tempTList = new ArrayList<>();
        tempTList.addAll(TList);
        if (listbox != null) {
            listbox.setModel(new ListModelArray<>(TList));

        }

    }

    public void refreshTList(T selectedT) {
        refreshTList();
        setSelectedT(selectedT);
    }

    private void buildBandbox() {
        Bandpopup bandpopup = new Bandpopup();
        Toolbar toolbar = new Toolbar();
        newButton = new Button("Нэмэх");
        newButton.setIconSclass("z-icon-plus");
        toolbar.appendChild(newButton);
        if (newWindowUrl == null) {
            newButton.setVisible(false);
        }
        refreshButton = new Button();
        refreshButton.setIconSclass("z-icon-refresh");
        toolbar.appendChild(refreshButton);

        bandpopup.setParent(this);
        bandpopup.appendChild(toolbar);
        bandpopup.appendChild(buildListbox());
    }

    private Listbox buildListbox() {
        listbox = new Listbox();
        listbox.setModel(new ListModelArray<>(TList));
        listbox.setWidth("250px");
        listbox.setItemRenderer(getItemRenderer());
        if (selectedIndex != null) {
            listbox.setSelectedIndex(selectedIndex);
            this.selectedT = (T) listbox.getListModel().getElementAt(selectedIndex);
            try {
                this.setRawValue(methods[0].invoke(selectedT));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            selectedIndex = null;
        }
        return listbox;
    }


    public void setSelectedIndex(int selectedIndex) {
        this.selectedT = (T) listbox.getListModel().getElementAt(selectedIndex);
        refreshTList(this.selectedT);
    }

    public ListitemRenderer<?> getItemRenderer() {
        return new ListitemRenderer<Object>() {
            @Override
            public void render(Listitem item, Object data, int index) throws Exception {
                T entity = (T) data;
                Listcell listcell = new Listcell();
//                System.out.println("methods.length = " + methods.length);
                Method methods1 = methods[1];
                if (methods1 != null) {
                    Object data1 = methods[1].invoke(entity);
                    listcell.appendChild(UiHelper.getBandboxStyles(methods[0].invoke(entity).toString(), data1 != null ? data1.toString() : ""));
                } else {
                    Object o = methods[0].invoke(entity);
                    if (o != null)
                        listcell.appendChild(UiHelper.getBandboxStyles(methods[0].invoke(entity).toString(), ""));
                    else
                        listcell.appendChild(UiHelper.getBandboxStyles(methods[0].invoke(entity).toString(), ""));
                }
                item.appendChild(listcell);
            }
        };
    }

    public List<T> getTList() {
        return TList;
    }

    public void setTList(List<T> TList) {
        this.TList = TList;
    }

    public List<T> getTempTList() {
        return tempTList;
    }

    public void setTempTList(List<T> tempTList) {
        this.tempTList = tempTList;
    }

    public T getSelectedT() {
        return selectedT;
    }

    public void setSelectedT(T selectedT) {
        this.selectedT = selectedT;
        if (selectedT != null) {
            ListModel<Object> o = listbox.getListModel();
            for (int i = 0; i < o.getSize(); i++) {
                T company = (T) o.getElementAt(i);
                if (company.equals(selectedT)) {
                    this.listbox.setSelectedIndex(i);
                    try {
                        this.setRawValue(methods[0].invoke(company));
                        break;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.setRawValue(null);
                }
            }
        } else {
            this.setRawValue(null);
        }

    }

    /**
     * Bandbox-ыг үүсэнний дараа өөр object-оос болж утгаа шинэжлэхэд хэрэглэн
     *
     * @param namedQuery   bandbox дээр гарах жагсаалтийн query
     * @param searchNames  Хайлт хийхэд зориулсан нэрсийг дамжуулна.Хамгийн багадаа 2 parameter дамжуулах ёостой ба энэ parameter үүдийн эхний утга bandbox сонгогдож харагдах утга 2 дахь утга bandbox ийн жагсаалтатд дахь багана болно.
     * @param parameterMap Query оролцох parameter. key=paramName,value = value;
     *                     By:ganbat
     */
    public void updateList(String namedQuery, String[] searchNames, HashMap<String, Object> parameterMap) {
        this.namedQuery = namedQuery;
        this.searchNames = searchNames;
        this.parameterMap = parameterMap;
        refreshTList();

    }

    public Listbox getListbox() {
        return listbox;
    }

    public void setListbox(Listbox listbox) {
        this.listbox = listbox;
    }

    public String getNamedQuery() {
        return namedQuery;
    }

    public void setNamedQuery(String namedQuery) {
        this.namedQuery = namedQuery;
    }

    public String getNewWindowUrl() {
        return newWindowUrl;
    }

    public void setNewWindowUrl(String newWindowUrl) {
        this.newWindowUrl = newWindowUrl;
    }

    public Button getNewButton() {
        return newButton;
    }

    public void setNewButton(Button newButton) {
        this.newButton = newButton;
    }

    public HashMap<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(HashMap<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }
}
