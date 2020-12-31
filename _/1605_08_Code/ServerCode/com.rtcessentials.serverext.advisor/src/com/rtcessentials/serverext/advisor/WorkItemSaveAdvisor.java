package com.rtcessentials.serverext.advisor;

import java.sql.Timestamp;

import org.eclipse.core.runtime.IProgressMonitor;

import com.ibm.team.process.common.IProcessConfigurationElement;
import com.ibm.team.process.common.advice.AdvisableOperation;
import com.ibm.team.process.common.advice.IAdvisorInfo;
import com.ibm.team.process.common.advice.IAdvisorInfoCollector;
import com.ibm.team.process.common.advice.runtime.IOperationAdvisor;
import com.ibm.team.repository.common.IAuditable;
import com.ibm.team.repository.common.TeamRepositoryException;
import com.ibm.team.workitem.common.IAuditableCommon;
import com.ibm.team.workitem.common.ISaveParameter;
import com.ibm.team.workitem.common.IWorkItemCommon;
import com.ibm.team.workitem.common.model.ISeverity;
import com.ibm.team.workitem.common.model.IWorkItem;
import com.ibm.team.workitem.common.model.IWorkItemType;
import com.ibm.team.workitem.common.model.Identifier;

public class WorkItemSaveAdvisor implements IOperationAdvisor {

	public void run(AdvisableOperation operation,
			IProcessConfigurationElement advisorConfiguration,
			IAdvisorInfoCollector collector, IProgressMonitor monitor)
			throws TeamRepositoryException {
		// Returns the operation-specific data associated with the operation
		Object data= operation.getOperationData();
		if (data instanceof ISaveParameter) {
			ISaveParameter saveParameter= (ISaveParameter) data;
			// get the new auditable state
			IAuditable auditable= saveParameter.getNewState();
			// we are interested in the auditable item only if it's a WorkItem
			if (auditable instanceof IWorkItem) {
				// get the common auditable object from the team repository 
				IAuditableCommon auditableCommon= saveParameter.getSaveOperationParameter().getAuditableCommon();
				// get the specific workitem, workitemtype and severity 
				IWorkItemCommon workItemCommon= auditableCommon.getPeer(IWorkItemCommon.class);
				IWorkItem workItem= (IWorkItem) auditable;
				IWorkItemType workItemType= workItemCommon.findWorkItemType(workItem.getProjectArea(), workItem.getWorkItemType(), monitor);
				Identifier<ISeverity> severity = workItem.getSeverity();
				// if workitem is a defect, make sure that Due Date and Description fields are filled in. 
				if (workItemType.getIdentifier().equals("defect")) {
					if (severity.getStringIdentifier().equals("severity.literal.l6")) {
						Timestamp dueDate = workItem.getDueDate();
						if (dueDate == null) {
							IAdvisorInfo info = collector.createProblemInfo("Blocking Work Item", "Blocking workitem must have a Due Date", "error");
							collector.addInfo(info);
						}
						String plainDesc = workItem.getHTMLDescription().getPlainText();
						if (plainDesc == null || plainDesc.equals("")) {
							IAdvisorInfo info = collector.createProblemInfo("Blocking Work Item", "Blocking workitem must have a Description", "error");
							collector.addInfo(info);
						}
					}
				}
			}
		}
	}
}
