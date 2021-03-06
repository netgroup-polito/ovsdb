package org.opendaylight.ovsdb.southbound.transactions.md;

import org.opendaylight.controller.md.sal.binding.api.ReadWriteTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.ovsdb.lib.message.TableUpdates;
import org.opendaylight.ovsdb.lib.schema.DatabaseSchema;
import org.opendaylight.ovsdb.southbound.SouthboundMapper;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ovsdb.rev150105.ovsdb.node.attributes.ConnectionInfo;

public class OvsdbNodeCreateCommand extends AbstractTransactionCommand {

    public OvsdbNodeCreateCommand(ConnectionInfo key,TableUpdates updates,DatabaseSchema dbSchema) {
        super(key,updates,dbSchema);
    }

    @Override
    public void execute(ReadWriteTransaction transaction) {
        transaction.put(LogicalDatastoreType.OPERATIONAL,
                SouthboundMapper.createInstanceIdentifier(getConnectionInfo()),
                SouthboundMapper.createNode(getConnectionInfo()));
    }

}
