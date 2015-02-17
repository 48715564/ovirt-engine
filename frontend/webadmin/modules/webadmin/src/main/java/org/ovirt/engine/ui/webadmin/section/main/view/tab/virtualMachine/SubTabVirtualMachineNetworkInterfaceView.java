package org.ovirt.engine.ui.webadmin.section.main.view.tab.virtualMachine;

import org.ovirt.engine.core.common.businessentities.VM;
import org.ovirt.engine.core.common.businessentities.network.VmNetworkInterface;
import org.ovirt.engine.ui.common.CommonApplicationConstants;
import org.ovirt.engine.ui.common.idhandler.ElementIdHandler;
import org.ovirt.engine.ui.common.system.ClientStorage;
import org.ovirt.engine.ui.common.uicommon.model.SearchableDetailModelProvider;
import org.ovirt.engine.ui.common.view.AbstractSubTabTableWidgetView;
import org.ovirt.engine.ui.common.widget.table.column.AbstractBooleanColumn;
import org.ovirt.engine.ui.common.widget.uicommon.vm.VmInterfaceListModelTable;
import org.ovirt.engine.ui.uicommonweb.models.vms.VmInterfaceListModel;
import org.ovirt.engine.ui.uicommonweb.models.vms.VmListModel;
import org.ovirt.engine.ui.webadmin.ApplicationConstants;
import org.ovirt.engine.ui.webadmin.ApplicationMessages;
import org.ovirt.engine.ui.webadmin.ApplicationTemplates;
import org.ovirt.engine.ui.webadmin.section.main.presenter.tab.virtualMachine.SubTabVirtualMachineNetworkInterfacePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

public class SubTabVirtualMachineNetworkInterfaceView extends AbstractSubTabTableWidgetView<VM, VmNetworkInterface, VmListModel<Void>, VmInterfaceListModel> implements SubTabVirtualMachineNetworkInterfacePresenter.ViewDef {

    interface ViewIdHandler extends ElementIdHandler<SubTabVirtualMachineNetworkInterfaceView> {
        ViewIdHandler idHandler = GWT.create(ViewIdHandler.class);
    }

    @Inject
    public SubTabVirtualMachineNetworkInterfaceView(
            SearchableDetailModelProvider<VmNetworkInterface, VmListModel<Void>, VmInterfaceListModel> modelProvider,
            EventBus eventBus,
            ClientStorage clientStorage,
            ApplicationConstants constants,
            ApplicationMessages messages,
            ApplicationTemplates templates) {
        super(new VmInterfaceListModelTable(modelProvider, eventBus, clientStorage, constants, messages, templates) {
            @Override
            public void initTable(final CommonApplicationConstants constants) {
                super.initTable(constants);

                getTable().enableColumnResizing();

                AbstractBooleanColumn<VmNetworkInterface> portMirroringColumn =
                        new AbstractBooleanColumn<VmNetworkInterface>(constants.portMirroringEnabled()) {
                            @Override
                            public Boolean getRawValue(VmNetworkInterface object) {
                                return object.isPortMirroring();
                            }
                        };
                portMirroringColumn.makeSortable();
                getTable().addColumnWithHtmlHeader(portMirroringColumn, constants.portMirroring(), "85px"); //$NON-NLS-1$
            }
        });
        ViewIdHandler.idHandler.generateAndSetIds(this);
        initTable(constants);
        initWidget(getModelBoundTableWidget());
    }

}
