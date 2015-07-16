package com.ui.controller.main.overtime;

import com.model.bean.OtherBean;
import com.model.bean.OvertimeBean;
import com.model.entity.LeaveAbsence;
import com.model.entity.Overtime;
import com.model.entity.OvertimeDates;
import com.model.entity.WorkMonths;
import com.ui.component.CustomBandbox;
import com.ui.component.SearchBox;
import com.ui.component.base.BaseTreeModel;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Articlaus on 7/2/15.
 */
public class OvertimeHistoryPanelController extends MainComponent {

    BaseTreeModel overtimeTreeModel;
    OvertimeBean overtimeBean;
    List<Overtime> overtimeList;
    OtherBean otherBean;
    private CustomBandbox<WorkMonths> workMonthsCustomBandbox;

    HashMap<String, Listbox> overtimeMap;

    @Wire
    Tree overtimeTree;

    @Wire
    Cell workCell, searchCell;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        overtimeBean = EBeanUtils.getBean(OvertimeBean.class);
        otherBean = EBeanUtils.getBean(OtherBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);

        overtimeMap = new HashMap<>();
        refresh();
        initComponents();

        workMonthsCustomBandbox = new CustomBandbox<WorkMonths>(WorkMonths.class, "WorkMonths.findAll", new String[]{"yearAndMonth"});
        workMonthsCustomBandbox.getListbox().addEventListener(Events.ON_CHANGING, event -> {
            overtimeList = overtimeBean.findByWorkMonthsId(workMonthsCustomBandbox.getSelectedT());
            getBinder().loadComponent(overtimeTree, true);
        });
        workCell.appendChild(workMonthsCustomBandbox);
//        SearchBox<LeaveAbsence> searchBox = new SearchBox<>(overtimeList, new String[]{"employeeCode.fullName", "ovetimeDatesList.", "employeeCode.fullName", "workMonthsId.month"}, leaveHistoryListBox, getBinder());
//        searchCell.appendChild(searchBox);
    }

    public void refresh() {
        overtimeList = overtimeBean.findAll();
        getBinder().loadComponent(overtimeTree, true);
    }

    private void initComponents() {
        if (overtimeTree != null) {
            overtimeTree.setItemRenderer(new TreeitemRenderer<TreeNode>() {
                @Override
                public void render(Treeitem treeitem, TreeNode treeNode, int i) throws Exception {
                    Object data = treeNode.getData();
                    Treerow treerow = new Treerow();
                    treerow.setParent(treeitem);
                    treeitem.setValue(data);

                    if (data instanceof Overtime) {
                        final Overtime entity = (Overtime) data;
                        overtimeMap.put(entity.getEmployeeCode().getCode(), null);
                        A a = new A("*" + entity.getEmployeeCode().getFullName());
                        a.setStyle("text-decoration: underline;");
                        a.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                getMainInclude().setDynamicProperty("employee", entity.getEmployeeCode());

                            }
                        });
                        Treecell treecell = new Treecell();
                        treecell.appendChild(a);
                        treerow.appendChild(treecell);
                        treerow.appendChild(new Treecell(entity.getReason()));

                    } else {
                        OvertimeDates entity = (OvertimeDates) data;
                        Listbox listbox = getBox(entity);
                        if (i == 0) {
                            Treecell treecell = new Treecell();
                            treecell.setSpan(6);
                            treecell.appendChild(listbox);
                            treerow.appendChild(treecell);
                        }
                    }
                }
            });
        }
    }

    private Listbox getBox(OvertimeDates overtimeDates) {
        if (overtimeMap.get(overtimeDates.getOvertimeid().getEmployeeCode().getCode()) == null) {
            Listbox listbox = new Listbox();
            Listhead listhead = new Listhead();
            listhead.appendChild(new Listheader("Ажиллах өдөр", "", "40%"));
            listhead.appendChild(new Listheader("Эхлэх цаг", "", "30%"));
            listhead.appendChild(new Listheader("Дуусах цаг", "", "30%"));
            listbox.appendChild(listhead);
            overtimeMap.put(overtimeDates.getOvertimeid().getEmployeeCode().getCode(), listbox);
        }

        if (overtimeMap.get(overtimeDates.getOvertimeid().getEmployeeCode().getCode()) != null) {
            Listbox listbox = overtimeMap.get(overtimeDates.getOvertimeid().getEmployeeCode().getCode());
            Listitem listitem = new Listitem();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            listitem.appendChild(new Listcell(format.format(overtimeDates.getWorkDate())));
            listitem.appendChild(new Listcell(timeFormat.format(overtimeDates.getStartTime())));
            listitem.appendChild(new Listcell(timeFormat.format(overtimeDates.getEndTime())));
            listbox.appendChild(listitem);
        }
        return overtimeMap.get(overtimeDates.getOvertimeid().getEmployeeCode().getCode());
    }

    public BaseTreeModel getOvertimeTreeModel() {
        return overtimeTreeModel;
    }

    public void setOvertimeTreeModel(BaseTreeModel overtimeTreeModel) {
        this.overtimeTreeModel = overtimeTreeModel;
    }

    public List<Overtime> getOvertimeList() {
        return overtimeList;
    }

    public void setOvertimeList(List<Overtime> overtimeList) {
        this.overtimeList = overtimeList;
    }

    public HashMap<String, Listbox> getOvertimeMap() {
        return overtimeMap;
    }

    public void setOvertimeMap(HashMap<String, Listbox> overtimeMap) {
        this.overtimeMap = overtimeMap;
    }


}