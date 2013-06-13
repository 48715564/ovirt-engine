package org.ovirt.engine.ui.webadmin.uicommon.model;

import org.ovirt.engine.core.common.scheduling.ClusterPolicy;
import org.ovirt.engine.ui.common.presenter.AbstractModelBoundPopupPresenterWidget;
import org.ovirt.engine.ui.common.presenter.popup.RemoveConfirmationPopupPresenterWidget;
import org.ovirt.engine.ui.common.uicommon.model.SearchableTabModelProvider;
import org.ovirt.engine.ui.uicommonweb.UICommand;
import org.ovirt.engine.ui.uicommonweb.models.ConfirmationModel;
import org.ovirt.engine.ui.uicommonweb.models.Model;
import org.ovirt.engine.ui.uicommonweb.models.configure.scheduling.ClusterPolicyListModel;
import org.ovirt.engine.ui.webadmin.gin.ClientGinjector;
import org.ovirt.engine.ui.webadmin.section.main.presenter.popup.scheduling.ClusterPolicyPopupPresenterWidget;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ClusterPolicyModelProvider extends SearchableTabModelProvider<ClusterPolicy, ClusterPolicyListModel> {

    private final Provider<ClusterPolicyPopupPresenterWidget> clusterPolicyPopupProvider;
    private final Provider<RemoveConfirmationPopupPresenterWidget> removeConfirmPopupProvider;

    @Inject
    public ClusterPolicyModelProvider(ClientGinjector ginjector,
            final Provider<ClusterPolicyPopupPresenterWidget> clusterPolicyPopupProvider,
            final Provider<RemoveConfirmationPopupPresenterWidget> removeConfirmPopupProvider) {
        super(ginjector);
        this.clusterPolicyPopupProvider = clusterPolicyPopupProvider;
        this.removeConfirmPopupProvider = removeConfirmPopupProvider;
    }

    @Override
    public ClusterPolicyListModel getModel() {
        return getCommonModel().getClusterPolicyListModel();
    }

    @Override
    public AbstractModelBoundPopupPresenterWidget<? extends Model, ?> getModelPopup(ClusterPolicyListModel source,
            UICommand lastExecutedCommand, Model windowModel) {
        if (lastExecutedCommand.equals(getModel().getNewCommand())
                || lastExecutedCommand.equals(getModel().getEditCommand())
                || lastExecutedCommand.equals(getModel().getCloneCommand())) {
            return clusterPolicyPopupProvider.get();
        } else {
            return super.getModelPopup(source, lastExecutedCommand, windowModel);
        }
    }

    @Override
    public AbstractModelBoundPopupPresenterWidget<? extends ConfirmationModel, ?> getConfirmModelPopup(ClusterPolicyListModel source,
            UICommand lastExecutedCommand) {
        if (lastExecutedCommand.equals(getModel().getRemoveCommand())) {
            return removeConfirmPopupProvider.get();
        } else {
            return super.getConfirmModelPopup(source, lastExecutedCommand);
        }
    }

}
