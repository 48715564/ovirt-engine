package org.ovirt.engine.core.bll;

import org.ovirt.engine.core.bll.context.EngineContext;
import org.ovirt.engine.core.common.queries.VdcQueryParametersBase;
import org.ovirt.engine.core.common.utils.customprop.VmPropertiesUtils;

public class GetVmCustomPropertiesQuery<P extends VdcQueryParametersBase> extends QueriesCommandBase<P> {
    public GetVmCustomPropertiesQuery(P parameters, EngineContext engineContext) {
        super(parameters, engineContext);
    }

    @Override
    protected void executeQueryCommand() {
        getQueryReturnValue().setReturnValue(VmPropertiesUtils.getInstance().getAllVmProperties());
    }
}
